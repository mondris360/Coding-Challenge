//package com.aneeque.coding.challenge.demo.Service.Impl;
//
//import com.aneeque.coding.challenge.demo.Controller.ExceptionHandler.CustomErrorClass.IllegalArgumentException;
//import com.aneeque.coding.challenge.demo.Dto.UserSignUpReqDto;
//import com.aneeque.coding.challenge.demo.Model.User;
//import org.springframework.stereotype.Service;
//
//import java.util.Objects;
//
//@Service
//public class UserValidationService {
//
//    private  final  String apiRoute = "/user";
//
//    public void validateSignUpPayload(UserSignUpReqDto request) {
//
//        if (Objects.isNull(request)) {
//
//            throw  new IllegalArgumentException("Please provide the right arguments", apiRoute);
//
//        } else if (Objects.isNull(request.getFirstName())) {
//
//            throw  new IllegalArgumentException("first_name is mandatory", apiRoute);
//
//        }else if (Objects.isNull(request.getLastName())) {
//
//            throw  new IllegalArgumentException("last_name is mandatory", apiRoute);
//
//        }else if (Objects.isNull(request.getPhoneNo())) {
//
//            throw  new IllegalArgumentException("phone_no is mandatory", apiRoute);
//
//        }else if (Objects.isNull(request.getEmail())) {
//
//            throw  new IllegalArgumentException("email is mandatory", apiRoute);
//
//        }else if (Objects.isNull(request.getPassword())) {
//
//            throw  new IllegalArgumentException("password is mandatory", apiRoute);
//
//        }else if (Objects.isNull(request.getConfirmPass())) {
//
//            throw  new IllegalArgumentException("confirm_password is mandatory", apiRoute);
//
//        }else if (Objects.isNull(request.getAuthorities())) {
//
//            throw  new IllegalArgumentException("authorities is mandatory", apiRoute);
//
//        }else if (Objects.isNull(request.getAuthorities())) {
//
//            throw  new IllegalArgumentException("authorities is mandatory", apiRoute);
//        }
//
//
//
//
//        if (!request.getPassword().equals(request.getConfirmPass())) {
//
//            throw  new IllegalArgumentException("Password and confirm password values must be the same", apiRoute);
//
//        }
//
//        request.setEmail(request.getEmail().trim().toLowerCase());
//
//        final User USER = userRepository.getByEmail(request.getEmail());
//
//        if (Objects.nonNull(USER)) {
//
//            throw new IllegalArgumentException("A user with this email address already exist", apiRoute);
//        }
//    }
//}
