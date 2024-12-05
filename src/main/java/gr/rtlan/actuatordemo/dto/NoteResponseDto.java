package gr.rtlan.actuatordemo.dto;

import lombok.Data;

@Data
public class NoteResponseDto {

    private Long noteId;
    private String title;
    private String body;
}
