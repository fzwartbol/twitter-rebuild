package com.frederikzwartbol.springboottwitterrebuild.features.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.frederikzwartbol.springboottwitterrebuild.features.DatabaseAbstractContainerTest;
import com.frederikzwartbol.springboottwitterrebuild.features.authentication.Credentials;
import com.frederikzwartbol.springboottwitterrebuild.features.user.models.ProfileInformation;
import com.frederikzwartbol.springboottwitterrebuild.features.user.models.dto.UserRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIntegrationWithContainerTest extends DatabaseAbstractContainerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private WebApplicationContext context;
    @Autowired
    private ObjectMapper objectMapper;

    private UserRequest userRequest;

    private User user;

    @BeforeEach
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(springSecurity())
                .build();
        userRequest = new UserRequest(1L, "John", "Doe",
                "john.doe@example.com", "password", "johndoe", "profile_image",
                "profile_header", "profile_bio", "profile_background", "location", "link_url");
        user = User.builder()
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

        mockMvc.perform(post("/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userRequest)));

    }

    @Test
    void whenSaveUserIsNotLoggedIn_thenReturnUnauthorized() throws Exception {
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(username = "john", roles = { "USER" })
    void whenSaveUserIsLoggedInWithUser_thenReturnForbidden() throws Exception {
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(username = "john", roles = { "ADMIN" })
    void whenSaveUserIsLoggedInWithADMIN_thenReturnOk() throws Exception {
        mockMvc.perform(post("/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(jsonPath("credentials").doesNotHaveJsonPath())
                .andExpect(status().isCreated());
    }

    @Test
    @WithMockUser
    void whenFindUserIsNotFound_thenReturnNotFound() throws Exception {
        mockMvc.perform(get("/users/3"))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    void whenFindUserIsNotFound_thenReturnOk() throws Exception {
        mockMvc.perform(get("/users/1"))
                .andExpect(jsonPath("credentials").doesNotHaveJsonPath())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void whenFindUserByHandleIsFound_thenReturnOk() throws Exception {
        mockMvc.perform(get("/users/handle/johnnydoe"))
                .andExpect(jsonPath("credentials").doesNotHaveJsonPath())
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser
    void whenFindUserByHandleIsNotFound_thenReturnNotFound() throws Exception {
        mockMvc.perform(get("/users/handle/elonmusk"))
                .andExpect(status().isNotFound());
    }

}