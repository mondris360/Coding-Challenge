package com.aneeque.coding.challenge.demo.Service.Impl;

import com.aneeque.coding.challenge.demo.Config.JwtTokenConfig;
import com.aneeque.coding.challenge.demo.Controller.ExceptionHandler.CustomErrorClass.IllegalArgumentException;
import com.aneeque.coding.challenge.demo.Controller.ExceptionHandler.CustomErrorClass.UserNotFoundException;
import com.aneeque.coding.challenge.demo.Dto.UserLoginDto;
import com.aneeque.coding.challenge.demo.Dto.UserSignUpReqDto;
import com.aneeque.coding.challenge.demo.Dto.UserSignUpResDto;
import com.aneeque.coding.challenge.demo.Model.User;
import com.aneeque.coding.challenge.demo.Repository.UserRepository;
import com.aneeque.coding.challenge.demo.Service.UserAuthorityService;
import com.aneeque.coding.challenge.demo.Service.UserService;
import com.aneeque.coding.challenge.demo.Util.Api.Response.ApiResponse;

import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.Validator;

import java.util.Objects;

@Service
@Transactional
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
        apiResponse.setMessage("User Created Successfully");
        apiResponse.setData(USER_SIGNUP_RES_DTO);

        return  apiResponse;
    }

    @Override
    @Cacheable(cacheNames = "users-token", key = "#request.email")
    public ApiResponse login(UserLoginDto request) {

        // NOTE: user input has already been validated at dto level. Please check UserLoginDto

        final String API_PATH = "user/login";
        final User USER = userRepository.getByEmail(request.getEmail().toLowerCase().trim());

        if (Objects.isNull(USER)) {

           throw new UserNotFoundException("Invalid Login Details", API_PATH);
        }

        final boolean IS_VALID_PASSWORD = bCryptPasswordEncoder.matches(request.getPassword(), USER.getPassword());

        if (!IS_VALID_PASSWORD) {

            throw new UserNotFoundException("Invalid Login Details", API_PATH);
        }

        final String JWT_USER_TOKEN = jwtTokenConfig.generateToken(USER);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(true);
        apiResponse.setMessage("Login Was Successful");
        apiResponse.setJwtToken(JWT_USER_TOKEN);

        return  apiResponse;
    }

    @Override
    public ApiResponse getAllUsers(int page, int size, String sortByField) {
        // i have already done this at the controller level but i just want to do it here in case another service wants to use this method
        if(size <= 0) {

            size = 20;
        }

        if (page < 0){

            page = 0;
        }

        if (Objects.isNull(sortByField)) {

            sortByField ="createdAt";
        }

        Sort.Direction sortDirection = Sort.Direction.ASC;

        sortByField = sortByField.toLowerCase().trim();

        //  to make the field to sort the records by more flexible so that  the client can sort  by either firstName, lastName , country or createdAt
        final String validatedSortByField = validateSortByField(sortByField);

        if (validatedSortByField.equals("createdAt")) {

            sortDirection =  Sort.Direction.DESC;
        }


        Sort sort = Sort.by(sortDirection, validatedSortByField);
        Pageable pageable = PageRequest.of(page, size, sort);
        final Page<User> allUsers = userRepository.findAll(pageable);

        ApiResponse apiResponse = new ApiResponse();
        apiResponse.setStatus(true);
        apiResponse.setMessage("List of Users");
        apiResponse.setData(allUsers);

        return apiResponse;
    }

    private String validateSortByField(String sortByField) {

        switch (sortByField) {

            case "firstname":
                sortByField = "firstName";
                break;

            case "lastname":
                sortByField = "lastName";
                break;

            case "country":
                sortByField = "country";
                break;

            case "createdat":
                sortByField = "createdAt";
                break;

            default:
                throw new IllegalArgumentException("sortByField must have a value of firstName, lastName, country or createAt", "/user");

        }

        return sortByField;
    }

}
