package gr.rtlan.actuatordemo.validator.constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import gr.rtlan.actuatordemo.validator.NoteTitleValidator;
import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = NoteTitleValidator.class)
@Target({ElementType.FIELD, ElementType.PARAMETER, ElementType.ANNOTATION_TYPE, ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidNoteTitle {
    String message() default "Invalid Note Title";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
