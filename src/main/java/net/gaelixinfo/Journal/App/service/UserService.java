package net.gaelixinfo.Journal.App.service;

import lombok.extern.slf4j.Slf4j;
import net.gaelixinfo.Journal.App.entity.User;
import net.gaelixinfo.Journal.App.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class UserService {


    @Autowired
    private  UserRepo userRepo;


    private final static PasswordEncoder passwordEncoder=new BCryptPasswordEncoder();

    public void saveNewUser(User user) {
        try {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(List.of("USER"));
            userRepo.save(user);
        }catch (Exception e) {
            log.error("Error while saving  {} ",user.getUsername() + e);
        }

    }

    public void saveUser(User user) {
        try {
            userRepo.save(user);
        }catch (Exception e) {
          //  log.error("Error while saving JournalEntry", e);
            log.warn(e.getMessage());
        }

    }

    public List<User> getAll() {
        return userRepo.findAll();
    }

    public Optional<User> getUserById(ObjectId id) {
        return userRepo.findById(id);
    }

    public void deleteUserById(ObjectId id) {
         userRepo.deleteById(id);
    }

    public void deleteByUserName(String username) {
        userRepo.deleteByUsername(username);
    }

    public User findByUserName(String userName) {
        return userRepo.findByUsername(userName);
    }
}
