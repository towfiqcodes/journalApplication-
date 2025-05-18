package net.gaelixinfo.Journal.App.repository;


import net.gaelixinfo.Journal.App.entity.TodoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoRepository extends MongoRepository<TodoEntity,String> {
}
