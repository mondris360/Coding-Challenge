package com.aneeque.coding.challenge.demo.Util.Api.Response;

import lombok.*;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ApiResponse implements Serializable {
    private boolean status;
    private String message;
    private String jwtToken;
    private Object data;


    public ApiResponse(boolean status, HttpStatus httpStatus, String message) {
        this.status = status;
        this.message = message;
    }

}
