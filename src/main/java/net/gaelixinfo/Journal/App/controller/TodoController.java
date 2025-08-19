package net.gaelixinfo.Journal.App.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import net.gaelixinfo.Journal.App.entity.TodoEntity;
import net.gaelixinfo.Journal.App.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController()
@RequestMapping("/todo")
@CrossOrigin(origins = "http://localhost:3000")
@Tag(name = "TODO APIs")
public class TodoController {

    @Autowired
    TodoService todoService;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "OK";
    }



    @PostMapping
   public ResponseEntity<?> createTodo(@RequestBody TodoEntity todoEntity){
        todoEntity.setId(UUID.randomUUID().toString());
        todoEntity.setCreateDate(LocalDateTime.now());
      return   todoService.save(todoEntity);
   }


   @GetMapping
   public ResponseEntity<?> getAllTodo(){
        return todoService.getAllTodo();
   }


   @PutMapping("id/{id}")
   public ResponseEntity<?> updateTodo(@PathVariable String id, @RequestBody TodoEntity todoEntity){
     return todoService.updateTodo(id, todoEntity);
   }


   @DeleteMapping("id/{id}")
    public ResponseEntity<?> deleteTodo(@PathVariable String id){
        return todoService.deleteTodo(id);
   }




}
