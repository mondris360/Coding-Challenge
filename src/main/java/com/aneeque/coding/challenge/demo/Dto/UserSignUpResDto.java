package com.aneeque.coding.challenge.demo.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserSignUpResDto {

    private  Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNo;

    private String country;

    private  String CreatedAt;

}
