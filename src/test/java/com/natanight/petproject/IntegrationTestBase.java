package com.natanight.petproject;

import com.natanight.petproject.service.JwtService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public abstract class IntegrationTestBase {
    @Autowired
    private JwtService jwtService;

    protected String adminToken;
    protected String userToken;

    @BeforeAll
    void setupToken() {
        UserDetails adminDetails = User.builder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();
        adminToken = jwtService.generateToken(adminDetails);


        UserDetails userDetails = User.builder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();
        userToken = jwtService.generateToken(userDetails);
    }

    static final PostgreSQLContainer<?> postgres =
            new PostgreSQLContainer<>("postgres:15");

    static {
        postgres.start();
    }

    @DynamicPropertySource
    static void registerProps(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgres::getJdbcUrl);
        registry.add("spring.datasource.username", postgres::getUsername);
        registry.add("spring.datasource.password", postgres::getPassword);
    }

    protected String getAdminToken() {
        return "Bearer " + adminToken;
    }

    protected String getUserToken() {
        return "Bearer " + userToken;
    }
}