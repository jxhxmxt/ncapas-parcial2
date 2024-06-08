package com.uca.clinic.exception;

import com.uca.clinic.responses.GeneralResponse;
import com.uca.clinic.utils.ErrorMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice // this annotation is used to handle exceptions globally
@Slf4j
public class GlobalErrorHandler {

    private final ErrorMapper errorMapper;

    @Autowired
    public GlobalErrorHandler(ErrorMapper errorMapper) {
        this.errorMapper = errorMapper;
    }
    @ExceptionHandler(NoResourceFoundException.class)
    public ResponseEntity<GeneralResponse> notFoundExceptionHandler(NoResourceFoundException e){
        return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }


//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<GeneralResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {
//        String errorMessage = ex.getBindingResult().getFieldError().getDefaultMessage();
//        return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, errorMessage);
//    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<GeneralResponse> handleUserNotFoundException(UserNotFoundException e){
        return GeneralResponse.getResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GeneralResponse> generalExceptionHandler(Exception e){
        log.error(e.getMessage());
        log.error(e.getClass().toGenericString());
        // the stacktrace could also be printed if needed
        return GeneralResponse.getResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    private ResponseEntity<GeneralResponse> validationExceptionHandler(MethodArgumentNotValidException e){
        log.error("ERROR MAPPER>>>>>>>>>>>>>>>", e);
        return GeneralResponse.getResponse(HttpStatus.BAD_REQUEST, errorMapper.map(e.getBindingResult().getFieldErrors()));
    }





}