package com.uca.clinic.services;

import com.uca.clinic.domain.entities.Rol;
import com.uca.clinic.domain.entities.User;

import java.util.List;
import java.util.Set;

public interface UserService {
    User signUp(User user);
    User findByEmail(String email);
    User findById(Long id);
    User update(User user);
    void delete(Long id);
    List<User> findAll();
    void updateRole(Long userId, Set<Rol> roles);
}
