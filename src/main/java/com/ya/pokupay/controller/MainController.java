package com.ya.pokupay.controller;

import com.ya.pokupay.model.Advert;
import com.ya.pokupay.model.User;
import com.ya.pokupay.service.AdvertService;
import com.ya.pokupay.service.EmailService;
import com.ya.pokupay.service.SecurityService;
import com.ya.pokupay.service.UserService;
import com.ya.pokupay.validator.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
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
    private BCryptPasswordEncoder bCryptPasswordEncoder;

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

    @RequestMapping(value =  "/addAdvert", method = RequestMethod.GET)
    public String addAdvertPage(Model model) {
        model.addAttribute("advert", new Advert());
        return "addAdvert";
    }

    @ResponseBody
    @RequestMapping(value="/saveAdvert", method = RequestMethod.POST)
    public String uploadPage (@RequestBody Advert obyavleniye) {
        System.out.println("dddd");
        System.out.println(obyavleniye);
        this.advertService.addAdvert(obyavleniye);
        return "success";
    }

    @RequestMapping(value = "/obyavleniye/{advertid}", method = RequestMethod.GET)
    public String userPage(@PathVariable("advertid") Integer advertid, Model model, HttpServletRequest request) throws Exception {
        Advert advert = advertService.getAdvertById(advertid);
        model.addAttribute("advert", advert);

        List<Integer> imagesCount = Arrays.asList(new Integer[advert.getImagesCount()]);
        model.addAttribute("imagesCount", imagesCount);
        return "advertPage";
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public String handleMyException(Exception  exception) {
        return "admin";
    }

    @ResponseBody
    @RequestMapping(value="/uploadImages/{user}", method = RequestMethod.POST)
    public String uploadImages (@PathVariable("user") String username, MultipartHttpServletRequest request) throws Exception {
        List<MultipartFile> files = request.getFiles("files");
        String contextPath = request.getSession().getServletContext().getRealPath("");
        File directory = new File(contextPath.substring(0,contextPath.length() - 17) + "/src/main/webapp/resources/uploadImages/" + username + "/");

        for (int i = 0; i < files.size(); i++) {
            try {
                MultipartFile multiFile = files.get(i);
                String fileName = i + ".jpg";

                //making directories for our required path.
                byte[] bytes = multiFile.getBytes();
                directory.mkdirs();

                // saving the file
                File file = new File(directory + System.getProperty("file.separator") + fileName);
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
                stream.write(bytes);
                stream.close();
            } catch (Exception e) {
                e.printStackTrace();
                throw new Exception("Error while loading the file");
            }
        }
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
            HttpServletRequest request) throws Exception {

        User user = userService.findByUsername(username);
        if (user.getToken() != null) {
            if (user.getToken().equals(token)) {
                userService.confirmRegistration(username);
            }
        }
        securityService.autoLogin(user);
        return "confirmRegistration";
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

}
