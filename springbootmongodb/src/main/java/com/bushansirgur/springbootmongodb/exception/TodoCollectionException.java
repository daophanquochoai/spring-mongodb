package com.bushansirgur.springbootmongodb.exception;

public class TodoCollectionException extends Exception{
    private static final long serialVersionUID = 1;
    public TodoCollectionException(String message){
        super(message);
    }
    public static String NotFoundException(String id){
        return "Todo with " + id + " not found";
    }
    public static String TodoAlreadyExits(){
        return "Todo with given name already exits";
    }
}
