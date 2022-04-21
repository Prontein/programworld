package ru.geekbrains.programworld.core.exceptions;

import lombok.Data;

@Data
public class ArticleAlreadyExistException extends RuntimeException{
    private String messages;

    public ArticleAlreadyExistException(String messages) {
        this.messages = messages;
    }
}

