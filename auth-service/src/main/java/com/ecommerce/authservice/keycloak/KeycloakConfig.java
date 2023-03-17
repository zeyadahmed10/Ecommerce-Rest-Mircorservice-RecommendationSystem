package com.ecommerce.authservice.keycloak;

import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;

public class KeycloakConfig {
    static Keycloak keycloak = null;
    final static String serverUrl = "http://localhost:8080";
    public final static String realm = "ecommerce-micro";
    final static String clientId = "ecommerce-auth";
    final static String clientSecret = "lmXRK4udDbxWSAOzpirhmSdLH4h0Cu6T";
    final static String username = "admin-micro";
    final static String password = "password";


    public KeycloakConfig() {
    }

    public static Keycloak getInstance(){
        if(keycloak == null){

            keycloak = KeycloakBuilder.builder()
                    .serverUrl(serverUrl)
                    .realm(realm)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(username)
                    .password(password)
                    .clientId(clientId)
                    .clientSecret(clientSecret)
                    .resteasyClient(new ResteasyClientBuilderImpl()
                            .connectionPoolSize(10)
                            .build()
                    )
                    .build();
        }
        return keycloak;
    }
}
