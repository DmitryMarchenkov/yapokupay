package com.ya.pokupay;

import com.ya.pokupay.model.Product;
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

    @Autowired(required=true)
    @Qualifier(value="productService")
    public void setPersonService(ProductService ps){
        this.productService = ps;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String index(Model model) {
        return "index";
    }

    @RequestMapping(value =  {"/home"}, method = RequestMethod.GET)
    public String homePage() {
        System.out.println("home");
        return "forward:/";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String aboutPage(Model model) {
        System.out.println("about");
        return "forward:/";
    }

    @ResponseBody
    @RequestMapping(value = "/products/listProducts", method = RequestMethod.GET)
    public List<Product> getProducts() {
        List<Product> products = this.productService.listProducts();
        System.out.println("123" + products);
        return products;
    }

    @RequestMapping(value =  {"/products"}, method = RequestMethod.GET)
    public String productsPage() {
        System.out.println("products");
        return "forward:/";
    }

    @RequestMapping(value =  {"/contacts"}, method = RequestMethod.GET)
    public String contactsPage() {
        System.out.println("contacts");
        return "forward:/";
    }
}
