package com.aneeque.coding.challenge.demo.Service.Impl;

import com.aneeque.coding.challenge.demo.Config.JwtTokenConfig;
import com.aneeque.coding.challenge.demo.Dto.UserLoginDto;
import com.aneeque.coding.challenge.demo.Dto.UserSignUpReqDto;
import com.aneeque.coding.challenge.demo.Dto.UserSignUpResDto;
import com.aneeque.coding.challenge.demo.Model.User;
import com.aneeque.coding.challenge.demo.Repository.UserRepository;
import com.aneeque.coding.challenge.demo.Service.UserAuthorityService;
import com.aneeque.coding.challenge.demo.Service.UserService;
import com.aneeque.coding.challenge.demo.Util.Api.Exception.CustomErrorClass.IllegalArgumentException;
import com.aneeque.coding.challenge.demo.Util.Api.Exception.CustomErrorClass.UserNotFoundException;
import com.aneeque.coding.challenge.demo.Util.Api.Response.ApiResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    private ModelMapper modelMapper;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private JwtTokenConfig jwtTokenConfig;

    private  UserAuthorityService authorityService;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, BCryptPasswordEncoder bCryptPassEncoder,
                           JwtTokenConfig jwtTokenConfig , UserAuthorityService authorityService) {

        this.userRepository = userRepository;

        this.modelMapper = modelMapper;

        this.bCryptPasswordEncoder =  bCryptPassEncoder;

        this.jwtTokenConfig = jwtTokenConfig;

        this.authorityService = authorityService;

    }

    @Override
    public ApiResponse createUser(UserSignUpReqDto request) {

        // NOTE: user input has already been validated at dto level. Please check UserSignUpReqDto

        String apiRoute = "/user";

        if (!request.getPassword().equals(request.getConfirmPass())) {

             throw  new IllegalArgumentException("Password and confirm password values must be the same", apiRoute);

        }
        System.out.println(request);

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

        final UserSignUpResDto USER_SIGNUP_RES_DTO = modelMapper.map(NEWLY_CREATED_USER, UserSignUpResDto.class);

        authorityService.saveAuthority(NEWLY_CREATED_USER, request.getAuthorities());

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(true);
        apiResponse.setHttpStatus(HttpStatus.CREATED);
        apiResponse.setMessage("User Created Successfully");
        apiResponse.setData(USER_SIGNUP_RES_DTO);

        System.out.println("returning response");

        return  apiResponse;
    }

    @Override
    public ApiResponse login(UserLoginDto request) {

        // NOTE: user input has already been validated at dto level. Please check UserLoginDto

        final String API_PATH = "user/login";

        final User USER = userRepository.getByEmail(request.getEmail().toLowerCase().trim());

        if (Objects.isNull(USER)) {

           throw new UserNotFoundException("Invalid  Email", API_PATH);
        }

        final boolean IS_VALID_PASSWORD = bCryptPasswordEncoder.matches(request.getPassword(), USER.getPassword());
        System.out.println("IS_VALID_PASSWORD" +  IS_VALID_PASSWORD);
        if (!IS_VALID_PASSWORD) {

            throw new UserNotFoundException("Invalid  pass Details", API_PATH);
        }

        final String JWT_USER_TOKEN = jwtTokenConfig.generateToken(USER);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(true);
        apiResponse.setHttpStatus(HttpStatus.OK);
        apiResponse.setMessage("Login Was Successful");
        apiResponse.setJwtToken(JWT_USER_TOKEN);

        return  apiResponse;
    }

    @Override
    public ApiResponse getAllUsers() {

        final List<User> ALL_USERS = userRepository.findAll();

        return null;
    }

}
