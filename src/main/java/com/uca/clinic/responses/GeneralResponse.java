package com.uca.clinic.responses;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import lombok.Data;

@Data
public class GeneralResponse {
    private String message;
    private Object data;

    public GeneralResponse(String message, Object data) {
        this.message = message;
        this.data = data;
    }

    public static ResponseEntity<GeneralResponse> getResponse(HttpStatus status, String message, Object data){
        GeneralResponse generalResponse = new GeneralResponse(message, data);
        return new ResponseEntity<>(generalResponse, status);
    }

    public static ResponseEntity<GeneralResponse> getResponse(HttpStatus status, String message){
        return getResponse(status, message, null);
    }

    public static ResponseEntity<GeneralResponse> getResponse(HttpStatus status, Object data){
        return getResponse(status, null, data);
    }
}
