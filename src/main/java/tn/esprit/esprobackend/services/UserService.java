package tn.esprit.esprobackend.services;

import tn.esprit.esprobackend.entities.User;

import java.util.List;

public interface UserService {

    public List<User> retrieveAllUser();
    public User addUser(User user);
    public User updateUser(User user);
    void removeUser(Long idUsers);

    User getUserById(Long id);

    User getUserByEmail(String email);

}
