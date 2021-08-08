package com.aneeque.coding.challenge.demo.Repository;

import com.aneeque.coding.challenge.demo.Model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface UserRepository   extends JpaRepository<User, Long> {

    User getByEmail(String email);
}
