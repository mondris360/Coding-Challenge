package com.aneeque.coding.challenge.demo.Controller;

import com.aneeque.coding.challenge.demo.Dto.UserLoginDto;
import com.aneeque.coding.challenge.demo.Dto.UserSignUpReqDto;
import com.aneeque.coding.challenge.demo.Service.UserService;
import com.aneeque.coding.challenge.demo.Util.Api.Response.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<ApiResponse> createUser(@Valid @RequestBody UserSignUpReqDto request) {

        final ApiResponse apiResponse = userService.createUser(request);

        return new ResponseEntity<>(apiResponse, HttpStatus.CREATED);
    }

    @GetMapping("/user")
    public ResponseEntity<ApiResponse> getAllUsers(@RequestParam(name = "page", defaultValue = "0") int page,
                                                   @RequestParam(name = "size", defaultValue = "20") int size,
                                                   @RequestParam(name = "sortByField", defaultValue = "createdAt") String sortByField) {

        final ApiResponse apiResponse = userService.getAllUsers(page, size, sortByField);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PostMapping("/user/login")
    public ResponseEntity<ApiResponse> login(@Valid @RequestBody UserLoginDto request) {

        final ApiResponse apiResponse = userService.login(request);

        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }



}
