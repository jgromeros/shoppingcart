FROM eclipse-temurin:17
EXPOSE 8080
EXPOSE 8083
MAINTAINER jgromero
COPY build/libs/shoppingcart-0.0.1-SNAPSHOT.jar shoppingcart-0.0.1-SNAPSHOT.jar
ENV JAVA_TOOL_OPTIONS=-agentlib:jdwp=transport=dt_socket,address=*:8083,server=y,suspend=n
ENTRYPOINT ["java","-jar","/shoppingcart-0.0.1-SNAPSHOT.jar"]