package gr.rtlan.actuator_demo.dto;

import jakarta.validation.constraints.NotBlank;

import lombok.Data;

@Data
public class NoteResponseDto {

    private Long noteId;
    private String title;
    private String body;
}
