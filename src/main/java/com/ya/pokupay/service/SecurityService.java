package com.ya.pokupay.service;


import com.ya.pokupay.model.User;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(User user);
}