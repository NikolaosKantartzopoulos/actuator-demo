package gr.rtlan.actuatordemo.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import gr.rtlan.actuatordemo.dto.NoteRequestDto;

public class NoteBodyValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return NoteRequestDto.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        NoteRequestDto noteRequestDto = (NoteRequestDto) target;
        if (noteRequestDto.getBody().equals("Invalid body")) {
            errors.rejectValue("body", "note.body.invalid");
        }
    }
}
