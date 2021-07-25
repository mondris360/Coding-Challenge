package com.aneeque.coding.challenge.demo.Service.Impl;

import com.aneeque.coding.challenge.demo.Dto.UserSignUpReqDto;
import com.aneeque.coding.challenge.demo.Model.User;
import com.aneeque.coding.challenge.demo.Repository.UserRepository;
import com.aneeque.coding.challenge.demo.Service.UserService;
import com.aneeque.coding.challenge.demo.Util.Api.Exception.CustomErrorClass.IllegalArgumentException;
import com.aneeque.coding.challenge.demo.Util.Api.Response.ApiResponse;

import org.apache.tomcat.util.net.AprEndpoint;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    private BCryptPasswordEncoder bCryptPasswordEncoder;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.userRepository = userRepository;

        this.modelMapper = modelMapper;

        this.bCryptPasswordEncoder =  bCryptPasswordEncoder;

    }

    @Override
    public ApiResponse createUser(UserSignUpReqDto request) {

        // NOTE: user input has already been validated at dto level. Please check UserSignUpReqDto

        String apiRoute = "/user";

        if (!request.getPassword().equals(request.getConfirmPass())) {

             throw  new IllegalArgumentException("Password and confirm password values must be the same", apiRoute);

        }

        request.setEmail(request.getEmail().trim().toLowerCase());

        final User USER = userRepository.getByEmail(request.getEmail());

        if (Objects.nonNull(USER)) {

            throw new IllegalArgumentException("A user with this email address already exist", apiRoute);
        }

        final User NEW_USER_DETAILS = modelMapper.map(request, User.class);

        // encrypt the password
        final String ENCRYPTED_PASSWORD =  bCryptPasswordEncoder.encode(request.getPassword());

        NEW_USER_DETAILS.setPassword(ENCRYPTED_PASSWORD);
        final User NEWLY_CREATED_USER = userRepository.save(NEW_USER_DETAILS);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(true);
        apiResponse.setHttpStatus(HttpStatus.CREATED);
        apiResponse.setMessage("User Created Successfully");
        apiResponse.setData(NEWLY_CREATED_USER);

        return  apiResponse;
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
