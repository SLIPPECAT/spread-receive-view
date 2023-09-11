package com.example.spread.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {
    @ExceptionHandler(SpreadException.class)
    public ResponseEntity<String> handleSpreadException(SpreadException e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(e.getMessage(), httpStatus);
    }

    @ExceptionHandler(ReceiveException.class)
    public ResponseEntity<String> handleReceiveException(ReceiveException e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(e.getMessage(), httpStatus);
    }

    @ExceptionHandler(ViewException.class)
    public ResponseEntity<String> handleViewException(ViewException e){
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        return new ResponseEntity<>(e.getMessage(), httpStatus);
    }

}
