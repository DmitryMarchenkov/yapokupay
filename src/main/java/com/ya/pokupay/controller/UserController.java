package com.ya.pokupay.controller;

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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private SecurityService securityService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private EmailService emailService;

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
        modelEmail.put("name", userForm.getName());
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

    @ResponseBody
    @RequestMapping(value="/messageToAuthor", method = RequestMethod.POST)
    public String messageToAuthor (@RequestParam String email, @RequestParam String message, @RequestParam String authName) {
        Map<String, Object> modelEmail = new HashMap<>();
        modelEmail.put("from", email);
        modelEmail.put("subject", "Новое приватное сообщение от пользователя на Ya Pokupay!");
        modelEmail.put("to", userService.findByUsername(authName).getEmail());
        modelEmail.put("message", message);
        boolean result = emailService.sendEmail("privateMessage.vm", modelEmail);
        return "Message sent";
    }
}
