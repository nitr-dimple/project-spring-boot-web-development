package com.neu.dimple.houserentalapplication.validator;

import com.neu.dimple.houserentalapplication.pojo.Residence;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Component
public class ResidenceValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return Residence.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "state", "empty-state", "State can not be empty");
        ValidationUtils.rejectIfEmpty(errors, "city", "empty-city", "City can not be empty");
        ValidationUtils.rejectIfEmpty(errors, "street", "empty-street", "Street can not be empty");
        ValidationUtils.rejectIfEmpty(errors, "residencename", "empty-residencename", "Residence name can not be empty");
        ValidationUtils.rejectIfEmpty(errors, "zipcode", "empty-zipcode", "Zipcode can not be empty");

    }
}
