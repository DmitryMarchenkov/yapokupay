package com.ya.pokupay.service;

import java.util.List;

import com.ya.pokupay.model.Advert;

public interface AdvertService {
    List<Advert> listAdverts(String category);

    List<Advert> searchAdvert(String searchQuery) throws InterruptedException;

    Advert addAdvert(Advert advert);

    void increaseViewCounter(Advert advert);

    Advert getAdvertById(Integer id);
}
