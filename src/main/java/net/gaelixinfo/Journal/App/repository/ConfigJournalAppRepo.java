package net.gaelixinfo.Journal.App.repository;

import net.gaelixinfo.Journal.App.entity.ConfigJournalApp;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigJournalAppRepo extends MongoRepository<ConfigJournalApp, ObjectId> {



}
