package com.aneeque.coding.challenge.demo.Repository;

import com.aneeque.coding.challenge.demo.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository   extends JpaRepository<User, Long> {

}
