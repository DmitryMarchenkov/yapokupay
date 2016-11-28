package com.ya.pokupay.dao;


import com.ya.pokupay.model.User;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface UserDAO {
    User findByUsername(String username);

    void save(User user);
}
