package com.bushansirgur.springbootmongodb.service;

import com.bushansirgur.springbootmongodb.exception.TodoCollectionException;
import com.bushansirgur.springbootmongodb.model.TodoDTO;

import java.util.List;

public interface TodoService {
    public void createTodo(TodoDTO todoDTO) throws TodoCollectionException;
    public List<TodoDTO> getAllTodo();
    public TodoDTO getSimpleTodo(String id) throws TodoCollectionException;
    public void updateTodo(String id, TodoDTO todoDTO) throws TodoCollectionException;
    public void deleteById(String id) throws TodoCollectionException;
}
