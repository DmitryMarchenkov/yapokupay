package com.ya.pokupay.service;

import com.ya.pokupay.model.User;

public interface UserService {

    void save(User user);

    User findByUsername(String username);

    void confirmRegistration(String username);
}
