package com.aneeque.coding.challenge.demo.Model;

import com.aneeque.coding.challenge.demo.Model.BaseModel.BaseModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseModel {


    private String firstName;

    private String lastName;

    private String email;

    private String phoneNo;

    private String country;


}
