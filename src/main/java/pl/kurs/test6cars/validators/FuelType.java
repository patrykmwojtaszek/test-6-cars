package pl.kurs.test6cars.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {FuelTypeValidator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface FuelType {

    String message() default "Not a supported fuel type!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
