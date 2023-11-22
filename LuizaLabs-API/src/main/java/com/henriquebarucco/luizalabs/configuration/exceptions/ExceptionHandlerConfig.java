package com.henriquebarucco.luizalabs.configuration.exceptions;

import com.henriquebarucco.luizalabs.core.exceptions.FileProcessException;
import com.henriquebarucco.luizalabs.core.exceptions.FileReaderException;
import com.henriquebarucco.luizalabs.core.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ExceptionHandlerConfig {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> notFound(ResourceNotFoundException e, HttpServletRequest request) {
        String error = "Resource not found";
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                error,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(FileProcessException.class)
    public ResponseEntity<StandardError> fileProcess(FileProcessException e, HttpServletRequest request) {
        String error = "File Process Error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                error + ": " + e.getMessage(),
                e.getCause().getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(FileReaderException.class)
    public ResponseEntity<StandardError> fileReader(FileReaderException e, HttpServletRequest request) {
        String error = "File Reader Error";
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError(
                Instant.now(),
                status.value(),
                error + ": " + e.getMessage(),
                e.getCause().getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(err);
    }
}
