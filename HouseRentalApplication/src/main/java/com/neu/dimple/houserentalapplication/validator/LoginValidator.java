package com.neu.dimple.houserentalapplication.validator;

import com.neu.dimple.houserentalapplication.pojo.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Component
public class LoginValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "password", "empty-password", "Password can not be empty");
        ValidationUtils.rejectIfEmpty(errors, "usertype", "empty-usertype", "Usertype can not be empty");
        ValidationUtils.rejectIfEmpty(errors, "email", "empty-email", "Email can not be empty");
    }
}
