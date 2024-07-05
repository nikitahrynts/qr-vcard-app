package by.nces.qr_vcard_app.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class CustomPatternValidator implements ConstraintValidator<CustomPattern, String> {

    private String regexp;

    @Override
    public void initialize(CustomPattern constraintAnnotation) {
        this.regexp = constraintAnnotation.regexp();
    }

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if (s == null || s.trim().isEmpty()) {
            return true;
        }
        return s.matches(regexp);
    }
}
