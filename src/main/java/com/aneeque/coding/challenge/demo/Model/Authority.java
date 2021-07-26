package com.aneeque.coding.challenge.demo.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authorities")
@ToString
public class Authority {

    @Id
    @GeneratedValue
    private int id;

    private String authority;

    @ManyToOne
    @JoinColumn(name="user_fk")
    @JsonIgnore
    private User user;

    public Authority(User user, String authority) {
        this.user =  user;
        this.authority = authority;
    }
}
