package tn.esprit.esprobackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.esprobackend.entities.User;
import tn.esprit.esprobackend.repositories.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService {
    UserRepository userRepository;
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }
    public User retrieveUser(Long id) {
        return userRepository.findById(id).get();
    }
    public User addUser(User c) {
        return userRepository.save(c);
    }
    public void removeUser(Long id) {
        userRepository.deleteById(id);
    }
    public User modifyUser(User bloc) {
        return userRepository.save(bloc);
    }
}

