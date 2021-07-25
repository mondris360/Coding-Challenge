package com.aneeque.coding.challenge.demo.Model;

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
public class User {

    private String firstName;

    private String lastName;

    private String email;

    private String phoneNo;

    private String country;


}
