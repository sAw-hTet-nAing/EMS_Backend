package com.saw.emsbackend.exception;

import com.saw.emsbackend.response.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice()
public class RestExceptionHandler {


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Object> handleValidationException(MethodArgumentNotValidException ex) {
        LocalDateTime currentTimestamp = LocalDateTime.now();
        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .collect(Collectors.toList());
        ErrorResponse response = new ErrorResponse(400,
                String.join(", ", errors),
                currentTimestamp.toString());

        return buildResponseEntity(response);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstrainViolationException(ConstraintViolationException ex) {

        LocalDateTime currentTimestamp = LocalDateTime.now();
        ErrorResponse response = new ErrorResponse(400,
                ex.getMessage(),
                currentTimestamp.toString());

        return buildResponseEntity(response);
    }


    @ExceptionHandler(SQLException.class)
    public ResponseEntity<Object> handleSqlException(SQLException ex) {

        LocalDateTime currentTimestamp = LocalDateTime.now();
        ErrorResponse response = new ErrorResponse(400,
                ex.getMessage(),
                currentTimestamp.toString());
        return buildResponseEntity(response);
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex) {

        LocalDateTime currentTimestamp = LocalDateTime.now();
        ErrorResponse response = new ErrorResponse(404,
                ex.getMessage(),
                currentTimestamp.toString());

        return buildResponseEntity(response);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex) {

        LocalDateTime currentTimestamp = LocalDateTime.now();
        ErrorResponse response = new ErrorResponse(400,
                ex.getMessage(),
                currentTimestamp.toString());

        return buildResponseEntity(response);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Object> handleHttpMessageNotReadableException(HttpMessageNotReadableException ex) {

        LocalDateTime currentTimestamp = LocalDateTime.now();
        ErrorResponse response = new ErrorResponse(400,
                ex.getMessage(),
                currentTimestamp.toString());

        return buildResponseEntity(response);
    }


    private ResponseEntity<Object> buildResponseEntity(ErrorResponse errorResponse) {
        return ResponseEntity.status(errorResponse.getCode()).body(errorResponse);
    }

}
