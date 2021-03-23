package workshop.springb.web.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class IsNotSarumanValidator implements ConstraintValidator<IsNotSaruman, String> {

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return !"saruman".equalsIgnoreCase(name);
    }
}
