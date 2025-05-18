package net.gaelixinfo.Journal.App.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;


@Document(collection = "config_journal_app")
@Data
@NoArgsConstructor
public class ConfigJournalApp {
    private String key;
    private String value;


}
