package com.ya.pokupay.service;

import java.util.List;

import com.ya.pokupay.model.Advert;

public interface AdvertService {
    public List<Advert> listAdverts(String category);
}
