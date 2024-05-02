package com.spring.task2.exception;

public class GroupExistsException extends RuntimeException{
    public GroupExistsException(String message) {
        super(message);
    }
}
