package pl.kurs.test6cars.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FuelTypeValidator implements ConstraintValidator<FuelType, String> {
    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        try {
            pl.kurs.test6cars.model.FuelType.valueOf(s);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
