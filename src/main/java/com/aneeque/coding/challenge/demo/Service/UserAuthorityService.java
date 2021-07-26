package com.aneeque.coding.challenge.demo.Service;

import com.aneeque.coding.challenge.demo.Model.User;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

@Service
@Primary
public interface UserAuthorityService {

    void saveAuthority(User user, String authorities);
}
