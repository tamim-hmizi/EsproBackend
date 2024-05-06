package tn.esprit.esprobackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprobackend.entities.User;
import tn.esprit.esprobackend.services.IUserService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/user")
public class UserRestController {
    IUserService userService;
    // http://localhost:8089/esprobackend/user/retrieve-all-users
    @GetMapping("/retrieve-all-users")
    public List<User> getUsers() {
        List<User> listUsers = userService.retrieveAllUsers();
        return listUsers;
    }
    // http://localhost:8089/esproeackend/user/retrieve-user/8
    @GetMapping("/retrieve-user/{user-id}")
    public User retrieveUser(@PathVariable("user-id") Long chId) {
        User user = userService.retrieveUser(chId);
        return user;
    }
    // http://localhost:8089/esprobackend/user/add-user
    @PostMapping("/add-user")
    public User addUser(@RequestBody User c) {
        User user = userService.addUser(c);
        return user;
    }
    // http://localhost:8089/esprobackend/user/remove-user/{user-id}
    @DeleteMapping("/remove-user/{user-id}")
    public void removeUser(@PathVariable("user-id") Long chId) {
        userService.removeUser(chId);
    }
    // http://localhost:8089/esprobackend/user/modify-user
    @PutMapping("/modify-user")
    public User modifyUser(@RequestBody User c) {
        User user = userService.modifyUser(c);
        return user;
    }
}

