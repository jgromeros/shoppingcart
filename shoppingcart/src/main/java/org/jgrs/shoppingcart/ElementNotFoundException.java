package org.jgrs.shoppingcart;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = org.springframework.http.HttpStatus.NOT_FOUND, reason = "Element not found")
public class ElementNotFoundException extends RuntimeException {
}
