package com.aneeque.coding.challenge.demo.Model;

import com.aneeque.coding.challenge.demo.Model.BaseModel.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Entity(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseModel {

    @NotNull(message = "firstName is mandatory")
    private String firstName;

    @NotNull(message = "lastName is mandatory")
    private String lastName;

    @NotNull(message = "email is mandatory")
    @Column(unique =  true)
    private String email;

    @NotNull(message = "password is mandatory")
    private String password;

    @NotNull(message = "phoneNo is mandatory")
    private String phoneNo;

    private String country;


}
