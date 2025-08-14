package swa.semproject.userservice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.assertj.core.api.Assertions;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.server.ResponseStatusException;
import swa.semproject.userservice.controller.UserController;
import swa.semproject.userservice.dto.response.UserResponseDTO;
import swa.semproject.userservice.model.User;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static swa.semproject.userservice.UserServiceIntegrationTests.generateRandomString;

@WebMvcTest
public class UserControllerTests {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserController userController;

    @Autowired
    ObjectMapper objectMapper;

    public static UserResponseDTO generateRandomUserResponseDTO() {
        UserResponseDTO user = new UserResponseDTO();
        user.setId(1);
        user.setFirstName("John");
        user.setUserName(generateRandomString()); // has to be unique
        user.setEmail("john@gmail.com");
        user.setLastName("Doe");
        user.setTel(993000123);
        return user;
    }

    @Test
    public void getUser_shouldReturnUser() throws Exception {
        UserResponseDTO user = generateRandomUserResponseDTO();
        when(userController.getUser(anyInt()))
                .thenReturn(user);
        MvcResult mvcResult = this.mockMvc.perform(get("/user/1")).andDo(print())
                .andExpect(status().isOk()).andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        UserResponseDTO response = objectMapper.readValue(contentAsString, UserResponseDTO.class);
        assertThat(response.equals(user)).isTrue();
    }

    @Test
    public void getUser_shouldThrowNotFoundException() throws Exception {
        when(userController.getUser(9999))
                .thenThrow(new ResponseStatusException(HttpStatus.NOT_FOUND));

        this.mockMvc.perform(get("/user/9999")).andDo(print()).andExpect(status().isNotFound());
    }
}
