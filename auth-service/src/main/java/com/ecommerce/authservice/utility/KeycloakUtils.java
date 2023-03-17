package com.ecommerce.authservice.utility;

import com.ecommerce.authservice.keycloak.KeycloakConfig;
import org.keycloak.representations.idm.ClientRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.stream.Collectors;

public class KeycloakUtils {
    public List<String> getAllRoles(){
        ClientRepresentation clientRep = KeycloakConfig.getInstance()
                .realm(KeycloakConfig.REALM)
                .clients()
                .findByClientId(KeycloakConfig.CLIENT_ID)
                .get(0);
        List<String> availableRoles = KeycloakConfig.getInstance()
                .realm(KeycloakConfig.REALM)
                .clients()
                .get(clientRep.getId())
                .roles()
                .list()
                .stream()
                .map(role -> role.getName())
                .collect(Collectors.toList());
        return availableRoles;
    }
    public String addRealmRole(String new_role_name){
        if(!getAllRoles().contains(new_role_name)){
            RoleRepresentation roleRep = new  RoleRepresentation();
            roleRep.setName(new_role_name);
            roleRep.setDescription("role_" + new_role_name);
            ClientRepresentation clientRep = KeycloakConfig.getInstance()
                    .realm(KeycloakConfig.REALM)
                    .clients()
                    .findByClientId(KeycloakConfig.CLIENT_ID)
                    .get(0);
            KeycloakConfig.getInstance().realm(KeycloakConfig.REALM)
                    .clients()
                    .get(clientRep.getId())
                    .roles()
                    .create(roleRep);
        }
        return "added";
    }
}
