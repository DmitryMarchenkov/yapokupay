package com.ya.pokupay;

import com.ya.pokupay.model.Advert;
import com.ya.pokupay.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.*;

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
        String category = "Все категории";
        model.addAttribute("category", category);
        model.addAttribute("advert", new Advert());
        model.addAttribute("listAdverts", this.advertService.listAdverts(category));
        return "index";
    }

    @RequestMapping(value =  "/{category}", method = RequestMethod.GET)
    public String autoPage(@PathVariable("category") String category, Model model) {
        String queryCategory;
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
        model.addAttribute("category", queryCategory);
        model.addAttribute("advert", new Advert());
        model.addAttribute("listAdverts", this.advertService.listAdverts(queryCategory));
        return "index";
    }

    @RequestMapping(value =  "/addAdvert", method = RequestMethod.GET)
    public String addAdvertPage(Model model) {
        model.addAttribute("advert", new Advert());
        return "addAdvert";
    }

    @RequestMapping(value =  "/advert/add", method = RequestMethod.POST)
    public String addAdvert(@ModelAttribute("advert") Advert advert) {
//    public String addAdvert(@RequestBody Advert advert) {
        System.out.println("Enter to adding");
        System.out.println("id" + advert.getId());
        System.out.println("title" + advert.getTitle());
        System.out.println("price" + advert.getPrice());
//        if(advert.getId() == 0){
            //new person, add it
            this.advertService.addAdvert(advert);
        return "redirect:/all";
    }



    //        session.close(); logout
}
