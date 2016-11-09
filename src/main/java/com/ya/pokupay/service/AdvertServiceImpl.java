package com.ya.pokupay.service;

import java.util.List;

import com.ya.pokupay.model.Advert;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ya.pokupay.dao.AdvertDAO;

@Service
public class AdvertServiceImpl implements AdvertService {

    private AdvertDAO advertDAO;

    public void setAdvertDAO(AdvertDAO advertDAO) {
        this.advertDAO = advertDAO;
    }

    @Override
    @Transactional
    public List<Advert> listAdverts(String category) {
        return this.advertDAO.listAdverts(category);
    }

    @Override
    public void addAdvert(Advert a) {
        this.advertDAO.addAdvert(a);
    }
}
