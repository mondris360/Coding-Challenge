package com.aneeque.coding.challenge.demo.UnitTest;

import com.aneeque.coding.challenge.demo.Config.JwtTokenConfig;
import com.aneeque.coding.challenge.demo.Controller.ExceptionHandler.CustomErrorClass.IllegalArgumentException;
import com.aneeque.coding.challenge.demo.Controller.ExceptionHandler.CustomErrorClass.UserNotFoundException;
import com.aneeque.coding.challenge.demo.Dto.UserLoginDto;
import com.aneeque.coding.challenge.demo.Dto.UserSignUpReqDto;
import com.aneeque.coding.challenge.demo.Model.User;
import com.aneeque.coding.challenge.demo.Repository.UserRepository;
import com.aneeque.coding.challenge.demo.Service.Impl.UserServiceImpl;
import com.aneeque.coding.challenge.demo.Service.UserAuthorityService;
import com.aneeque.coding.challenge.demo.Util.Api.Response.ApiResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.data.domain.Pageable;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Mock
    private JwtTokenConfig jwtTokenConfig;

    @Mock
    private Pageable pageable;

    @Mock Page<User> userPage;

    @Mock
    private UserAuthorityService authorityService;

    @InjectMocks
    private UserServiceImpl userService;

    private static UserSignUpReqDto userSignUpReqDto;

    private static User user;

    private  static UserLoginDto userLoginDto;




    @BeforeAll
    static void setup(){
        userSignUpReqDto = new UserSignUpReqDto();
        userSignUpReqDto.setEmail("test@test.com");
        userSignUpReqDto.setAuthorities("user, admin");
        userSignUpReqDto.setConfirmPass("123456");
        userSignUpReqDto.setPassword("123456");
        userSignUpReqDto.setCountry("Nigeria");
        userSignUpReqDto.setFirstName("Mondris");
        userSignUpReqDto.setLastName("Okundolor");
        userSignUpReqDto.setPhoneNo("08106516740");

        user =  new User();
        user.setEmail("test@test.com");
        user.setPassword("123456");
        user.setCountry("Nigeria");
        user.setFirstName("Mondris");
        user.setLastName("Okundolor");
        user.setPhoneNo("08106516740");

        userLoginDto = new UserLoginDto();
        userLoginDto.setEmail("xxxx@gmail.com");
        userLoginDto.setPassword("testing");
    }
            //TEST FOR createUser()  METHOD
    @Test
    public void whenUserPassAndConfirmPassAreNotThesame(){
        // make the the confirm pass different from the pass
        userSignUpReqDto.setPassword("smith");

        Assertions.assertThrows(IllegalArgumentException.class, () ->{
            userService.createUser(userSignUpReqDto);
        });
    }

    @Test
    public void whenUserEmailAddressAlreadyExist(){

        when(userRepository.getByEmail(anyString())).thenReturn(user);

        Assertions.assertThrows(IllegalArgumentException.class, () ->{
            userService.createUser(userSignUpReqDto);
        });
    }

    @Test
    public void whenSignupPayloadIsValidAndUserEmailDoesNotExist(){

        when(modelMapper.map(userSignUpReqDto, User.class)).thenReturn(user);
        when(bCryptPasswordEncoder.encode(anyString())).thenReturn("XSDUEEUR-SDHHHGF-SJCCJDHFF");
        when(userRepository.save(any(User.class))).thenReturn(user);

        final ApiResponse apiResponse = userService.createUser(userSignUpReqDto);

        Assertions.assertEquals(true, apiResponse.isStatus());
        Assertions.assertEquals("User Created Successfully", apiResponse.getMessage());

    }

    // TEST FOR LOGIN() METHOD

    @Test
    public void whenUserLoginEmailIsInvalid(){

        // make the the confirm pass different from the pass
        when(userRepository.getByEmail(anyString())).thenReturn(null);
        Assertions.assertThrows(UserNotFoundException.class, () ->{

            userService.login(userLoginDto);
        });
    }

    @Test
    public void WhenUserLoginPasswordIsInvalid(){

        // make the the confirm pass different from the pass
        when(userRepository.getByEmail(anyString())).thenReturn(user);
        when(bCryptPasswordEncoder.matches(anyString(), anyString())).thenReturn(false);

        Assertions.assertThrows(UserNotFoundException.class, () ->{
            userService.login(userLoginDto);
        });
    }



    @Test
    public void WhenUserLoginDetailsAreValid(){

        // make the the confirm pass different from the pass
        String token ="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJhZG1pbkBtb25leW1pZS5jb20iLCJleHAiOjE2Mjc2MzgyNjgsInVzZXIiOnsiZmlyc3RuYW1lIjoiQWRtaW4iLCJsYXN0bmFtZSI6IlRlY2giLCJlbWFpbCI6ImFkbWluQG1vbmV5bWllLmNvbSIsImF1dGhvcml0aWVzIjpbeyJhdXRob3JpdHkiOiJST0xFX0FETUlOIn1dLCJlbmFibGVkIjp0cnVlLCJ1c2VybmFtZSI6ImFkbWluQG1vbmV5bWllLmNvbSJ9LCJpYXQiOjE2MjcwMzM0Njh9.UQ12L01nqPtNvopvlGFdMbLdKU6LxNas-HuaK9j0rdfB-YCwpYPxwqEBQf8ydwyT27i6mu8D1K_0lAkHtMoX6w";
        when(userRepository.getByEmail(anyString())).thenReturn(user);
        when(bCryptPasswordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(jwtTokenConfig.generateToken(user)).thenReturn(token);

        final ApiResponse apiResponse = userService.login(userLoginDto);

        Assertions.assertTrue(apiResponse.isStatus());
        Assertions.assertEquals("Login Was Successful", apiResponse.getMessage());
        Assertions.assertEquals(token, apiResponse.getJwtToken());

    }


    // TESTS FOR getALlUsers() Methods

    @Test
    public void getALlUsersWhenSortByFieldValueIsInValid(){

        Assertions.assertThrows(IllegalArgumentException.class, () ->{

            userService.getAllUsers(1, 20, "xxxxxx");

        });
    }


    @Test
    public void getAllUsersWhenAllMethodArgsAreValid(){

        when(userRepository.findAll(any(Pageable.class))).thenReturn(userPage);

        final ApiResponse apiResponse = userService.getAllUsers(1, 20, "firstName");

        Assertions.assertTrue(apiResponse.isStatus());
        Assertions.assertEquals("List of Users", apiResponse.getMessage());
    }

}
