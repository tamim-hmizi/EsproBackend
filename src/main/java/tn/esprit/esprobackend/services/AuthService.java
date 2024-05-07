package tn.esprit.esprobackend.services;


import tn.esprit.esprobackend.Dto.SignupRequest;
import tn.esprit.esprobackend.entities.User;

public interface AuthService {

    User createCustomer(SignupRequest signupRequest);
}
