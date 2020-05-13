package workshop.springb.web.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsNotSarumanValidator.class)
public @interface IsNotSaruman {
    String message() default "Saruman has ben spotted!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}