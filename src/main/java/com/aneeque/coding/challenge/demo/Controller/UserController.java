package com.aneeque.coding.challenge.demo.Controller;

import com.aneeque.coding.challenge.demo.Dto.UserLoginDto;
import com.aneeque.coding.challenge.demo.Dto.UserSignUpReqDto;
import com.aneeque.coding.challenge.demo.Service.UserService;
import com.aneeque.coding.challenge.demo.Util.Api.Response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody UserSignUpReqDto request) {

        final ApiResponse API_RESPONSE = userService.createUser(request);

        return new ResponseEntity<>(API_RESPONSE, HttpStatus.CREATED);
    }

    @PostMapping("/user/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody UserLoginDto request) {

        final ApiResponse API_RESPONSE = userService.login(request);

        return new ResponseEntity<>(API_RESPONSE, HttpStatus.CREATED);
    }
}
