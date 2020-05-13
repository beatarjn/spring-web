package workshop.springb.web.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/*
    TODO 4 imlementacja ConstraintValidator, parametryzowany interfejs <adnotacja, typ-pola>
 */
public class IsNotSauronValidator implements ConstraintValidator<IsNotSauron, String> {

    public boolean isValid(String name, ConstraintValidatorContext context) {
        return !"sauron".equalsIgnoreCase(name);
    }

}
