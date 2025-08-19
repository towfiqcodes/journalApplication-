package net.gaelixinfo.Journal.App.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import net.gaelixinfo.Journal.App.entity.User;
import net.gaelixinfo.Journal.App.service.UserDetailsServiceImpl;
import net.gaelixinfo.Journal.App.service.UserService;
import net.gaelixinfo.Journal.App.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/public")
@Tag(name = "Public APIs")
public class PublicController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "OK";
    }


    @PostMapping("/signup")
    public void signup(@RequestBody User user) {
        userService.saveNewUser(user);
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        try {
           authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
           userDetailsService.loadUserByUsername(user.getUsername());
            String jwt = jwtUtil.generateToken(user.getUsername());
            return  new ResponseEntity<>(jwt, HttpStatus.OK);
        }catch (Exception e) {
          log.error("Exception occured while createAuthenticationToken ", e);
          return new ResponseEntity<>("Incorrect username or password", HttpStatus.UNAUTHORIZED);
        }

    }
}
