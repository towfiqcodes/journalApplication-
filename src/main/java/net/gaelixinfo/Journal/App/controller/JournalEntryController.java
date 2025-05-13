package net.gaelixinfo.Journal.App.controller;

import net.gaelixinfo.Journal.App.entity.JournalEntry;
import net.gaelixinfo.Journal.App.entity.User;
import net.gaelixinfo.Journal.App.service.JournalEntryService;
import net.gaelixinfo.Journal.App.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {
    @Autowired
 private  JournalEntryService journalEntryService;

    @Autowired
    private UserService userService;




  @GetMapping("{username}")
  public ResponseEntity<?> getAllJournalEntriesOfUser(@PathVariable String username) {
      User user = userService.findByUserName(username);
      List<JournalEntry> all=user.getJournalEntries();
    if (all !=null && !all.isEmpty()) {
      return new ResponseEntity<>(all, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping("{username}")
    public ResponseEntity<JournalEntry> createJournalEntry(@RequestBody JournalEntry journalEntry,  @PathVariable String username) {
    try {

//      journalEntry.setDate(LocalDateTime.now());
      journalEntryService.saveEntry(journalEntry,username);
      return new  ResponseEntity<>(journalEntry, HttpStatus.CREATED);
    }catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }



    }


    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
      Optional<JournalEntry> journalEntry= journalEntryService.getJournalEntryById(myId) ;
        return journalEntry.map(entry -> new ResponseEntity<>(entry, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("id/{username}/{myId}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId myId,  @PathVariable String username) {
       journalEntryService.deleteJournalEntryById(myId,username);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{username}/{id}")
    public ResponseEntity<?> updateJournalEntry(@PathVariable ObjectId id,
                                                @RequestBody JournalEntry newJournalEntry,
                                                @PathVariable String username) {
     JournalEntry oldJournalEntry = journalEntryService.getJournalEntryById(id).orElse(null);
     if (oldJournalEntry != null) {
       oldJournalEntry.setTitle(!newJournalEntry.getTitle().isEmpty()
               ? newJournalEntry.getTitle()
               : oldJournalEntry.getTitle()
       );

       oldJournalEntry.setContent(newJournalEntry.getContent()!=null && !newJournalEntry.getContent().isEmpty()
               ? newJournalEntry.getContent()
               : oldJournalEntry.getContent() );
       journalEntryService.saveEntry(oldJournalEntry);
     return new ResponseEntity<>(HttpStatus.OK);
     }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
