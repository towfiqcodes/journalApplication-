package net.gaelixinfo.Journal.App.service;

import net.gaelixinfo.Journal.App.entity.JournalEntry;
import net.gaelixinfo.Journal.App.entity.User;
import net.gaelixinfo.Journal.App.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {



    @Autowired
    private  JournalEntryRepo journalEntryRepo;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String username) {
        try {
            User user = userService.findByUserName(username);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry save = journalEntryRepo.save(journalEntry);
            user.getJournalEntries().add(save);
            userService.saveEntry(user);
        }catch(Exception e) {
            System.out.println("Error saving entry: " + e);
            throw new RuntimeException("An Error occurred while saving the entry ", e);
        }

    }

    public void saveEntry(JournalEntry journalEntry) {
         journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> getAllEntries() {
        return journalEntryRepo.findAll();
    }

    public Optional<JournalEntry> getJournalEntryById(ObjectId id) {
        return journalEntryRepo.findById(id);
    }

    public void deleteJournalEntryById(ObjectId id, String username) {
          User user = userService.findByUserName(username);
          user.getJournalEntries().removeIf(x -> x.getId().equals(id));
          userService.saveEntry(user);
         journalEntryRepo.deleteById(id);
    }
}
