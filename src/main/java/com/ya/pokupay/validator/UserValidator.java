package com.ya.pokupay.validator;

import com.ya.pokupay.model.User;
import com.ya.pokupay.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
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

        /*Validate name*/
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "Required");
        if (user.getName().length() < 2 || user.getName().length() > 32) {
            errors.rejectValue("Name", "Size.userForm.name");
        }

        /*Validate phone*/
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "phone", "Required");

        /*Validate username*/
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "username", "Required");
        if (user.getUsername().length() < 5 || user.getUsername().length() > 32) {
            errors.rejectValue("username", "Size.userForm.username");
        }

        if (userService.findByUsername(user.getUsername()) != null) {
            errors.rejectValue("username", "Duplicate.userForm.username");
        }

        /*Validate password*/
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password", "Required");
        if (user.getPassword().length() < 8 || user.getPassword().length() > 32) {
            errors.rejectValue("password", "Size.userForm.password");
        }

        if (!user.getConfirmPassword().equals(user.getPassword())) {
            errors.rejectValue("confirmPassword", "Different.userForm.password");
        }

        /*Validate email*/
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "Required");
        if (user.getEmail().length() < 8 || user.getEmail().length() > 40) {
            errors.rejectValue("email", "Size.userForm.email");
        }
    }
}
