package workshop.springb.web.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsNotSarumanValidator implements ConstraintValidator<IsNotSaruman, String> {

    public boolean isValid(String name, ConstraintValidatorContext context) {
        return !"saruman".equalsIgnoreCase(name);
    }

}