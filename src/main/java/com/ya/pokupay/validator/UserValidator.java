package com.ya.pokupay.validator;

import com.ya.pokupay.model.User;
import com.ya.pokupay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Service("userValidator")
public class UserValidator implements Validator{

    @Autowired
    private UserService userService;

    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        User user = (User) o;

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "This field is required.");
        if (user.getUsername().length() < 5 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Username must be between 5 and 32 characters.");
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Such username already exists.");
        }

        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "This field is required.");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Username must be between 8 and 32 characters.");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("password", "Password don't match.");
        }


    }
}
