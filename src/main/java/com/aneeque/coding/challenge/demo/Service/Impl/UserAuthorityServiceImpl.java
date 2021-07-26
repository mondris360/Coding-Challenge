package com.aneeque.coding.challenge.demo.Service.Impl;

import com.aneeque.coding.challenge.demo.Model.Authority;
import com.aneeque.coding.challenge.demo.Model.User;
import com.aneeque.coding.challenge.demo.Repository.UserAuthorityRepository;
import com.aneeque.coding.challenge.demo.Service.UserAuthorityService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Service
@EnableAsync
public class UserAuthorityServiceImpl implements UserAuthorityService {

   private UserAuthorityRepository userAuthorityRepository;

    public UserAuthorityServiceImpl(UserAuthorityRepository userAuthorityRepository) {
        this.userAuthorityRepository = userAuthorityRepository;
    }

    @Override
    @Async
    public void saveAuthority(User user, String authorities) {

        Set<Authority> userAuthorities = new HashSet<>();

        if (Objects.isNull(authorities)) {

            userAuthorities.add(new Authority(user, "user"));

        } else {

            final String[] AUTHORITIES_ARRAY = authorities.split(",");

            for (String authority : AUTHORITIES_ARRAY) {

                userAuthorities.add(new Authority(user, authority.toLowerCase().trim()));
            }
        }

        userAuthorityRepository.saveAll(userAuthorities);
    }
}
