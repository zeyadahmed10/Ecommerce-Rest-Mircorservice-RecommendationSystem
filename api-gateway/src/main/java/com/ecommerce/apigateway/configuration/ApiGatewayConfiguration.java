package com.ecommerce.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApiGatewayConfiguration {
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) throws Exception {
        return builder.routes()
                .route(p-> p.path("/api/v1/category/**").uri("lb://product-service"))
                .route(p-> p.path("/api/v1/product/**").uri("lb://product-service")).build();
    }
}
