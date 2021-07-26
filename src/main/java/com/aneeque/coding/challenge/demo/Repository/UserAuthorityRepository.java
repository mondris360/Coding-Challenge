package com.aneeque.coding.challenge.demo.Repository;

import com.aneeque.coding.challenge.demo.Model.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserAuthorityRepository extends JpaRepository<Authority, Long> {

}
