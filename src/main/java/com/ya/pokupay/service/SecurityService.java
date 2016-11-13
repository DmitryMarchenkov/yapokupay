package com.ya.pokupay.service;

public interface SecurityService {

    String findLoggedInUsername();

    void autoLogin(String username, String password);
}