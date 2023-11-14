package com.example.resttempl.controller;

import com.example.resttempl.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;





@RestController
public class Controller {
    private final RestTemplate restTemplate;
    private final String url = "http://94.198.50.185:7081/api/users";
    private HttpHeaders headers;

    @Autowired
    public Controller(RestTemplate restTemplate, HttpHeaders headers) {
        this.restTemplate = restTemplate;
        this.headers = headers;
        this.headers.set("Cookie",
                String.join(";", restTemplate.headForHeaders(url).get("Set-Cookie")));
    }

    @GetMapping
    public List<User> getAll() {
        ResponseEntity<List<User>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() { });
        System.out.println(responseEntity.getHeaders());
        return responseEntity.getBody();
    }

    @PostMapping
    public ResponseEntity<String> create() {
            User newUser = new User(3L,"James", "Brown", (byte) 20);
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<User> request = new HttpEntity<>(newUser, headers);
            ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, request, String.class);
            System.out.println("Response: " + responseEntity.getBody());
            return responseEntity;

    }


    @PutMapping()
    public ResponseEntity<String> update() {
        User newUser = new User(3L,"Thomas", "Shelby", (byte) 20);
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> request = new HttpEntity<>(newUser, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, request, String.class, 3L);
        System.out.println("Response: " + responseEntity.getBody());
        return responseEntity;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url + "/" + id, HttpMethod.DELETE, entity, String.class);
        System.out.println("Response: " + responseEntity.getBody());
        return responseEntity;
    }

}
