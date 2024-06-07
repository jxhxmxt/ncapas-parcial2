package com.uca.clinic.config;


import com.uca.clinic.domain.entities.User;
import com.uca.clinic.services.UserService;
import com.uca.clinic.utils.JWTTokenFilter;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {


    private final PasswordEncoder passwordEncoder;

    private final UserService userService;

    private final JWTTokenFilter jwtTokenFilter;

    @Autowired
    public WebSecurityConfig(PasswordEncoder passwordEncoder, UserService userService, JWTTokenFilter jwTokenFilter) {
        this.passwordEncoder = passwordEncoder;
        this.userService = userService;
        this.jwtTokenFilter = jwTokenFilter;
    }

    @Bean
    AuthenticationManager authenticationManager(HttpSecurity http) throws  Exception{
        AuthenticationManagerBuilder managerBuilder
                = http.getSharedObject(AuthenticationManagerBuilder.class);

        managerBuilder
                .userDetailsService( identifier -> {
                    User user = userService.findOneByIdentifier(identifier);

                    if (user == null){
                        throw new UsernameNotFoundException("User not found" + identifier);
                    }
                    return user;
                })
                .passwordEncoder(passwordEncoder);

        return managerBuilder.build();
    }

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.httpBasic(Customizer.withDefaults()).csrf(AbstractHttpConfigurer::disable);

        http.authorizeHttpRequests(auth ->
                auth
                        .requestMatchers("/api/**").permitAll()
                        .anyRequest().authenticated()
        );

        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.exceptionHandling(handling -> handling.authenticationEntryPoint(
                (request, response, authException) -> {
                    response.sendError(
                            HttpServletResponse.SC_UNAUTHORIZED,
                            "Auth fail"
                    );
                }
        ));


        http.addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);


        return http.build();



    }



}
