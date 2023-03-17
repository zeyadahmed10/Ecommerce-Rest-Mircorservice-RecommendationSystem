package com.ecommerce.authservice.controller;

import com.ecommerce.authservice.dto.SigninRequest;
import com.ecommerce.authservice.dto.UserDto;
import com.ecommerce.authservice.keycloak.KeycloakConfig;
import com.ecommerce.authservice.service.KeycloakService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.ws.rs.core.Response;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    KeycloakService service;
    @Autowired
    RestTemplate restTemplate;
    @Value("${keycloak.loginUrl}")
    private String loginUrl;
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody @Valid UserDto userDto){
        Response response = service.addUser(userDto);
        return new ResponseEntity<>(response.getEntity(), HttpStatusCode.valueOf(response.getStatus()));
    }
    @PostMapping("/signin")
    public ResponseEntity<Object> signin(@RequestBody @Valid SigninRequest signinRequest){
        //creating headers for request
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED.toString());
        headers.add("Accept", MediaType.APPLICATION_JSON.toString()); //Optional in case server sends back JSON data
        //creating body for request
        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<String, String>();
        requestBody.add("grant_type", "password");
        requestBody.add("username", signinRequest.getUsername());
        requestBody.add("password", signinRequest.getPassword());
        requestBody.add("client_id", KeycloakConfig.CLIENT_ID);
        requestBody.add("client_secret", KeycloakConfig.CLIENT_SECRET);

        HttpEntity formEntity = new HttpEntity<MultiValueMap<String, String>>(requestBody, headers);
        try{
            return restTemplate.exchange(loginUrl, HttpMethod.POST, formEntity, Object.class);
        }catch(Exception ex){
            throw new RuntimeException(ex.getMessage());
        }

    }
}
