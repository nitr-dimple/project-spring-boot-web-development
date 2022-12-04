package com.neu.dimple.houserentalapplication.validator;

import com.neu.dimple.houserentalapplication.pojo.House;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Component
public class HouseValidator implements Validator {
    @Override
    public boolean supports(Class<?> clazz) {
        return House.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "houseno", "empty-houseno", "HouseNo can not be empty");
        ValidationUtils.rejectIfEmpty(errors, "totalarea", "empty-totalarea", "Total Area can not be empty");
//        ValidationUtils.rejectIfEmpty(errors, "startdate", "empty-startdate", "Start Date can not be empty");
//        ValidationUtils.rejectIfEmpty(errors, "enddate", "empty-enddate", "End Date can not be empty");
        ValidationUtils.rejectIfEmpty(errors, "bedrooms", "empty-bedrooms", "Number of Bedrooms can not be empty");
        ValidationUtils.rejectIfEmpty(errors, "bathrooms", "empty-bathrooms", "Number of Bathrooms can not be empty");
        ValidationUtils.rejectIfEmpty(errors, "balcony", "empty-balcony", "Number of Balcony can not be empty");
        ValidationUtils.rejectIfEmpty(errors, "livinrooms", "empty-livinrooms", "Number of Living rooms can not be empty");
        ValidationUtils.rejectIfEmpty(errors, "advancepayment", "empty-advancepayment", "Advance Payment can not be empty");
        ValidationUtils.rejectIfEmpty(errors, "monthrent", "empty-monthrent", "Month-Rent can not be empty");
    }
}
