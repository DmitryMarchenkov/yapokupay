package com.ya.pokupay.controller;

import com.ya.pokupay.model.Advert;
import com.ya.pokupay.model.User;
import com.ya.pokupay.service.AdvertService;
import com.ya.pokupay.service.SecurityService;
import com.ya.pokupay.service.UserService;
import com.ya.pokupay.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
//@RequestMapping("/")
public class MainController {

    @Autowired
    private AdvertService advertService;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @RequestMapping(value =  {"/", "/all"}, method = RequestMethod.GET)
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

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        System.out.println("logout");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";//You can redirect wherever you want, but generally it's a good practice to show login screen again.
    }

    @ResponseBody
    @RequestMapping(value="/upload", method = RequestMethod.POST)
    public String logoutPage (@RequestBody Advert obyavleniye) {
        System.out.println("Enter: " + obyavleniye);
//        this.advertService.addAdvert(obyavleniye);
        return "success";
    }







    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());

        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getConfirmPassword());

        return "redirect:/all";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        System.out.println("ERROR***************************************************************************");
//        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
//            model.addObject("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }
//        model.addObject("message", "Logged .");

        return "login";
    }

}