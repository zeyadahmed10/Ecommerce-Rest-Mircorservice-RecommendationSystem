package com.ecommerce.authservice.controller;

import com.ecommerce.authservice.dto.SigninRequest;
import com.ecommerce.authservice.dto.UserDto;
import com.ecommerce.authservice.service.KeycloakService;
import org.springframework.beans.factory.annotation.Autowired;
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
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody UserDto userDto){
        Response response = service.addUser(userDto);
        return new ResponseEntity<>(response.getEntity(), HttpStatusCode.valueOf(response.getStatus()));
    }
    @PostMapping("/signin")
    public ResponseEntity<Object> signin(@RequestBody SigninRequest signinRequest){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED.toString());
        headers.add("Accept", MediaType.APPLICATION_JSON.toString()); //Optional in case server sends back JSON data

        MultiValueMap<String, String> requestBody = new LinkedMultiValueMap<String, String>();
        requestBody.add("grant_type", "password");
        requestBody.add("username", signinRequest.getUsername());
        requestBody.add("password", signinRequest.getPassword());
        requestBody.add("client_id", "ecommerce-auth");
        requestBody.add("client_secret", "lmXRK4udDbxWSAOzpirhmSdLH4h0Cu6T");

        HttpEntity formEntity = new HttpEntity<MultiValueMap<String, String>>(requestBody, headers);

        ResponseEntity<Object> response =
                restTemplate.exchange("http://localhost:8080/realms/ecommerce-micro/protocol/openid-connect/token", HttpMethod.POST, formEntity, Object.class);
        return response;
    }
}
