package com.aneeque.coding.challenge.demo.Config;

import com.aneeque.coding.challenge.demo.Model.User;
import com.aneeque.coding.challenge.demo.Util.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Date;


@Service
@Slf4j
public class JwtTokenConfig {


    public String generateToken(@Valid User user) throws NullPointerException {
        long currentTime =  System.currentTimeMillis();
        // 2hrs
        int tokenValidityPeriod = 2 * 60 * 60 * 1000;
        final String generatedToken = Jwts.builder().signWith(SignatureAlgorithm.HS256,
                Constants.JwtTokenSecretKey7854fhg2234567880695.toString())
                .setIssuedAt(new Date(currentTime))
                .setExpiration(new Date(currentTime + tokenValidityPeriod))
                .claim("email", user.getEmail())
                .claim("Authorities", user.getAuthorities())
                .compact();

        return generatedToken;
    }


    public Claims validateToken(String token) throws Exception {
       try {
           return Jwts.parser().setSigningKey(Constants.JwtTokenSecretKey7854fhg2234567880695.toString())
                   .parseClaimsJws(token)
                   .getBody();

       } catch(Exception e){
            log.warn(e.getMessage());
           throw new IllegalArgumentException("Invalid/Expired JWT token");
       }


    }


}
