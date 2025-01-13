package org.jgrs.shoppingcart.cart;

import lombok.RequiredArgsConstructor;
import org.jgrs.shoppingcart.customer.Customer;
import org.jgrs.shoppingcart.customer.CustomerRepository;
import org.jgrs.shoppingcart.product.ProductPrice;
import org.jgrs.shoppingcart.product.ProductPriceRepository;
import org.jgrs.shoppingcart.promotion.PriceCalculator;
import org.jgrs.shoppingcart.promotion.Promotion3x2PriceCalculator;
import org.jgrs.shoppingcart.promotion.PromotionService;
import org.jgrs.shoppingcart.promotion.VIPPriceCalculator;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final PromotionService promotionService;
    private final ProductPriceRepository productPriceRepository;
    private final CustomerRepository customerRepository;

    public Cart createCart(CartRequest cartRequest) {
        Cart cart = null;
        if (cartRequest.getCartId() == null) {
            cart = Cart.builder()
                    .customer(cartRequest.getCustomer())
                    .items(cartRequest.getItems())
                    .build();
        } else {
            cart = cartRepository.findById(cartRequest.getCartId()).orElse(null);
        }
        cart.getItems().stream().forEach(cartItem -> {
            cartItem.setProductPrice(productPriceRepository.findById(cartItem.getProductPrice().getId()).orElse(null));
        });
        cart.setCustomer(customerRepository.findById(cart.getCustomer().getId()).orElse(null));
        updateTotals(cart);
        cart = cartRepository.save(cart);
        return cart;
    }

    public Cart addToCart(Integer cartId, CartUpdateRequest cartUpdateRequest) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null) {
            throw new IllegalArgumentException("Cart not found");
        }
        Map<ProductPrice, CartItem> updatedItems = cart.getItems().stream()
                .collect(Collectors.toMap(item -> item.getProductPrice(), item -> item));
        cartUpdateRequest.getItems().stream().forEach(cartItem -> {
            updatedItems.computeIfPresent(cartItem.getProductPrice(), (k, v) -> {
                v.setQuantity(v.getQuantity() + cartItem.getQuantity());
                return v;
            });
            updatedItems.computeIfAbsent(cartItem.getProductPrice(), k -> cartItem);
            cartItem.setProductPrice(productPriceRepository.findById(cartItem.getProductPrice().getId()).orElse(null));
        });
        cart.setItems(updatedItems.values().stream().collect(Collectors.toList()));
        updateTotals(cart);
        cart = cartRepository.save(cart);
        return cart;
    }

    private void updateTotals(Cart cart) {
        cart.setTotalBeforeDiscount(promotionService.calculatePriceBeforeDiscount(cart));
        cart.setDiscount(promotionService.calculateDiscount(cart));
        cart.setTotal(promotionService.calculateTotalPrice(cart));
    }

    public Cart removeFromCart(Integer cartId, Integer itemId) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart == null) {
            throw new IllegalArgumentException("Cart not found");
        }
        cart.getItems().remove(CartItem.builder().id(itemId).build());
        updateTotals(cart);
        cart = cartRepository.save(cart);
        return cart;
    }

}
