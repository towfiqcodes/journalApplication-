package net.gaelixinfo.Journal.App.controller;


import net.gaelixinfo.Journal.App.entity.User;
import net.gaelixinfo.Journal.App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return  userService.getAll();
    }


    @PostMapping
    public void createUser(@RequestBody User user) {
        userService.saveEntry(user);
    }

    @PutMapping
    public ResponseEntity<?> getUserById(@RequestParam User user) {
       User userInDb= userService.findByUserName(user.getUsername());
       if(userInDb!=null) {
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
            return ResponseEntity.ok(userInDb);
       }
       return ResponseEntity.notFound().build();
    }



}
