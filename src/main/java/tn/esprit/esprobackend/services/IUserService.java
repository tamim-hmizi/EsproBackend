package tn.esprit.esprobackend.services;

import tn.esprit.esprobackend.entities.User;
import java.util.List;
public interface IUserService {
    public List<User> retrieveAllUsers();
    public User retrieveUser(Long UserId);
    public User addUser(User c);
    public void removeUser(Long UserId);
    public User modifyUser(User User);
}
