package com.ecommerce.apigateway.configuration;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public RouteLocator gatewayRouter(RouteLocatorBuilder builder) throws Exception {
        return builder.routes().route(
                predicateSpec -> predicateSpec.path("/category/**").uri("lb://category")
        ).build();
    }
}
