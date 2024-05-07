package tn.esprit.esprobackend.controllers;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


import tn.esprit.esprobackend.Dto.LoginRequest;
import tn.esprit.esprobackend.Dto.LoginResponse;
import tn.esprit.esprobackend.repositories.UserRepository;
import tn.esprit.esprobackend.Utils.JwtUtil;
import tn.esprit.esprobackend.services.jwt.UserServiceImpl;

import java.io.IOException;

@RestController
@AllArgsConstructor
@RequestMapping("/login")
@CrossOrigin(origins = "http://localhost:4200")

public class LoginController {


    private final AuthenticationManager authenticationManager;

  private final UserServiceImpl userService;
    private final JwtUtil jwtUtil;
    private final UserRepository userRepository;





    @PostMapping
    public LoginResponse login(@RequestBody LoginRequest loginRequest, HttpServletResponse response) throws IOException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            throw new BadCredentialsException("Incorrect email or password.");
        } catch (DisabledException disabledException) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Customer is not activated");
            return null;
        }
        final UserDetails userDetails = userService.loadUserByUsername(loginRequest.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername(), userRepository.getRolFromUser(userDetails.getUsername()).getRole());

        return new LoginResponse(jwt);
    }
}
