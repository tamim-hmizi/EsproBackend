package tn.esprit.esprobackend.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import tn.esprit.esprobackend.Dto.SignupRequest;
import tn.esprit.esprobackend.entities.User;
import tn.esprit.esprobackend.repositories.UserRepository;

@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;




    @Override
    public User createCustomer(SignupRequest signupRequest) {
       
        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return null;
        }

        User user = new User();
        BeanUtils.copyProperties(signupRequest,user);


        String hashPassword = passwordEncoder.encode(signupRequest.getPassword());
        user.setPassword(hashPassword);
        User createdCustomer = userRepository.save(user);
        user.setId(createdCustomer.getId());
        return user;
    }
}
