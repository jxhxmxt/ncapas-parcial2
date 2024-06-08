package com.uca.clinic.repositories;

import com.uca.clinic.domain.entities.Token;
import com.uca.clinic.domain.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TokenRepository extends JpaRepository<Token, Long>{
    List<Token> findByUserAndActive(User user, Boolean active);
}
