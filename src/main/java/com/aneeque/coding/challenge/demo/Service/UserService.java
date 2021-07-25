package com.aneeque.coding.challenge.demo.Service;

import com.aneeque.coding.challenge.demo.Dto.UserSignUpReqDto;
import com.aneeque.coding.challenge.demo.Util.Api.Response.ApiResponse;
import org.springframework.stereotype.Service;

@Service
public interface UserService {

    ApiResponse createUser(UserSignUpReqDto request);

    ApiResponse login(UserSignUpReqDto request);

    ApiResponse getAllUsers();

}
