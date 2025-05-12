package org.voduybao.bookstorebackend.tools.security.jwt;

import java.util.Objects;

import com.nimbusds.jose.JWSAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.stereotype.Component;

import org.voduybao.bookstorebackend.dao.entities.User;
import org.voduybao.bookstorebackend.dao.repositories.UserRepository;
import org.voduybao.bookstorebackend.services.JwtService;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseErrors;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseException;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

@Component
@RequiredArgsConstructor
@Slf4j(topic = "JWT-DECODER")
public class CustomJwtDecoder implements JwtDecoder {

    @Value("${jwt.base64-secret}")
    private String secretKey;

    private NimbusJwtDecoder nimbusJwtDecoder;
    private final JwtService jwtService;
    private final UserRepository userRepository;

    @Override
    public Jwt decode(String token) throws JwtException {
        if (Objects.isNull(nimbusJwtDecoder)) {
            SecretKey key = new SecretKeySpec(secretKey.getBytes(), JWSAlgorithm.HS512.toString());
            nimbusJwtDecoder = NimbusJwtDecoder.withSecretKey(key)
                    .macAlgorithm(MacAlgorithm.HS512)
                    .build();
        }
        String email = jwtService.extractAccessTokenEmail(token);
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new ResponseException(ResponseErrors.ACCOUNT_EXISTS));
        boolean isValid = jwtService.verificationToken(token, user);

        if (isValid) {
            return nimbusJwtDecoder.decode(token);
        }

        return nimbusJwtDecoder.decode(token);

    }
}
