package ru.geekbrains.programworld.core.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.geekbrains.programworld.api.exceptions.ResourceNotFoundException;
import ru.geekbrains.programworld.api.exceptions.SiteError;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler
    public ResponseEntity<?> catchResourceNotFoundException(ResourceNotFoundException e) {
        return new ResponseEntity<>(new SiteError(e.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<?> catchDataValidationException(DataValidationException e) {
        return new ResponseEntity<>(new SiteError(e.getMessages()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> catchArticleAlreadyExistException(ArticleAlreadyExistException e) {
        return new ResponseEntity<>(new ArticleAlreadyExistException(e.getMessages()), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    public ResponseEntity<?> catchRatingAlreadyExistException(RatingAlreadyExistException e) {
        return new ResponseEntity<>(new RatingAlreadyExistException(e.getMessages()), HttpStatus.BAD_REQUEST);
    }
}
