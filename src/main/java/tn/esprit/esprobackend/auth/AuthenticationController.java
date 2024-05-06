package tn.esprit.esprobackend.auth;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.esprobackend.entities.user;
import tn.esprit.esprobackend.repositories.userRepo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
//import tn.esprit.esprobackend.config.RegisterRequest;
//import com.helloIftekhar.springJwt.service.AuthenticationService;
import org.springframework.context.support.DefaultMessageSourceResolvable;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor

public class AuthenticationController {
  private final  AuthenticationService authService;
userRepo userRepository;

/*
    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody @Valid RegisterRequest request
    ) {
        return ResponseEntity.ok(authService.register(request));

    }*/




    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid RegisterRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Si des erreurs de validation sont présentes, construisez une réponse appropriée avec les messages d'erreur
            List<String> errors = bindingResult.getAllErrors().stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.toList());
            return ResponseEntity.badRequest().body(errors);
        }

        // Si les données sont valides, procédez à l'enregistrement de l'utilisateur
        AuthenticationResponse response = authService.register(request);
        return ResponseEntity.ok(response);
    }





    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody @Valid AuthenticationRequest request
    ) {


        return ResponseEntity.ok(authService.authenticate(request));
      //si email ou pawd sont incorrects


    }


  /*@PostMapping("/retrive-Emailuser")
  public Optional<user> retrieveByMail(String email){
    return userRepository.findByEmail(email);
  }
*/
}
