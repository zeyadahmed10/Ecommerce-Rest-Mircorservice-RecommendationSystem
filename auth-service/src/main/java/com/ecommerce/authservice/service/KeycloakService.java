package com.ecommerce.authservice.service;

import com.ecommerce.authservice.dto.UserDto;
import com.ecommerce.authservice.keycloak.Credentials;
import com.ecommerce.authservice.keycloak.KeycloakConfig;
import lombok.AllArgsConstructor;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.admin.client.resource.UsersResource;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.Response;
import java.util.*;

@AllArgsConstructor
@Service
public class KeycloakService {
    public void addRealmRoleToUser(String username, String roleName){
        String client_id = KeycloakConfig.getInstance()
                .realm(KeycloakConfig.REALM)
                .clients()
                .findByClientId(KeycloakConfig.CLIENT_ID)
                .get(0)
                .getId();
        String userId = KeycloakConfig.getInstance()
                .realm(KeycloakConfig.REALM)
                .users()
                .search(username)
                .get(0)
                .getId();
        UserResource user = KeycloakConfig.getInstance()
                .realm(KeycloakConfig.REALM)
                .users()
                .get(userId);
        List<RoleRepresentation> roleToAdd = new LinkedList<>();
        roleToAdd.add(KeycloakConfig.getInstance()
                .realm(KeycloakConfig.REALM)
                .clients()
                .get(client_id)
                .roles()
                .get(roleName)
                .toRepresentation()
        );
        user.roles().clientLevel(client_id).add(roleToAdd);
    }

    public void addUser(UserDto userDto) {
        try{
            CredentialRepresentation credential = Credentials
                    .createPasswordCredentials(userDto.getPassword());
            UserRepresentation user = new UserRepresentation();
            user.setUsername(userDto.getUsername());
            user.setFirstName(userDto.getFirstName());
            user.setLastName(userDto.getLastName());
            user.setEmail(userDto.getEmail());
            user.setCredentials(Collections.singletonList(credential));
            user.setEnabled(true);
            UsersResource instance = getInstance();
            var response =instance.create(user);
            if(response.getStatus()>=300||response.getStatus()<200)
                throw new RuntimeException("Error while creating the user: ");
            addRealmRoleToUser(userDto.getUsername(),"user");


        }
        catch (Exception e) {
            throw e;
        }

    }

    public List<UserRepresentation> getUser(String userName){
        UsersResource usersResource = getInstance();
        List<UserRepresentation> user = usersResource.search(userName, true);
        return user;

    }

    public void updateUser(String userId, UserDto userDto) {
        CredentialRepresentation credential = Credentials
                .createPasswordCredentials(userDto.getPassword());
        UserRepresentation user = new UserRepresentation();
        user.setUsername(userDto.getUsername());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setCredentials(Collections.singletonList(credential));

        UsersResource usersResource = getInstance();
        usersResource.get(userId).update(user);
    }
    public void deleteUser(String userId){
        UsersResource usersResource = getInstance();
        usersResource.get(userId)
                .remove();
    }


    public void sendVerificationLink(String userId){
        UsersResource usersResource = getInstance();
        usersResource.get(userId)
                .sendVerifyEmail();
    }

    public void sendResetPassword(String userId){
        UsersResource usersResource = getInstance();

        usersResource.get(userId)
                .executeActionsEmail(Arrays.asList("UPDATE_PASSWORD"));
    }

    public UsersResource getInstance(){
        return KeycloakConfig.getInstance().realm(KeycloakConfig.REALM).users();
    }


}