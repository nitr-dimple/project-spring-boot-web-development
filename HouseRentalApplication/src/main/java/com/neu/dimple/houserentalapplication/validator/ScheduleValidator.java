package com.neu.dimple.houserentalapplication.validator;

import com.neu.dimple.houserentalapplication.pojo.Schedule;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * @author Dimpleben Kanjibhai Patel
 */

@Component
public class ScheduleValidator implements Validator {


    @Override
    public boolean supports(Class<?> clazz) {
        return Schedule.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {

        ValidationUtils.rejectIfEmpty(errors, "name", "empty-name", "name can not be empty");
        ValidationUtils.rejectIfEmpty(errors, "email", "empty-email", "email can not be empty");
        ValidationUtils.rejectIfEmpty(errors, "phonenumber", "empty-phonenumber", "phonenumber can not be empty");
        ValidationUtils.rejectIfEmpty(errors, "scheduleDate", "empty-scheduleDate", "scheduleDate can not be empty");
        ValidationUtils.rejectIfEmpty(errors, "scheduledTime", "empty-scheduledTime", "scheduledTime can not be empty");

    }
}
