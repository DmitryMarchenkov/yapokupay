package com.ya.pokupay.service;

import java.util.List;

import com.ya.pokupay.model.Advert;

public interface AdvertService {
    List<Advert> listAdverts(String category, String orderByCriteria);

    List<Advert> searchAdvert(String searchQuery, String category) throws InterruptedException;

    Advert addAdvert(Advert advert);

    void increaseViewCounter(Advert advert);

    Advert getAdvertById(Integer id);
}
