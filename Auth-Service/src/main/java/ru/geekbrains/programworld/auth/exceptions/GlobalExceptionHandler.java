package ru.geekbrains.programworld.auth.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.geekbrains.programworld.api.exceptions.SiteError;
import ru.geekbrains.programworld.api.exceptions.ResourceNotFoundException;

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
    public ResponseEntity<?> catchUserAlreadyExistException(UserAlreadyExistException e) {
        return new ResponseEntity<>(new UserAlreadyExistException(e.getMessages()), HttpStatus.BAD_REQUEST);
    }

}
