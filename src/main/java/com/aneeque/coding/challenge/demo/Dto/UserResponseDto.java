package com.aneeque.coding.challenge.demo.Dto;

import com.aneeque.coding.challenge.demo.Model.Authority;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserResponseDto {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNo;

    private String country;

    private Set<Authority> authorities;

}
