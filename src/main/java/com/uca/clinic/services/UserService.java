package com.uca.clinic.services;

import com.uca.clinic.domain.entities.User;

public interface UserService {
    User signUp(User user);
    User findByEmail(String email);
    User findById(Long id);
    User update(User user);
    void delete(Long id);
}
