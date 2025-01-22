// filepath: /src/main/java/com/example/demo/controller/AuthController.java
package com.example.demo.controllers;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/realms/Demo")
public class AuthController {

    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/protocol/openid-connect/token")
    public String authenticate(@RequestBody AuthRequest authRequest) {
        // Basic authentication logic
        if ("username".equals(authRequest.getUsername()) && "password".equals(authRequest.getPassword())) {
            return "Authenticated";
        } else {
            return "Authentication failed";
        }
    }
}