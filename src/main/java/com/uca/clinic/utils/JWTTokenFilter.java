package com.uca.clinic.utils;


import com.uca.clinic.domain.entities.User;
import com.uca.clinic.services.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
public class JWTTokenFilter extends OncePerRequestFilter{

    private final UserService userService;
    private final JWTTools jwtTools;

    @Autowired
    public JWTTokenFilter(UserService userService, JWTTools jwtTools) {
        this.userService = userService;
        this.jwtTools = jwtTools;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tokenHeader = request.getHeader("Authorization");
        String identifier = null;
        String token = null;


        if (tokenHeader != null && tokenHeader.startsWith("Bearer ") && tokenHeader.length() > 7) {

//            Remove the "Bearer " prefix from the token
            token = tokenHeader.substring(7);

            try {
                identifier = jwtTools.getIdentifierFrom(token);
            } catch (IllegalArgumentException e) {
                log.error("Unable to get JWT Token");
            } catch (ExpiredJwtException e) {
                log.error("JWT Token has expired");
            } catch (MalformedJwtException e) {
                log.error("JWT Token is malformed");
            }
        }
        else {
            log.warn("JWT Token does not begin with Bearer String");

        }


        if( identifier != null && SecurityContextHolder.getContext().getAuthentication() == null){

            User user = userService.findOneByIdentifier(identifier);


            if(user != null ){
                Boolean tokenValidity = userService.isTokenValid(user, token);

                if(tokenValidity){
                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());


                    authToken.setDetails(
                            new WebAuthenticationDetailsSource().buildDetails(request)
                    );
                    SecurityContextHolder
                            .getContext()
                            .setAuthentication(authToken);
                }
            }
        }

        filterChain.doFilter(request, response);
    }
}
