package gr.rtlan.actuatordemo.validator;

import java.util.HashSet;
import java.util.Set;

import gr.rtlan.actuatordemo.validator.constraint.ValidNoteTitle;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NoteTitleValidator implements ConstraintValidator<ValidNoteTitle, String> {

    Set<String> invalidTitles = new HashSet<>();

    @Override
    public void initialize(ValidNoteTitle constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
        invalidTitles.add("Invalid title");
        invalidTitles.add("OMG this is an invalid title");
    }

    @Override
    public boolean isValid(String noteTitle, ConstraintValidatorContext constraintValidatorContext) {
        return !invalidTitles.contains(noteTitle);
    }
}
