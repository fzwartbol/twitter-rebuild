package com.frederikzwartbol.springboottwitterrebuild.features.authentication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frederikzwartbol.springboottwitterrebuild.features.authentication.Credentials;
import com.frederikzwartbol.springboottwitterrebuild.features.authentication.SecurityConstants;
import com.frederikzwartbol.springboottwitterrebuild.features.authentication.repository.CredentialsRepository;
import com.frederikzwartbol.springboottwitterrebuild.features.user.User;
import com.frederikzwartbol.springboottwitterrebuild.features.user.UserService;
import com.frederikzwartbol.springboottwitterrebuild.features.user.models.ProfileInformation;
import com.frederikzwartbol.springboottwitterrebuild.features.user.models.dto.UserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Controller integration test example
 */

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private WebApplicationContext context;
    @MockBean
    private CredentialsRepository credentialsRepository;
    @MockBean
    private UserService userService;
    private User user;
    private Credentials credentials;

    @BeforeEach
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();

        credentials = new Credentials("john.doe@example.com", encoder.encode( "password"));
        user = User.builder()
                .id(1L)
                .firstName("John")
                .credentials(credentials)
                .lastName("Doe")
                .twitterHandle("johndoe")
                .profileImage("profile_image")
                .profileInformation(new ProfileInformation("profile_header","profile_bio",
                        "profile_background","location","link_url"))
                .build();
    }

    @Test
    void whenLoginIsInvalid_thenReturnUnauthorized() throws Exception {
        when(credentialsRepository.findByUsername("john.doe@example.com")).thenReturn(Optional.empty());
        mockMvc.perform(post("/authenticate")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("username","john.doe@example.com")
                        .param("password","password"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void whenLoginIsValid_thenReturnAuthorisationResponseOk() throws Exception {
        when(credentialsRepository.findByUsername("john.doe@example.com")).thenReturn(Optional.of(credentials));
        when(userService.findUserByEmailAddress("john.doe@example.com")).thenReturn(user);
        mockMvc.perform(post("/authenticate")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                        .param("username","john.doe@example.com")
                        .param("password","password"))
                .andExpect(status().isOk());
    }

    @Test
    void whenAuthenticateRefreshTokenIsValid_thenReturnResponseOk() throws Exception {
        mockMvc.perform(post("/authenticate/refresh"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenAuthenticateRefreshTokenIsNotPresent_thenReturnBadRequest() throws Exception {
        mockMvc.perform(post("/authenticate/refresh"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void whenAuthenticateRefreshTokenIsNotValid_thenReturnUnauthorized() throws Exception {
        mockMvc.perform(post("/authenticate/refresh")
                .param(SecurityConstants.REFRESH_TOKEN_REQUEST_PARAMETER,"FALSE_TOKEN"))
                .andExpect(status().isUnauthorized());
    }
}