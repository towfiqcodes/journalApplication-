package net.gaelixinfo.Journal.App.service;


import lombok.extern.slf4j.Slf4j;
import net.gaelixinfo.Journal.App.entity.JournalEntry;
import net.gaelixinfo.Journal.App.entity.TodoEntity;
import net.gaelixinfo.Journal.App.entity.User;
import net.gaelixinfo.Journal.App.repository.TodoRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class TodoService {

    @Autowired
    private TodoRepository todoRepository;


    @Transactional
    public ResponseEntity<?> save(TodoEntity todoEntity) {
       try{
           TodoEntity save = todoRepository.save(todoEntity);
           return new ResponseEntity<>(save,HttpStatus.CREATED);
       } catch (Exception e){
           return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
       }

    }


    public ResponseEntity<?> getAllTodo() {
        List<TodoEntity> all=todoRepository.findAll();
        if (all !=null && !all.isEmpty()) {
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<?> updateTodo(String id,  TodoEntity todoEntity) {
     TodoEntity update = todoRepository.findById(id).orElse(null);
     if (update != null) {
         update.setTitle(todoEntity.getTitle());
         update.setDescription(todoEntity.getDescription());
         todoRepository.save(update);
         return new ResponseEntity<>(update,HttpStatus.OK);
     }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @Transactional
    public ResponseEntity<?> deleteTodo(String id) {

        boolean remove= todoRepository.findById(id).isPresent();
          if (remove) {
              todoRepository.deleteById(id);
              return new ResponseEntity<>(remove,HttpStatus.OK);
          }
      return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
