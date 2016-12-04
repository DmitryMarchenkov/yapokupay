package com.ya.pokupay.service;

import java.io.File;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

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
    public List<Advert> listAdverts(String category) {
        return this.advertDAO.listAdverts(category);
    }

    @Override
    public void addAdvert(Advert advert) {
        Date dateNow = new Date();
        SimpleDateFormat dateFormat = null;

        dateFormat = new SimpleDateFormat("dd MMMM yyyy в HH:mm:ss", myDateFormatSymbols );
        advert.setDate(dateFormat.format(dateNow));

        User user = userService.findByUsername(advert.getAuthorid());
        String username = advert.getAuthorid();

        Long authorId = user.getId();

        advert.setAuthorid(authorId.toString());
        advert.setAuthorUsername(username);


        this.advertDAO.addAdvert(advert);
    }

    private static DateFormatSymbols myDateFormatSymbols = new DateFormatSymbols(){

        @Override
        public String[] getMonths() {
            return new String[]{"января", "февраля", "марта", "апреля", "мая", "июня",
                    "июля", "августа", "сентября", "октября", "ноября", "декабря"};
        }

    };
}
