package org.voduybao.bookstorebackend.controllers;


import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.voduybao.bookstorebackend.dtos.AuthenDto;
import org.voduybao.bookstorebackend.services.AuthenService;

@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class AuthenControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AuthenService authenService;

    private AuthenDto.LoginRequest requestLogin;
    private AuthenDto.LoginResponse responseLogin;

    @BeforeEach
    void initData() {
        requestLogin = AuthenDto.LoginRequest.builder()
                .phoneOrEmail("van.a.nguyen@example.com")
                .password("Password123")
                .build();
        responseLogin = AuthenDto.LoginResponse.builder()
                .build();
    }

    @Test
    void authenticate_invalidLogin_success() throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        String content = objectMapper.writeValueAsString(requestLogin);

//        Mockito.when(authenService.login(Mockito.any()))
//                .thenAnswer(invocation -> {
//                    System.out.println("authenService.login() was called");
//                    return responseLogin;
//                });

        mockMvc.perform(MockMvcRequestBuilders.post("/v1/authentication/login")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(content))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value(200));
    }
}
