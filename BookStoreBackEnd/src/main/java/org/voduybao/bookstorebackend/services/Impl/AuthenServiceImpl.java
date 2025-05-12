package org.voduybao.bookstorebackend.services.Impl;

import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.entities.Token;
import org.voduybao.bookstorebackend.dao.entities.User;
import org.voduybao.bookstorebackend.dao.repositories.RoleRepository;
import org.voduybao.bookstorebackend.dao.repositories.TokenRepository;
import org.voduybao.bookstorebackend.dao.repositories.UserRepository;
import org.voduybao.bookstorebackend.dtos.AuthenDto;
import org.voduybao.bookstorebackend.services.AuthenService;
import org.voduybao.bookstorebackend.services.JwtService;
import org.voduybao.bookstorebackend.services.TokenService;
import org.voduybao.bookstorebackend.tools.contants.AuthProviderEnum;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseException;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseErrors;
import org.voduybao.bookstorebackend.tools.security.password.PasswordUtils;
import org.voduybao.bookstorebackend.tools.utils.StrUtil;

import java.util.Set;

@Component
@Slf4j(topic = "AUTHENTICATION-SERVICE")
public class AuthenServiceImpl implements AuthenService {
    @Setter(onMethod_ = @Autowired)
    private PasswordUtils passwordUtils;

    @Setter(onMethod_ = @Autowired)
    private UserRepository userRepository;
    @Setter(onMethod_ = @Autowired)
    private TokenRepository tokenRepository;
    @Setter(onMethod_ = @Autowired)
    private RoleRepository roleRepository;

    @Setter(onMethod_ = @Autowired)
    private TokenService tokenService;
    @Setter(onMethod_ = @Autowired)
    private JwtService jwtService;

    @Override
    public void reigster(AuthenDto.RegisterRequest request) {
        log.info("Authentication registered ...!");
        if (userRepository.existsUserByEmail(request.getEmail()))
            throw new ResponseException(ResponseErrors.EMAIL_VERIFIED);

        if (userRepository.existsUserByPhoneNumber(request.getPhone()))
            throw new ResponseException(ResponseErrors.PHONE_VERIFIED);

        User user = User.builder()
                .phoneNumber(request.getPhone())
                .email(request.getEmail())
                .password(passwordUtils.hashPassword(request.getPassword()))
                .authProvider(AuthProviderEnum.LOCAL)
                .roles(Set.of(roleRepository.getCustomerRole()))
                .build();

        userRepository.save(user);
    }

    @Override
    public AuthenDto.LoginResponse login(
            AuthenDto.LoginRequest request,
            HttpServletResponse response) {
        log.info("Authentication sign in ...!");
        User user = null;
        if (request.isEmail()) {
            user = userRepository.findUserByEmail(request.getPhoneOrEmail())
                    .orElseThrow(() -> new ResponseException(ResponseErrors.EMAIL_VERIFIED));
        } else if (request.isPhone()) {
            user = userRepository.findUserByPhone(request.getPhoneOrEmail())
                    .orElseThrow(() -> new ResponseException(ResponseErrors.PHONE_VERIFIED));
        }

        if (!passwordUtils.checkPassword(request.getPassword(), user.getPassword()))
            throw new ResponseException(ResponseErrors.PASSWORD_INCORRECT);

        Token token = tokenService.createAndSaveToken(user, response);

        return AuthenDto.LoginResponse.builder()
                .userId(user.getUserId())
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }

    @Override
    public void logout(AuthenDto.LogoutRequest request, HttpServletResponse response) {
        log.info("Authentication sign out ...!");
        String email = jwtService.extractAccessTokenEmail(request.getAccessToken());
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new ResponseException(ResponseErrors.ACCOUNT_EXISTS));

        jwtService.verificationToken(request.getAccessToken(), user);

        String jti = jwtService.extracJti(request.getAccessToken());
        Token token = tokenRepository.findByJti(jti)
                .orElseThrow(() -> new ResponseException(ResponseErrors.TOKEN_INVALID));

        boolean checkAccessTokenExpTime = jwtService.extractTokenExpired(request.getAccessToken());
        if (!checkAccessTokenExpTime) {
            tokenService.revokeToken(token, response);
        }

    }

    @Override
    public AuthenDto.LoginResponse refresh(String refreshToken, HttpServletResponse response) {
        log.info("Authentication refresh token ...!");
        if (StrUtil.isBlank(refreshToken))
            throw new ResponseException(ResponseErrors.REFRESH_TOKEN_INVALID);

        String email = jwtService.extractAccessTokenEmail(refreshToken);
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new ResponseException(ResponseErrors.ACCOUNT_EXISTS));

        boolean isVerifi = jwtService.verificationToken(refreshToken, user);
        if (!isVerifi)
            throw new ResponseException(ResponseErrors.REFRESH_TOKEN_INVALID);

        Token token = tokenService.createAndSaveToken(user, response);

        return AuthenDto.LoginResponse.builder()
                .userId(user.getUserId())
                .accessToken(token.getAccessToken())
                .refreshToken(token.getRefreshToken())
                .build();
    }

    @Override
    public String socailAuthType(String loginType, HttpServletResponse response) {
        log.info("Authentication login with google...!");


        return null;
    }

}