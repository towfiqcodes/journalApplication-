package net.gaelixinfo.Journal.App.controller;


import io.swagger.v3.oas.annotations.tags.Tag;
import net.gaelixinfo.Journal.App.api.response.WeatherResponse;
import net.gaelixinfo.Journal.App.entity.User;
import net.gaelixinfo.Journal.App.service.UserService;
import net.gaelixinfo.Journal.App.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Tag(name = "User APIs", description = "Read , Update & Delete User")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WeatherService weatherService;




//    @PutMapping()
//    public ResponseEntity<?> updateUser(@RequestParam User user) {
//         Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//         String username = authentication.getName();
//        System.out.println(username);
//       User userInDb= userService.findByUserName(username);
//       userInDb.setUsername(user.getUsername());
//       userInDb.setPassword(user.getPassword());
//       userService.saveEntry(userInDb);
//
//       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//    }


    @PutMapping()
    public ResponseEntity<?> updateUser(@RequestBody User request) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.findByUserName(username);
        if (user == null) return ResponseEntity.status(404).body("User not found");
        user.setUsername(request.getUsername());
        user.setPassword(request.getPassword());
        userService.saveNewUser(user);

        return ResponseEntity.noContent().build();
    }


    @DeleteMapping()
    public ResponseEntity<?> deleteUserByUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userService.deleteByUserName(authentication.getName());
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<?> getUserByUserName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getWeather("Dhaka");
        String greetings="";
        if (weatherResponse!=null) {
           greetings= "Weather feels like " + weatherResponse.getCurrent().getFeelsLike();
        }
        return ResponseEntity.ok("HI "+authentication.getName() + greetings);
    }

}
