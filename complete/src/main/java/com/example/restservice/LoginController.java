package com.example.restservice;

import com.example.restservice.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class LoginController {

    @Autowired
    private RestTemplate restTemplate;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        String authServiceUrl = "https://reqres.in/api/login";
        LoginRequest request = new LoginRequest(loginRequest.getUsername(), loginRequest.getPassword());
        ResponseEntity<String> response = restTemplate.postForEntity(authServiceUrl, request, String.class);
        return new ResponseEntity<>(response.getBody(), HttpStatus.OK);
    }

    @GetMapping("/get-users")
    public String getUsers() {
        RestTemplate restTemplate = new RestTemplate();
        String url = "https://reqres.in/api/users";
        ResponseEntity<UserResponse> response = restTemplate.getForEntity(url, UserResponse.class);
        return response.getBody().getSupport().getUrl();
    }
}