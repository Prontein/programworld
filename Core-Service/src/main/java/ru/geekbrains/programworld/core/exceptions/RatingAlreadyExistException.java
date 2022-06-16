package ru.geekbrains.programworld.core.exceptions;

import lombok.Data;

@Data
public class RatingAlreadyExistException extends RuntimeException{
    private String messages;

    public RatingAlreadyExistException(String messages) {
        this.messages = messages;
    }

}
