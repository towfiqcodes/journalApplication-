package net.gaelixinfo.Journal.App.entity

import lombok.Data
import lombok.NoArgsConstructor
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDateTime

@Document(collection = "todos")
@Data
@NoArgsConstructor
class TodoEntity {
    @Id
    private val id: String? = null
    private val title: String? = null
    private val description: String? = null
    private val createDate: LocalDateTime? = null
}
