package tn.esprit.esprobackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import tn.esprit.esprobackend.Dto.SignupRequest;
import tn.esprit.esprobackend.entities.User;
import tn.esprit.esprobackend.services.AuthService;

@RestController
@AllArgsConstructor
@RequestMapping("/signup")
@CrossOrigin(origins = "http://localhost:4200")

public class SignupController {

    private final AuthService authService;


    @PostMapping
    public ResponseEntity<?> signupCustomer(@RequestBody SignupRequest signupRequest) {
        User createdCustomer = authService.createCustomer(signupRequest);
        if (createdCustomer != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to create customer");
        }
    }
}
