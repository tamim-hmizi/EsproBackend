package tn.esprit.esprobackend.services;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.esprit.esprobackend.entities.User;
import tn.esprit.esprobackend.repositories.UserRepository;

import java.util.List;
@Service
@AllArgsConstructor
public class UserServiceIm implements  UserService{

    UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public List<User> retrieveAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User addUser(User user) {
        String hashPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        return userRepository.save(user);
    }

    @Override
    public User updateUser(User user) {
        String hashPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashPassword);
        return userRepository.save(user);
    }

    @Override
    public void removeUser(Long idUsers) {

        userRepository.deleteById(idUsers);

    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User getUserByEmail(String email) {
        return userRepository.getRolFromUser(email);
    }
}
