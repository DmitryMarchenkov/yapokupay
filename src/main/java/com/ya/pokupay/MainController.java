package com.ya.pokupay;

import com.ya.pokupay.model.Advert;
import com.ya.pokupay.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    private AdvertService advertService;


    @Autowired(required=true)
    @Qualifier(value="advertService")
    public void setAdvertService(AdvertService as){
        this.advertService = as;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value =  "/auto", method = RequestMethod.GET)
    public String autoPage(Model model) {
        model.addAttribute("advert", new Advert());
        model.addAttribute("listAdverts", this.advertService.listAdverts());
        System.out.println("123 " + this.advertService.listAdverts());
        System.out.println("home");
        return "index";
    }
}
