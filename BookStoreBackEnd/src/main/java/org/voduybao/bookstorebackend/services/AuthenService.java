package org.voduybao.bookstorebackend.services;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.voduybao.bookstorebackend.dtos.AuthenDto;

public interface AuthenService {
    void reigster(AuthenDto.RegisterRequest request);

    AuthenDto.LoginResponse login(AuthenDto.LoginRequest request, HttpServletResponse response);

    void logout(AuthenDto.LogoutRequest request, HttpServletResponse response);

    AuthenDto.LoginResponse refresh(String refreshToken, HttpServletResponse response);

    String socailAuthType(String loginType, HttpServletResponse response);

}
