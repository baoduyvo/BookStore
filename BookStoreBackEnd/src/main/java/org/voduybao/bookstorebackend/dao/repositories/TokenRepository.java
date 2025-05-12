package org.voduybao.bookstorebackend.dao.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.voduybao.bookstorebackend.dao.entities.Token;

import java.util.Optional;

public interface TokenRepository extends CrudRepository<Token, Integer> {
    @Query("SELECT t FROM Token t WHERE t.jti = :jti")
    Optional<Token> findByJti(@Param("jti") String jti);

}