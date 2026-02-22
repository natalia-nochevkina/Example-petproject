package com.natanight.petproject.user;

import com.natanight.petproject.IntegrationTestBase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// @Sql("/auth/auth-test-data.sql")
class CreateUserTest extends IntegrationTestBase {
    @Autowired
    MockMvc mockMvc;

    @Test
    void login_shouldReturn401_whenWrongCredentials() throws Exception {
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                {
                                  "username": "wrong",
                                  "password": "wrong"
                                }
                                """))
                .andExpect(status().isUnauthorized());
    }
}