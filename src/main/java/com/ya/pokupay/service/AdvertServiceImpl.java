package com.ya.pokupay.service;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;

import com.ya.pokupay.model.Advert;
import com.ya.pokupay.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ya.pokupay.dao.AdvertDAO;

@Service
public class AdvertServiceImpl implements AdvertService {

    private AdvertDAO advertDAO;

    @Autowired
    private UserService userService;

    public void setAdvertDAO(AdvertDAO advertDAO) {
        this.advertDAO = advertDAO;
    }

    @Override
    @Transactional
    public List<Advert> listAdverts(String category, String orderByCriteria, String user) {
        List<Advert> advertList = this.advertDAO.listAdverts(category, orderByCriteria, user);
        return advertList;
    }

    @Override
    public Advert addAdvert(Advert advert) {
        Date dateNow = new Date();
        SimpleDateFormat dateFormat = null;

        dateFormat = new SimpleDateFormat("dd MMMM yyyy в HH:mm:ss", myDateFormatSymbols );
        advert.setDate(dateFormat.format(dateNow));

        User user = userService.findByUsername(advert.getAuthorid());
        String username = advert.getAuthorid();

        Long authorId = user.getId();

        advert.setAuthorid(authorId.toString());
        advert.setAuthorUsername(username);
        advert.setViewCounter(0);

        return this.advertDAO.addAdvert(advert);
    }

    @Override
    public String deleteAdvert(Integer advertId) {
        return this.advertDAO.deleteAdvert(advertId);
    }

    @Override
    public void increaseViewCounter(Advert advert) {
        advert.setViewCounter(advert.getViewCounter() + 1);
        this.advertDAO.updateAdvert(advert);
    }

    @Override
    public Advert getAdvertById(Integer id) {
        return this.advertDAO.getAdvertById(id);
    }

    @Override
    public List<Advert> searchAdvert(String searchQuery, String category) throws InterruptedException {
        List<Advert> foundedAdvertList = this.advertDAO.searchAdvert(searchQuery);
        if (category == null) return foundedAdvertList;
        if (category.equals("Все категории")) return foundedAdvertList;

        List<Advert> advertList = new ArrayList<>();
        for (Advert advert : foundedAdvertList) {
            if (advert.getCategory().equals(category)) {
                advertList.add(advert);
            }
        }
        return advertList;
    }

    private static DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols(){

        @Override
        public String[] getMonths() {
            return new String[]{"января", "февраля", "марта", "апреля", "мая", "июня",
                    "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        }

    };
}
