package com.aneeque.coding.challenge.demo.IntegerationTest;

import com.aneeque.coding.challenge.demo.Dto.UserSignUpReqDto;
import com.aneeque.coding.challenge.demo.Repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import javax.annotation.Resource;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.CoreMatchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest()
@AutoConfigureMockMvc
public class UserServiceIntegrationTest {

    @Resource
    private MockMvc mockMvc;

    @Resource
    private ObjectMapper objectMapper;

    @Resource
    private UserRepository userRepository;

    private  static UserSignUpReqDto userSignUpReqDto;

    @BeforeEach
     void setup() {
        userSignUpReqDto = new UserSignUpReqDto();
        userSignUpReqDto.setEmail("test@test.com");
        userSignUpReqDto.setAuthorities("user, admin");
        userSignUpReqDto.setConfirmPass("123456");
        userSignUpReqDto.setPassword("123456");
        userSignUpReqDto.setCountry("Nigeria");
        userSignUpReqDto.setFirstName("Mondris");
        userSignUpReqDto.setLastName("Okundolor");
        userSignUpReqDto.setPhoneNo("08106516740");
    }

    @Test
    public void whenFirstNameIsEmpty() throws Exception {
        userSignUpReqDto.setFirstName(null);

        mockMvc.perform(post("/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userSignUpReqDto))
        ).andExpect(status().is4xxClientError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.message", is("Invalid User Input")))
                .andExpect(jsonPath("$.errors[0]", is("firstName is mandatory")));

    }
    @Test
    public void whenLastNameIsEmpty() throws Exception {
        userSignUpReqDto.setLastName(null);

        mockMvc.perform(post("/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userSignUpReqDto))
        ).andExpect(status().is4xxClientError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.message", is("Invalid User Input")))
                .andExpect(jsonPath("$.errors[0]", is("lastName is mandatory")));

    }

    @Test
    public void whenEmailIsEmpty() throws Exception {
        userSignUpReqDto.setEmail(null);

        mockMvc.perform(post("/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userSignUpReqDto))
        ).andExpect(status().is4xxClientError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.message", is("Invalid User Input")))
                .andExpect(jsonPath("$.errors[0]", is("email is mandatory")));

    }

    @Test
    public void whenPhoneNoIsEmpty() throws Exception {
        userSignUpReqDto.setPhoneNo(null);

        mockMvc.perform(post("/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userSignUpReqDto))
        ).andExpect(status().is4xxClientError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.message", is("Invalid User Input")))
                .andExpect(jsonPath("$.errors[0]", is("phoneNo is mandatory")));

    }

    @Test
    public void whenCountryNoIsEmpty() throws Exception {
        userSignUpReqDto.setCountry(null);

        mockMvc.perform(post("/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userSignUpReqDto))
        ).andExpect(status().is4xxClientError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.message", is("Invalid User Input")))
                .andExpect(jsonPath("$.errors[0]", is("country is mandatory")));

    }

    @Test
    public void whenAuthorityIsEmpty() throws Exception {
        userSignUpReqDto.setAuthorities(null);

        mockMvc.perform(post("/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userSignUpReqDto))
        ).andExpect(status().is4xxClientError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.message", is("Invalid User Input")))
                .andExpect(jsonPath("$.errors[0]", is("please specify one or more user authorities")));

    }

    @Test
    public void whenPasswordIsEmpty() throws Exception {
        userSignUpReqDto.setPassword(null);

        mockMvc.perform(post("/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userSignUpReqDto))
        ).andExpect(status().is4xxClientError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.message", is("Invalid User Input")))
                .andExpect(jsonPath("$.errors[0]", is("password is mandatory")));

    }


    @Test
    public void whenConfirmPassIsEmpty() throws Exception {
        userSignUpReqDto.setConfirmPass(null);

        mockMvc.perform(post("/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userSignUpReqDto))
        ).andExpect(status().is4xxClientError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.message", is("Invalid User Input")))
                .andExpect(jsonPath("$.errors[0]", is("confirmPass is mandatory")));

    }

    @Test
    public void WhenPasswordAndConfirmPassAreNotThesameValue() throws Exception {
        userSignUpReqDto.setPassword("abcd");
        userSignUpReqDto.setPassword("12345");

        mockMvc.perform(post("/user")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(userSignUpReqDto))
        ).andExpect(status().is4xxClientError())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.httpStatus", is("BAD_REQUEST")))
                .andExpect(jsonPath("$.message", is("Password and confirm password values must be the same")));
    }

}
