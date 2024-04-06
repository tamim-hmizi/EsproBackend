package tn.esprit.esprobackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprobackend.entities.user;
import tn.esprit.esprobackend.services.IUserService;
import java.util.*;

// http://localhost:8089/tpfoyer/chambre/retrieve-all-chambres
@RestController
@RequestMapping("/user")
@AllArgsConstructor
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class userRestController {
    IUserService userService;
    @GetMapping("/retrieve-all-users")
    public List<user> getUsers() {
        List<user> listChambres = userService.retrieveAllUsers();
        return listChambres;
    }

   /* public ResponseEntity<List<user>> getUsers() {
        List<user> liste=userService.retrieveAllUsers();
        if(liste.size()==0)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
        else
             return ResponseEntity.ok(liste);

    }*/



    @GetMapping("/retrieve-user/{user-id}")
    public user retrieveChambre(@PathVariable("user-id") Long chId) {
        user userr = userService.retrieveUser(chId);
        return userr;
    }


    @PostMapping("/add-user")
    public user addChambre(@RequestBody user u) {
        user userr = userService.addUser(u);
        return userr;
    }
    // http://localhost:8089/tpfoyer/chambre/remove-chambre/{chambre-id}
    @DeleteMapping("/remove-user/{user-id}")
    public void removeUser(@PathVariable("user-id") Long chId) {
        userService.removeUser(chId);
    }
    // http://localhost:8089/tpfoyer/chambre/modify-chambre

    @PutMapping("/modify-chambre/{user-id}")
    public user modifyChambre(@PathVariable("user-id") Long id,@RequestBody user c) {
        user userr = userService.modifyUser(id,c);
        return userr;
    }


    @PutMapping("/updateuser/{userId}")
    public ResponseEntity<user> updateUser(@PathVariable Long userId, @RequestBody user updatedUser) {

        updatedUser.setIdU(userId);
        user updatedUserResult = userService.modifyUser(userId,updatedUser);

        return ResponseEntity.ok(updatedUserResult);
    }





}
