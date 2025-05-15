package net.gaelixinfo.Journal.App.controller;

import net.gaelixinfo.Journal.App.entity.User;
import net.gaelixinfo.Journal.App.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {
    @Autowired
    private UserService userService;


    @GetMapping("/health-check")
    public String healthCheck() {
        return "OK";
    }


    @PostMapping("/create-user")
    public void createUser(@RequestBody User user) {
        userService.saveNewUser(user);
    }

}
