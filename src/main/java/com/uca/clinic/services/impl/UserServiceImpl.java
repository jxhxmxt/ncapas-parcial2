package com.uca.clinic.services.impl;

import com.uca.clinic.domain.entities.Rol;
import com.uca.clinic.domain.entities.Token;
import com.uca.clinic.domain.entities.User;
import com.uca.clinic.exception.UserNotFoundException;
import com.uca.clinic.repositories.TokenRepository;
import com.uca.clinic.repositories.UserRepository;
import com.uca.clinic.services.UserService;
import com.uca.clinic.utils.JWTTools;
import jakarta.transaction.Transactional;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;


@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final TokenRepository tokenRepository;

    private final JWTTools jwtTools;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder, TokenRepository tokenRepository, JWTTools jwtTools) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.tokenRepository = tokenRepository;
        this.jwtTools = jwtTools;
    }

    @Override
    public User signUp(User user) {
        if(userRepository.existsByEmailOrUsername(user.getEmail(), user.getUsername())) {
            throw new RuntimeException("Email or username already in use");
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User update(User user) {
        return userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void updateRole(Long userId, Set<Rol> roles) {
        User userToUpdate = userRepository.findById(userId).orElse(null);
        if(userToUpdate == null) {
            throw new RuntimeException("User not found");
        }
        userToUpdate.setRoles(roles);
    }


    //    Token management
    @Override
    @Transactional(rollbackOn = Exception.class)
    public Token registerToken(User user) throws Exception {
        cleanTokens(user);

        String tokenStr = jwtTools.generateToken(user);
        Token token = new Token(tokenStr, user);

        tokenRepository.save(token);


        return token;
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public Boolean isTokenValid(User user, String token) {
        try{
            cleanTokens(user);
            List<Token> tokens = tokenRepository.findByUserAndActive(user, true);

            tokens.stream()
                    .filter(_token -> _token.getContent().equals(token))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Token not found"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional(rollbackOn = Exception.class)
    public void cleanTokens(User user) throws Exception {
        List<Token> userTokens = tokenRepository.findByUserAndActive(user, true);
        for(Token token : userTokens) {
            if(!jwtTools.verifyToken(token.getContent())) {
                token.setActive(false);
                tokenRepository.save(token);
            }
        }
    }

    @Override
    public User findOneByIdentifier(String identifier) {
        return userRepository.findByEmailOrUsername(identifier, identifier);
    }

    @Override
    public User findUserAuthenticated() {
       String identifier = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

         return userRepository.findByEmailOrUsername(identifier, identifier);
    }

    @Override
    public User signIn(User user) {
        User userFound = userRepository.findByEmailOrUsername(user.getEmail(), user.getUsername());
        if(userFound == null) {
            throw new UserNotFoundException("User not found");
        }
        if(!bCryptPasswordEncoder.matches(user.getPassword(), userFound.getPassword())) {
            throw new IllegalArgumentException("Invalid password");
        }
        return userFound;
    }

    @Override
    public void assignRole(User user, Rol rol)  {
        user.getRoles().add(rol);
        userRepository.save(user);
    }

    @Override
    public void save(User paciente) {
        userRepository.save(paciente);
    }

    @Override
    public void removeRole(User user, Rol rol) {
        user.getRoles().remove(rol);
        userRepository.save(user);
    }


}
