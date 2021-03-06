package com.aneeque.coding.challenge.demo.Model;

import com.aneeque.coding.challenge.demo.Model.BaseModel.BaseModel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Set;

@Entity(name = "user")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User extends BaseModel{

    @NotNull(message = "firstName is mandatory")
    private String firstName;

    @NotNull(message = "lastName is mandatory")
    private String lastName;

    @NotNull(message = "email is mandatory")
    @Column(unique =  true)
    private String email;

    @NotNull(message = "password is mandatory")
    @JsonIgnore
    private String password;

    @NotNull(message = "phoneNo is mandatory")
    private String phoneNo;

    private String country;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Authority> authorities;


}
