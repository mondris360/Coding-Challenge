package com.aneeque.coding.challenge.demo.Service.Impl;

import com.aneeque.coding.challenge.demo.Dto.UserSignUpReqDto;
import com.aneeque.coding.challenge.demo.Repository.UserRepository;
import com.aneeque.coding.challenge.demo.Service.UserService;
import com.aneeque.coding.challenge.demo.Util.Api.Response.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ApiResponse createUser(UserSignUpReqDto request) {
        // user input has already been validated at dto level. Please check UserSignUpReqDto


        return null;
    }

    @Override
    public ApiResponse login(UserSignUpReqDto request) {
        return null;
    }

    @Override
    public ApiResponse getAllUsers() {
        return null;
    }
}
