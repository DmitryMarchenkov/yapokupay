package com.ya.pokupay.service;

import java.io.File;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.ya.pokupay.model.Advert;
import com.ya.pokupay.model.Image;
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

    @Autowired
    private ImageService imageService;

    public void setAdvertDAO(AdvertDAO advertDAO) {
        this.advertDAO = advertDAO;
    }

    @Override
    @Transactional
    public List<Advert> listAdverts(String category) {
        List<Advert> advertList = this.advertDAO.listAdverts(category);
//        if (advertList.isEmpty()) return null;
//
//        for (int i = 0; i < advertList.size(); i++) {
//            Advert advert = advertList.get(i);
//            String advertImageBase64;
//            Image advertImage = imageService.getOneImageByAdvertId(advert.getId());
//            if (advertImage != null) {
//                advertImageBase64 = advertImage.getBase64imageFile();
//            } else {
//                Image noImage = imageService.getOneImageByAdvertId(0);
//                advertImageBase64 = noImage.getBase64imageFile();
//            }
//            advert.setBase64imageFile(advertImageBase64);
//        }
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
    public void increaseViewCounter(Advert advert) {
        advert.setViewCounter(advert.getViewCounter() + 1);
        this.advertDAO.updateAdvert(advert);
    }

    @Override
    public Advert getAdvertById(Integer id) {
        return this.advertDAO.getAdvertById(id);
    }

    @Override
    public List<Advert> searchAdvert(String searchQuery) throws InterruptedException {
        return this.advertDAO.searchAdvert(searchQuery);
    }

    private static DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols(){

        @Override
        public String[] getMonths() {
            return new String[]{"января", "февраля", "марта", "апреля", "мая", "июня",
                    "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        }

    };
}
