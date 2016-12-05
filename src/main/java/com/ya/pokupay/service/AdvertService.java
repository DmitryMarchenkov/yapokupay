package com.ya.pokupay.service;

import java.util.List;

import com.ya.pokupay.model.Advert;

public interface AdvertService {
    List<Advert> listAdverts(String category);

    void addAdvert(Advert a);

    Advert getAdvertById(Integer id);
}
