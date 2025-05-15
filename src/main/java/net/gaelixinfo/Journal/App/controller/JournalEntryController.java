package net.gaelixinfo.Journal.App.controller;

import net.gaelixinfo.Journal.App.entity.JournalEntry;
import net.gaelixinfo.Journal.App.entity.User;
import net.gaelixinfo.Journal.App.service.JournalEntryService;
import net.gaelixinfo.Journal.App.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    @Autowired
 private  JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;




  @GetMapping()
  public ResponseEntity<?> getAllJournalEntriesOfUser() {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      User user = userService.findByUserName(authentication.getName());
      List<JournalEntry> all=user.getJournalEntries();
    if (all !=null && !all.isEmpty()) {
      return new ResponseEntity<>(all, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping()
    public ResponseEntity<JournalEntry> createJournalEntry(@RequestBody JournalEntry journalEntry) {
    try {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

//      journalEntry.setDate(LocalDateTime.now());
      journalEntryService.saveEntry(journalEntry,authentication.getName());
      return new  ResponseEntity<>(journalEntry, HttpStatus.CREATED);
    }catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }



    }


    @GetMapping("id/{myId}")
    public ResponseEntity<?> getJournalEntryById(@PathVariable ObjectId myId) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserName(authentication.getName());
        System.out.println("Authentication user name : " + authentication.getName());
        List<JournalEntry> journalEntries= user.getJournalEntries()
                .stream()
                .filter(journalEntry -> journalEntry.getId().equals(myId))
                .toList();

       if (!journalEntries.isEmpty()) {
           Optional<JournalEntry> journalEntry= journalEntryService.getJournalEntryById(myId) ;
           if (journalEntry.isPresent()) {
               return new ResponseEntity<>(journalEntry.get(), HttpStatus.OK);
           }
       }
       return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId myId) {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
      boolean removed= journalEntryService.deleteJournalEntryById(myId,authentication.getName());
      if (removed) {
          return new ResponseEntity<>(HttpStatus.OK);
      }else
          return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }

    @PutMapping("id/{id}")
    public ResponseEntity<?> updateJournalEntry(@PathVariable ObjectId id,
                                                @RequestBody JournalEntry newJournalEntry) {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findByUserName(authentication.getName());
        System.out.println("Authentication user name : " + authentication.getName());
        List<JournalEntry> journalEntries= user.getJournalEntries()
                .stream()
                .filter(journalEntry -> journalEntry.getId().equals(id))
                .toList();

        if (!journalEntries.isEmpty()) {
            Optional<JournalEntry> journalEntry= journalEntryService.getJournalEntryById(id) ;
            if (journalEntry.isPresent()) {
                JournalEntry oldJournalEntry = journalEntry.get();
                oldJournalEntry.setTitle(!newJournalEntry.getTitle().isEmpty()
                        ? newJournalEntry.getTitle()
                        : oldJournalEntry.getTitle()
                );

                oldJournalEntry.setContent(newJournalEntry.getContent()!=null && !newJournalEntry.getContent().isEmpty()
                        ? newJournalEntry.getContent()
                        : oldJournalEntry.getContent() );
                journalEntryService.saveEntry(oldJournalEntry);
                return new ResponseEntity<>(oldJournalEntry, HttpStatus.OK);
            }
        }

    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
