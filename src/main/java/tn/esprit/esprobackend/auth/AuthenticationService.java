package tn.esprit.esprobackend.auth;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import tn.esprit.esprobackend.entities.Role;
import tn.esprit.esprobackend.entities.user;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.esprobackend.repositories.userRepo;
import tn.esprit.esprobackend.security.JwtService;

import java.security.SecureRandom;
import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final userRepo repository;
    private final JwtService jwtService;
   // private final EmailService email;
private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
//default role
        var userRole = "USER";
        var userr = user.builder()
                .nameU(request.getNameU())
                .surnameU(request.getSurnameU())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.valueOf(userRole))
                .telnum(request.getTelnum())
                .img(request.getImg())
                .accountLocked(false)
                .enabled(true)
                .build();

        user savedUser = repository.save(userr);
        var claims = new HashMap<String,Object>();
        claims.put("nameU", savedUser.getNameU());

        String jwt = jwtService.generateToken(claims,savedUser);


        AuthenticationResponse authenticationResponse = new AuthenticationResponse(jwt, savedUser.getNameU(), savedUser.getSurnameU(), savedUser.getRole());

        return authenticationResponse;


     /*   user userr = new user();
        userr.setNameU(request.getNameU());
        userr.setSurnameU(request.getSurnameU());
        userr.setEmail(request.getEmail());
        userr.setPassword(passwordEncoder.encode(request.getPassword()));
        userr.setRole(Role.valueOf(userRole));
        userr.setTelnum(request.getTelnum());
        userr.setImg(request.getImg());
        userr.setAccountLocked(false);
        userr.setEnabled(false);
*/

    }

    /*
        private void sendValidationEmail(user u) {
            //var newToken = generateAndSaveActvationToken();
         le code qui sera envoyé
        var newToken = generateActivationCode(6);


    }

    private String generateAndSaveActvationToken() {
String generatedToken = generateActivationCode(6);
//String token = jwtService.generateToken();
return null;
    }

    private String generateActivationCode(int length) {

        String character = "0123456789";//elemnts qui seront generer le code
        StringBuilder codeBuilder =new StringBuilder();
        //randomvalue
        SecureRandom secureRandom = new SecureRandom();

        for (int i = 0; i<length;i++)
        {//nekhou index
            int randomIndex= secureRandom.nextInt(character.length());
           ///construire le code
            codeBuilder.append(character.charAt(randomIndex));
        }
        return codeBuilder.toString();


    }*/




    public AuthenticationResponse authenticate(AuthenticationRequest request)
    {
        try {
            var auth = authenticationManager.authenticate(

                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())

            );

            var claims = new HashMap<String, Object>();
            var user = (user) auth.getPrincipal();
            claims.put("nameU", user.getNameU());
            String jwt = jwtService.generateToken(claims, user);

            return new AuthenticationResponse(jwt,user.getNameU(),user.getSurnameU(),user.getRole());
        }


        catch(BadCredentialsException e){
            throw new BadCredentialsException("Incorrect username or password");
        }




    }
}
