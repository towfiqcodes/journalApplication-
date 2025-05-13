package net.gaelixinfo.Journal.App.service;

import lombok.extern.slf4j.Slf4j;
import net.gaelixinfo.Journal.App.entity.JournalEntry;
import net.gaelixinfo.Journal.App.entity.User;
import net.gaelixinfo.Journal.App.repository.JournalEntryRepo;
import net.gaelixinfo.Journal.App.repository.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Component
public class UserService {


    @Autowired
    private  UserRepo userRepo;


    public void saveEntry(User user) {
        try {
            userRepo.save(user);
        }catch (Exception e) {
            log.error("Error while saving JournalEntry", e);
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

    public User findByUserName(String userName) {
        return userRepo.findByUsername(userName);
    }
}
