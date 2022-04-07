package ru.geekbrains.programworld.auth.exceptions;

import lombok.Data;

@Data
public class UserAlreadyExistException extends RuntimeException{
    private String messages;

    public UserAlreadyExistException(String messages) {
        this.messages = messages;
    }
}

