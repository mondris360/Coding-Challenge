package com.aneeque.coding.challenge.demo.Dto;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserSignUpReqDto {

    @NotBlank(message = "firstName is mandatory")
    private String firstName;

    @NotBlank(message = "lastName is mandatory")
    private String lastName;

    @NotBlank(message = "email is mandatory")
    @Email
    private String email;

    @NotBlank(message = "password is mandatory")
    private String password;

    @NotBlank(message = "confirmPass is mandatory")
    private  String confirmPass;

    @NotBlank(message = "phoneNo is mandatory")
    private String phoneNo;

    private String country;

    @NotBlank(message = "please specify one or more user authorities")
    private String authorities;
}
