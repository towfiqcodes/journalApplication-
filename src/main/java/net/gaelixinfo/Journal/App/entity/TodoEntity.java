package net.gaelixinfo.Journal.App.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
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

    // Constructor with all fields (excluding id)
    public TodoEntity(String title, String description, LocalDateTime createDate) {
        this.title = title;
        this.description = description;
        this.createDate = createDate;
    }

    // Constructor with title and description (createDate will be set automatically)
    public TodoEntity(String title, String description) {
        this.title = title;
        this.description = description;
        this.createDate = LocalDateTime.now();
    }
}