package net.gaelixinfo.Journal.App.controller;

import net.gaelixinfo.Journal.App.entity.JournalEntry;
import net.gaelixinfo.Journal.App.service.JournalEntryService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Key;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/journal")
public class JournalEntryControllerV2 {

  JournalEntryService journalEntryService;
  @Autowired
  public JournalEntryControllerV2(JournalEntryService journalEntryService) {
    this.journalEntryService = journalEntryService;
  }


  @GetMapping()
  public ResponseEntity<?> getAll() {
    List<JournalEntry> all=journalEntryService.getAllEntries();
    if (all !=null && !all.isEmpty()) {
      return new ResponseEntity<>(all, HttpStatus.OK);
    }
    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @PostMapping()
    public ResponseEntity<JournalEntry> createJournalEntry(@RequestBody JournalEntry journalEntry) {
    try {
      journalEntry.setDate(LocalDateTime.now());
      journalEntryService.saveEntry(journalEntry);
      return new  ResponseEntity<>(journalEntry, HttpStatus.CREATED);
    }catch (Exception e) {
      return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }



    }


    @GetMapping("id/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable ObjectId myId) {
      Optional<JournalEntry> journalEntry= journalEntryService.getJournalEntryById(myId) ;
      if (journalEntry.isPresent()) {
        return new ResponseEntity<>(journalEntry.get(),HttpStatus.OK);
      }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("id/{myId}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable ObjectId myId) {
       journalEntryService.deleteJournalEntryById(myId);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("id/{id}")
    public ResponseEntity<?> updateJournalEntry(@PathVariable ObjectId id, @RequestBody JournalEntry newJournalEntry) {
     JournalEntry oldJournalEntry = journalEntryService.getJournalEntryById(id).orElse(null);
     if (oldJournalEntry != null) {
       oldJournalEntry.setTitle( newJournalEntry.getTitle()!=null && !newJournalEntry.getTitle().isEmpty()
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
