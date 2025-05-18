package net.gaelixinfo.Journal.App.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "todos")
@Data
@NoArgsConstructor
public class TodoEntity {
    @Id
    private String id;
    private String title;
    private String description;
    private LocalDateTime createDate;
}
