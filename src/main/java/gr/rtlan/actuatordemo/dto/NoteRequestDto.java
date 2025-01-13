package gr.rtlan.actuatordemo.dto;

import jakarta.validation.constraints.NotBlank;
import gr.rtlan.actuatordemo.validator.constraint.ValidNoteTitle;
import lombok.Data;

@Data
public class NoteRequestDto {

    @NotBlank(message = "Title cannot be blank")
    @ValidNoteTitle
    private String title;

    @NotBlank(message = "Body cannot be blank")
    private String body;
}
