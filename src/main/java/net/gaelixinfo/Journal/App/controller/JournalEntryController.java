package net.gaelixinfo.Journal.App.controller;

import net.gaelixinfo.Journal.App.entity.JournalEntry;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/journal")
public class JournalEntryController {
  Map<Long, JournalEntry> journalEntries = new HashMap<>();

  @GetMapping()
  public List<JournalEntry> getAll() {
        return new ArrayList<>(journalEntries.values());
    }


    @PostMapping()
    public boolean createJournalEntry(@RequestBody JournalEntry journalEntry) {
      journalEntries.put(journalEntry.getId(), journalEntry);
      return true;
    }


    @GetMapping("id/{myId}")
    public JournalEntry getJournalEntryById(@PathVariable long myId) {
      return journalEntries.get(myId);
    }

    @DeleteMapping("id/{myId}")
    public JournalEntry deleteJournalEntry(@PathVariable long myId) {
      return journalEntries.remove(myId);
    }

    @PutMapping("id/{id}")
    public JournalEntry updateJournalEntry(@PathVariable long id, @RequestBody JournalEntry journalEntry) {
      return journalEntries.put(id, journalEntry);
    }

}
