package com.aneeque.coding.challenge.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserLoginDto {

    @NotBlank(message = "email is mandatory")
    @Email
    private String email;

    @NotBlank(message = "password is mandatory")
    private String password;

}
