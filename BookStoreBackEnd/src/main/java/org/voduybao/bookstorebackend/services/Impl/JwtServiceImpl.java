package org.voduybao.bookstorebackend.services.Impl;

import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.voduybao.bookstorebackend.dao.entities.User;
import org.voduybao.bookstorebackend.services.JwtService;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseErrors;
import org.voduybao.bookstorebackend.tools.exceptions.error.ResponseException;

import java.text.ParseException;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@Slf4j(topic = "JWT-SERVICE")
public class JwtServiceImpl implements JwtService {

    @Value("${jwt.base64-secret}")
    private String secretKey;

    @Override
    public String generateAccessToken(User user, String jti, Instant issuedAt, Instant expiry) {
        return generateToken(user, jti, issuedAt, expiry, buildAuthority(user));
    }

    @Override
    public String generateRefreshToken(User user, String jti, Instant issuedAt, Instant expiry) {
        return generateToken(user, jti, issuedAt, expiry, null);
    }

    @Override
    public boolean extractTokenExpired(String token) {
        try {
            long expirationTime = SignedJWT.parse(token)
                    .getJWTClaimsSet().getExpirationTime().getTime();
            long currentTime = System.currentTimeMillis();
            return currentTime > expirationTime;
        } catch (ParseException e) {
            throw new ResponseException(ResponseErrors.TOKEN_INVALID);
        }
    }

    @Override
    public String extractAccessTokenEmail(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.getJWTClaimsSet().getSubject();
        } catch (ParseException e) {
            throw new ResponseException(ResponseErrors.TOKEN_INVALID);
        }
    }

    @Override
    public String extracJti(String token) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            return signedJWT.getJWTClaimsSet().getJWTID();
        } catch (ParseException e) {
            throw new ResponseException(ResponseErrors.TOKEN_INVALID);
        }
    }

    @Override
    public boolean verificationToken(String token, User user) {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            var jwtId = signedJWT.getJWTClaimsSet().getJWTID();
            var email = signedJWT.getJWTClaimsSet().getSubject();
            var expiration = signedJWT.getJWTClaimsSet().getExpirationTime();

            if (!Objects.equals(email, user.getEmail()))
                throw new ResponseException(ResponseErrors.TOKEN_INVALID);
            if (expiration.before(new Date()))
                throw new ResponseException(ResponseErrors.TOKEN_EXPIRED);
            if (!Objects.equals(jwtId, user.getToken().getJti()))
                throw new ResponseException(ResponseErrors.NOT_FOUND_ID_AUTHOR);

            return signedJWT.verify(new MACVerifier(secretKey));
        } catch (ParseException | JOSEException e) {
            throw new ResponseException(ResponseErrors.TOKEN_INVALID);
        }
    }

    public String generateToken(User user, String jti, Instant issuedAt, Instant expiry, String authorityClaim) {
        JWSHeader header = new JWSHeader(JWSAlgorithm.HS512);

        JWTClaimsSet.Builder claimsBuilder = new JWTClaimsSet.Builder()
                .subject(user.getEmail())
                .issuer("BookStore")
                .issueTime(Date.from(issuedAt))
                .expirationTime(Date.from(expiry))
                .jwtID(jti);

        if (authorityClaim != null) {
            claimsBuilder.claim("Authority", authorityClaim);
        }

        JWTClaimsSet claimsSet = claimsBuilder.build();

        Payload payload = new Payload(claimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(header, payload);

        try {
            jwsObject.sign(new MACSigner(secretKey));
        } catch (JOSEException e) {
            throw new RuntimeException("Error signing JWT", e);
        }

        return jwsObject.serialize();
    }

    private String buildAuthority(User user) {
        return user.getRoles().stream()
                .map(role -> role.getRoleName().name())
                .collect(Collectors.joining(", "));
    }

}
