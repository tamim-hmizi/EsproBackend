package tn.esprit.esprobackend.services;
import tn.esprit.esprobackend.entities.user;
import java.util.*;


public interface IUserService {
    public List<user> retrieveAllUsers();
    public user retrieveUser(Long userId);
    public user addUser(user u);
    public void removeUser(Long userId);
    public user modifyUser(Long id,user u);

}
