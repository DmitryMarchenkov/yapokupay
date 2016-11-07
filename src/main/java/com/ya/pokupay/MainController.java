package com.ya.pokupay;

import com.ya.pokupay.model.Product;
import com.ya.pokupay.service.AdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ya.pokupay.service.ProductService;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    private ProductService productService;
    private AdvertService advertService;

    @Autowired(required=true)
    @Qualifier(value="productService")
    public void setPersonService(ProductService ps){
        this.productService = ps;
    }

    @Autowired(required=true)
    @Qualifier(value="advertService")
    public void setAdvertService(AdvertService as){
        this.advertService = as;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value =  {"/auto"}, method = RequestMethod.GET)
    public String homePage() {

        System.out.println("home");
        return "index";
    }
}
