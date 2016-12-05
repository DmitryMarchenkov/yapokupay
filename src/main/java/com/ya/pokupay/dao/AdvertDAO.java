package com.ya.pokupay.dao;

import java.util.List;

import com.ya.pokupay.model.Advert;

public interface AdvertDAO {

    List<Advert> listAdverts(String category);

    void addAdvert(Advert a);

    Advert getAdvertById(Integer id);
}
