package by.nces.qr_vcard_app.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = CustomPatternValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CustomPattern {

    String regexp();

    String message() default "Вы не должны увидеть это сообщение!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
