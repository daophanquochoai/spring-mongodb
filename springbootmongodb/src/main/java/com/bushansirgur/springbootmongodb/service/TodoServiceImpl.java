package com.bushansirgur.springbootmongodb.service;

import com.bushansirgur.springbootmongodb.exception.TodoCollectionException;
import com.bushansirgur.springbootmongodb.model.TodoDTO;
import com.bushansirgur.springbootmongodb.repository.TodoRepository;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TodoServiceImpl implements TodoService{
    @Autowired
    private TodoRepository todoRepo;
    @Override
    public void createTodo(TodoDTO todoDTO) throws ConstraintViolationException,TodoCollectionException {
        Optional<TodoDTO> todo = todoRepo.findByTodo(todoDTO.getTodo());
        if(todo.isPresent()){
            throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExits());
        }else{
            todoDTO.setCreateAt(new Date(System.currentTimeMillis()));
            todoRepo.save(todoDTO);
        }
    }

    @Override
    public List<TodoDTO> getAllTodo() {
        List<TodoDTO> todo = todoRepo.findAll();
        if(todo.size() > 0){
            return todo;
        }else{
            return new ArrayList<TodoDTO>();
        }
    }

    @Override
    public TodoDTO getSimpleTodo(String id) throws TodoCollectionException {
        Optional<TodoDTO> todo = todoRepo.findById(id);
        if(todo.isPresent()){
            return todo.get();
        }else{
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }
    }

    @Override
    public void updateTodo(String id, TodoDTO todoDTO) throws TodoCollectionException{
        Optional<TodoDTO> todo = todoRepo.findById(id);
        Optional<TodoDTO> todoWithSameName = todoRepo.findByTodo(todoDTO.getTodo());
        if(todo.isPresent()){
            if(todoWithSameName.isPresent() && todoWithSameName.get().getId().equals(id)){
                throw new TodoCollectionException(TodoCollectionException.TodoAlreadyExits());
            }

            TodoDTO todoSave = todo.get();
            todoSave.setTodo(todoDTO.getTodo());
            todoSave.setDescription(todoDTO.getDescription());
            todoSave.setCompled(todoDTO.getCompled());
            todoSave.setUpdateAt(new Date(System.currentTimeMillis()));
            todoRepo.save(todoSave);
        }else{
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }
    }

    @Override
    public void deleteById(String id) throws TodoCollectionException {
        Optional<TodoDTO> todo = todoRepo.findById(id);
        if(todo.isPresent()){
            todoRepo.deleteById(id);
        }else{
            throw new TodoCollectionException(TodoCollectionException.NotFoundException(id));
        }
    }
}
