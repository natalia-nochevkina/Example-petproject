package com.natanight.petproject.user;

import com.natanight.petproject.IntegrationTestBase;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// TODO: add validation tests after fixing validation

@Sql("/user/user-crud-test-data.sql")
@Transactional
class UserCRUDTest extends IntegrationTestBase {
    @Autowired
    MockMvc mockMvc;

    @Nested
    class CreateUser {
        private final String PATH = "/users";

        @Nested
        class NotAuthorized {
            @Test
            void shouldReturn401_whenNotAuthorizedCreatesUser() throws Exception {
                mockMvc.perform(post(PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .content("""
                                        {
                                          "username": "billy_billy",
                                          "email": "somebilly@mail.ru",
                                          "password": "password"
                                        }
                                        """))
                        .andExpect(status().isUnauthorized());
            }
        }

        @Nested
        class NotAdmin {
            @Test
            void shouldReturn403_whenUserCreatesUser() throws Exception {
                mockMvc.perform(post(PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", getUserToken())
                                .content("""
                                        {
                                          "username": "billy_billy",
                                          "email": "somebilly@mail.ru",
                                          "password": "password"
                                        }
                                        """))
                        .andExpect(status().isForbidden());
            }
        }

        @Nested
        class Admin {
            @Test
            void shouldReturn201_whenAdminCreatesUser() throws Exception {
                mockMvc.perform(post(PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", getAdminToken())
                                .content("""
                                        {
                                          "username": "billy_billy",
                                          "email": "somebilly@mail.ru",
                                          "password": "password"
                                        }
                                        """))
                        .andExpect(status().isCreated());
            }

            @Test
            void shouldReturn409_whenUserExists() throws Exception {
                mockMvc.perform(post(PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", getAdminToken())
                                .content("""
                                        {
                                          "username": "non-admin3",
                                          "email": "somebilly@mail.ru",
                                          "password": "password"
                                        }
                                        """))
                        .andExpect(status().isConflict());
            }

            @Test
            void shouldReturn400_whenUsernameIsLessThen3Symbols() throws Exception {
                mockMvc.perform(post(PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", getAdminToken())
                                .content("""
                                        {
                                          "username": "no",
                                          "email": "somebilly@mail.ru",
                                          "password": "password"
                                        }
                                        """))
                        .andExpect(status().isBadRequest());
            }

            @Test
            void shouldReturn400_whenUsernameIsMoreThen25Symbols() throws Exception {
                mockMvc.perform(post(PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", getAdminToken())
                                .content("""
                                        {
                                          "username": "nononononononononononononononononononono",
                                          "email": "somebilly@mail.ru",
                                          "password": "password"
                                        }
                                        """))
                        .andExpect(status().isBadRequest());
            }

            @Test
            void shouldReturn400_whenUsernameIsEmpty() throws Exception {
                mockMvc.perform(post(PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", getAdminToken())
                                .content("""
                                        {
                                          "username": "",
                                          "email": "somebilly@mail.ru",
                                          "password": "password"
                                        }
                                        """))
                        .andExpect(status().isBadRequest());
            }

            @Test
            void shouldReturn400_whenUsernameIsMissing() throws Exception {
                mockMvc.perform(post(PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", getAdminToken())
                                .content("""
                                        {
                                          "username": "",
                                          "email": "somebilly@mail.ru",
                                          "password": "password"
                                        }
                                        """))
                        .andExpect(status().isBadRequest());
            }

            @Test
            void shouldReturn400_whenOtherFieldsAreAdded() throws Exception {
                mockMvc.perform(post(PATH)
                                .contentType(MediaType.APPLICATION_JSON)
                                .header("Authorization", getAdminToken())
                                .content("""
                                        {
                                          "username": "no",
                                          "email": "somebilly@mail.ru",
                                          "password": "password",
                                          "is_admin": "true"
                                        }
                                        """))
                        .andExpect(status().isBadRequest());
            }
        }
    }
}