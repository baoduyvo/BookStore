package org.voduybao.bookstorebackend.services.Impl;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.entities.Token;
import org.voduybao.bookstorebackend.dao.entities.User;
import org.voduybao.bookstorebackend.dao.repositories.TokenRepository;
import org.voduybao.bookstorebackend.services.JwtService;
import org.voduybao.bookstorebackend.services.TokenService;
import org.voduybao.bookstorebackend.tools.utils.RandomNumberGenerator;
import org.voduybao.bookstorebackend.tools.utils.StrUtil;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Component
@Slf4j(topic = "TOKEN-SERVICE")
public class TokenServiceImpl implements TokenService {
    @Setter(onMethod_ = @Autowired)
    private TokenRepository tokenRepository;

    @Setter(onMethod_ = @Autowired)
    private JwtService jwtService;

    @Value("${jwt.access-token-validity-in-MINUTES}")
    private int accessTokenMINUTES;

    @Value("${jwt.refresh-token-validity-in-DAY}")
    private int refreshTokenDAY;

    @Override
    public Token createAndSaveToken(User user, HttpServletResponse response) {
        log.info("Save Token And Set Cookie ...!");
        String jti = RandomNumberGenerator.generateRandomNumber();
        Instant now = Instant.now();
        Instant expiry1Hour = now.plus(accessTokenMINUTES, ChronoUnit.MINUTES);
        Instant expiry7Day = now.plus(refreshTokenDAY, ChronoUnit.DAYS);

        Token token = user.getToken();
        if (token == null) {
            token = new Token();
            token.setUser(user);
        }

        token.setJti(jti);
        token.setAccessToken(jwtService.generateAccessToken(user, jti, now, expiry1Hour));
        token.setRefreshToken(jwtService.generateRefreshToken(user, jti, now, expiry7Day));
        token.setExpiredAt(Date.from(now.plus(accessTokenMINUTES, ChronoUnit.MINUTES)));
        token.setRevoked(false);

        setRefreshTokenCookie(response, token.getRefreshToken(), refreshTokenDAY * 24 * 60 * 60, false);

        return tokenRepository.save(token);
    }

    @Override
    public void revokeToken(Token token, HttpServletResponse response) {
        if (token == null || token.isRevoked()) {
            return;
        }
        token.setRevoked(true);
        tokenRepository.save(token);
        setRefreshTokenCookie(response, "", 0, true);
    }

    private void setRefreshTokenCookie(HttpServletResponse response, String tokenValue, int maxAge, boolean secure) {
        Cookie cookie = new Cookie("refreshToken", tokenValue);
        cookie.setHttpOnly(true);
        cookie.setSecure(secure);
        cookie.setDomain("localhost");
        cookie.setPath(StrUtil.SLASH);
        cookie.setMaxAge(maxAge);
        response.addCookie(cookie);
    }
}