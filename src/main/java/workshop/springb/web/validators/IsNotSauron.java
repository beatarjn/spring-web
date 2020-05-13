package workshop.springb.web.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/*
    TODO 3 własna adnotacja, użyta w mechaniźmie walidacji
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsNotSauronValidator.class)
public @interface IsNotSauron {
    String message() default "Sauron has ben spotted!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}