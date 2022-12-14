package com.neu.dimple.houserentalapplication.validator;

import com.neu.dimple.houserentalapplication.pojo.Visit;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Component
public class VisitorValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Visit.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "email", "empty-email", "email can not be empty");
        ValidationUtils.rejectIfEmpty(errors, "phonenumber", "empty-phonenumber", "phonenumber can not be empty");
        ValidationUtils.rejectIfEmpty(errors, "description", "empty-description", "description can not be empty");

    }
}