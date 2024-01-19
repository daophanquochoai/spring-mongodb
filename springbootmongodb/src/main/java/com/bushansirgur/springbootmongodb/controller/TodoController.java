package com.bushansirgur.springbootmongodb.controller;

import com.bushansirgur.springbootmongodb.exception.TodoCollectionException;
import com.bushansirgur.springbootmongodb.model.TodoDTO;
import com.bushansirgur.springbootmongodb.repository.TodoRepository;
import com.bushansirgur.springbootmongodb.service.TodoService;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
public class TodoController {
    @Autowired
    private TodoRepository todoRepo;
    @Autowired
    private TodoService todoService;

    @GetMapping("/todos")
    public ResponseEntity<?> getAllTodos(){
        List<TodoDTO> list = todoService.getAllTodo();
        return new ResponseEntity<>(list, list.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }
    @PostMapping("/create")
    public ResponseEntity<?> createTodo(@RequestBody TodoDTO todoDTO){
        try{
            todoService.createTodo(todoDTO);
            return new ResponseEntity<>(todoDTO, HttpStatus.OK);
        }catch (ConstraintViolationException e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }catch (TodoCollectionException t){
            return new ResponseEntity<>(t.getMessage(), HttpStatus.CONFLICT);
        }
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<?> getSingleTodo(@PathVariable("id") String id){
        try{
            return new ResponseEntity<>(todoService.getSimpleTodo(id), HttpStatus.OK);
        }catch (TodoCollectionException t){
            return new ResponseEntity<>(t.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateTodo(@PathVariable("id") String id, @RequestBody TodoDTO todoDTO){
        try{
            todoService.updateTodo(id, todoDTO);
            return new ResponseEntity<>("Update Todo with id " + id, HttpStatus.OK);
        }catch (ConstraintViolationException c){
            return new ResponseEntity<>(c.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
        }catch (TodoCollectionException t){
            return new ResponseEntity<>(t.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable String id){
        try{
            todoService.deleteById(id);
            return new ResponseEntity<>("Da xoa!!", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>("Khong the xoa ID " + id, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
