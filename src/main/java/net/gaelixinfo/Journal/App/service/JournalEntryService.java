package net.gaelixinfo.Journal.App.service;

import net.gaelixinfo.Journal.App.entity.JournalEntry;
import net.gaelixinfo.Journal.App.repository.JournalEntryRepo;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    private final JournalEntryRepo journalEntryRepo;

    JournalEntryService(JournalEntryRepo journalEntryRepo) {
        this.journalEntryRepo = journalEntryRepo;
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

    public void deleteJournalEntryById(ObjectId id) {
         journalEntryRepo.deleteById(id);
    }
}
