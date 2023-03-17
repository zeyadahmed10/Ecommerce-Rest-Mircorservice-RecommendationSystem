package com.ecommerce.authservice.keycloak;

import org.jboss.resteasy.client.jaxrs.internal.ResteasyClientBuilderImpl;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;

import static org.keycloak.OAuth2Constants.CLIENT_SECRET;

public class KeycloakConfig {

    //static members
    static Keycloak keycloak = null;
    final static String SERVER_URL = "http://localhost:8080";
    public final static String REALM = "ecommerce-micro";
    public final static String CLIENT_ID = "ecommerce-auth";
    public final static String CLIENT_SECRET = "lmXRK4udDbxWSAOzpirhmSdLH4h0Cu6T";
    final static String USERNAME = "admin-micro";
    final static String PASSWORD = "password";

    public KeycloakConfig() {
    }

    public static Keycloak getInstance(){
        if(keycloak == null){

            keycloak = KeycloakBuilder.builder()
                    .serverUrl(SERVER_URL)
                    .realm(REALM)
                    .grantType(OAuth2Constants.PASSWORD)
                    .username(USERNAME)
                    .password(PASSWORD)
                    .clientId(CLIENT_ID)
                    .clientSecret(CLIENT_SECRET)
                    .resteasyClient(new ResteasyClientBuilderImpl()
                            .connectionPoolSize(10)
                            .build()
                    )
                    .build();
        }
        return keycloak;
    }
}
