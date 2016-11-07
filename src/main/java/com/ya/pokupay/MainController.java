package com.ya.pokupay;

import com.ya.pokupay.model.Advert;
import com.ya.pokupay.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.List;

@Controller
@RequestMapping("/**")
public class MainController {

    private AdvertService advertService;


    @Autowired(required=true)
    @Qualifier(value="advertService")
    public void setAdvertService(AdvertService as){
        this.advertService = as;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        String category = "Все категории";
        model.addAttribute("category", category);
        model.addAttribute("advert", new Advert());
        model.addAttribute("listAdverts", this.advertService.listAdverts(category));
        return "index";
    }

    @RequestMapping(value =  "/{category}", method = RequestMethod.GET)
    public String autoPage(@PathVariable("category") String category, Model model) {
        String queryCategory;
        System.out.println("category: " + category);

        switch (category) {
            case "auto":
                queryCategory = "Авто";
                break;
            case "technic":
                queryCategory = "Техника";
                break;
            case "property":
                queryCategory = "Недвижимость";
                break;
            default:
                queryCategory = "Все категории";
                break;
        }

        System.out.println("queryCategory: " + queryCategory);
        model.addAttribute("category", queryCategory);
        model.addAttribute("advert", new Advert());
        model.addAttribute("listAdverts", this.advertService.listAdverts(queryCategory));
        System.out.println("listAdverts: " + this.advertService.listAdverts(queryCategory));
        return "index";
    }
}
