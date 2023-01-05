package com.example.emailsender.handlers;

import com.example.emailsender.exceptions.RequestException;
import com.example.emailsender.responses.CustomErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;

@ControllerAdvice
public class Handlers {

    @ExceptionHandler(RequestException.class)
    public ResponseEntity<CustomErrorResponse> requestHandler(
            RequestException exception,
            ServletWebRequest request
    ) {
        CustomErrorResponse response = new CustomErrorResponse(
                HttpStatus.BAD_REQUEST,
                exception.getMessage(),
                request.getRequest().getRequestURI()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomErrorResponse> validHandler(
            MethodArgumentNotValidException exception,
            ServletWebRequest request
    ) {
        BindingResult result = exception.getBindingResult();
        CustomErrorResponse response = new CustomErrorResponse(
                HttpStatus.BAD_REQUEST,
                result.getFieldError().getDefaultMessage(),
                request.getRequest().getRequestURI()
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
