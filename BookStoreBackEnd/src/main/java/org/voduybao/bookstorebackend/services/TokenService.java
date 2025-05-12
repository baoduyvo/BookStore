package org.voduybao.bookstorebackend.services;

import jakarta.servlet.http.HttpServletResponse;
import org.voduybao.bookstorebackend.dao.entities.Token;
import org.voduybao.bookstorebackend.dao.entities.User;


public interface TokenService {
    Token createAndSaveToken(User user, HttpServletResponse response);

    void revokeToken(Token token, HttpServletResponse response);
}
