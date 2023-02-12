package com.frederikzwartbol.springboottwitterrebuild.features.authentication.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.frederikzwartbol.springboottwitterrebuild.features.authentication.Credentials;
import com.frederikzwartbol.springboottwitterrebuild.features.user.User;
import com.frederikzwartbol.springboottwitterrebuild.features.user.UserService;
import com.frederikzwartbol.springboottwitterrebuild.features.user.models.ProfileInformation;
import com.frederikzwartbol.springboottwitterrebuild.features.user.models.dto.UserRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


/**
 * Controller integration test example
 */
@SpringBootTest
@AutoConfigureMockMvc
class RegisterControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    private UserRequest userRequest;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        userRequest = new UserRequest(1L, "John", "Doe",
                "john.doe@example.com", "password", "johndoe", "profile_image",
                "profile_header", "profile_bio", "profile_background", "location", "link_url");
    }

    @Test
    public void whenRegisterWithValidRequest_thenReturnCreated() throws Exception {
        User user = User.builder()
                .id(1L).firstName("John")
                .credentials(new Credentials("john.doe@example.com","password"))
                .lastName("Doe")
                .twitterHandle("johndoe")
                .profileImage("profile_image")
                .profileInformation(
                        new ProfileInformation(
                                "profile_header",
                                "profile_bio",
                                "profile_background",
                                "location","link_url"))
                .build();

        when(userService.saveUserFromRequest(any(UserRequest.class))).thenReturn(user);
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated());
    }

    @Test
    public void whenRegisterWithInValidRequest_thenReturnCreated() throws Exception {
        when(userService.existsByEmailAddress(userRequest.getEmailAddress())).thenReturn(true);
        mockMvc.perform(post("/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isBadRequest());
    }
}