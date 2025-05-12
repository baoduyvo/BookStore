package org.voduybao.bookstorebackend.tools.security.password;

import lombok.experimental.NonFinal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PasswordUtils {
    private static final Logger logger = LoggerFactory.getLogger(PasswordUtils.class);

    private final PasswordEncoder passwordEncoder;

    public PasswordUtils(PasswordEncoder _passwordEncoder) {
        this.passwordEncoder = _passwordEncoder;
    }

    @NonFinal
    @Value("${key.password}")
    private static String keyPassword;

    public static boolean checkPassword(String plainPassword, String hashedPassword) {
        try {
            return BCrypt.checkpw(keyPassword + plainPassword, hashedPassword);
        } catch (Exception e) {
            logger.error("Password comparison error", e);
            return false;
        }
    }

    public String hashPassword(String plainPassword) {
        return passwordEncoder.encode(keyPassword + plainPassword);
    }

}