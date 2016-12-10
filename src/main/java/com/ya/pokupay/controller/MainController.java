package com.ya.pokupay.controller;

import com.ya.pokupay.model.Advert;
import com.ya.pokupay.model.Image;
import com.ya.pokupay.model.User;
import com.ya.pokupay.service.*;
import com.ya.pokupay.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.*;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    private AdvertService advertService;

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private EmailService emailService;

    @Autowired
    private ImageService imageService;

    @RequestMapping(value =  {"/", "/all"}, method = RequestMethod.GET)
    public String index(Model model) {
        String category = "Все категории";
        model.addAttribute("category", category);
        model.addAttribute("listAdverts", this.advertService.listAdverts(null));
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
            case "clothes":
                queryCategory = "Одежда";
                break;
            default:
                queryCategory = "Все категории";
                break;
        }
        model.addAttribute("category", queryCategory);
        model.addAttribute("listAdverts", this.advertService.listAdverts(queryCategory));
        return "index";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        if (logout != null) {
            model.addAttribute("message", "Logged out successfully.");
        }
        return "login";
    }

    @RequestMapping(value="/logout", method = RequestMethod.GET)
    public String logoutPage (HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/login?logout";
    }

    @RequestMapping(value = "/user/{user}", method = RequestMethod.GET)
    public String userPage(@PathVariable("user") String username, Model model) {
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "userPage";
    }

    @RequestMapping(value = "/obyavleniye/{advertid}", method = RequestMethod.GET)
    public String userPage(@PathVariable("advertid") Integer advertid, Model model) {
        Advert advert = advertService.getAdvertById(advertid);
        model.addAttribute("advert", advert);

        List<Image> images = imageService.getImagesByAdvertId(advertid);

        if (images != null) {
            List<Integer> imagesIds = new ArrayList<>();
            for (int i = 0; i < images.size(); i++) {
                imagesIds.add(images.get(i).getId());
            }
            model.addAttribute("imagesIds", imagesIds);
        } else {
            model.addAttribute("imageNotFound", true);
        }
        return "advertPage";
    }

    @RequestMapping(value = "/imageDisplay", method = RequestMethod.GET)
    public void showImage(@RequestParam("id") Integer advertid, HttpServletResponse response, Model model)
            throws ServletException, IOException, SQLException {
        Image image = imageService.getOneImageByAdvertId(advertid);
        model.addAttribute("imageNotFound", true);
        Blob blob = image.getData();
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(blob.getBytes(1, (int) blob.length()));
        response.getOutputStream().close();
    }

    @RequestMapping(value = "/imagesDisplay", method = RequestMethod.GET)
    public void showImages(@RequestParam("id") Integer imageId, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        Image image = imageService.getImageById(imageId);
        Blob blob = image.getData();
        response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
        response.getOutputStream().write(blob.getBytes(1, (int) blob.length()));
        response.getOutputStream().close();
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "registration";
        }
        String token = generateString(20);
        userForm.setToken(token);

        userService.save(userForm);

        System.out.println("EmailController email is called");
        Map<String, Object> modelEmail = new HashMap<>();
        modelEmail.put("from", "yapokupayfree@gmail.com");
        modelEmail.put("subject", "Подтверждение регистрации на Ya Pokupay!");
        modelEmail.put("to", userForm.getEmail());
        modelEmail.put("userName", userForm.getUsername());
        modelEmail.put("phone", userForm.getPhone());
        modelEmail.put("firstName", userForm.getFirstName());
        modelEmail.put("lastName", userForm.getLastName());
        modelEmail.put("token", token);
        boolean result = emailService.sendEmail("registered.vm", modelEmail);
        return "redirect:/all";
    }

    @RequestMapping(value = "/confirmRegistration/{username}/{token}", method = RequestMethod.GET)
    public String confirmRegistration(
            @PathVariable("username") String username,
            @PathVariable("token") String token,
            Model model,
            HttpServletRequest request) {

        User user = userService.findByUsername(username);
        if (user.getToken() != null) {
            if (user.getToken().equals(token)) {
                userService.confirmRegistration(username);
            }
        }
        securityService.autoLogin(user);
        return "confirmRegistration";
    }

    @RequestMapping(value = "/increaseViewCount/{advertid}", method = RequestMethod.POST)
    public void viewCounter(@PathVariable("advertid") Integer advertid) {

        Advert advert = advertService.getAdvertById(advertid);
        advertService.increaseViewCounter(advert);
//        return "viewCounterIncreased";
    }

    public static String generateString(int length)
    {
        String characters = "qwertyuiopasdfghjklzxcvbnm1234567890QWERTYUIOASDFGHJKLZXCVBNM";
        Random rnd = new Random();
        char[] text = new char[length];
        for (int i = 0; i < length; i++)
        {
            text[i] = characters.charAt(rnd.nextInt(characters.length()));
        }
        return new String(text);
    }


    @RequestMapping(value =  "/addAdvert", method = RequestMethod.GET)
    public String addAdvertPage(Model model) {
        model.addAttribute("advert", new Advert());
        return "addAdvert";
    }

    @ResponseBody
    @RequestMapping(value="/saveAdvert", method = RequestMethod.POST)
    public Advert uploadPage (@RequestBody Advert obyavleniye) {
        return this.advertService.addAdvert(obyavleniye);
    }

    @ResponseBody
    @RequestMapping(value="/uploadImages/{advertid}", method = RequestMethod.POST)
    public String uploadImages (MultipartHttpServletRequest request,
                                @PathVariable("advertid") Integer advertid) {
        List<MultipartFile> files = request.getFiles("files");
        imageService.save(files, advertid);
        return "redirect:/all";
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMyException(Exception  exception) {
        return "admin";
    }
}
