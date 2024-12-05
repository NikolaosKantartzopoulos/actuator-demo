package gr.rtlan.actuatordemo.api;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.rtlan.actuatordemo.dto.NoteRequestDto;
import gr.rtlan.actuatordemo.dto.NoteResponseDto;
import gr.rtlan.actuatordemo.service.NoteService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController()
@RequestMapping("/api/note")
public class NoteApi {

    Logger logger = LoggerFactory.getLogger(NoteApi.class);

    NoteService noteService;
    ConversionService conversionService;

    public NoteApi(NoteService noteService, ConversionService conversionService) {
        this.noteService = noteService;
        this.conversionService = conversionService;
    }

    @PostMapping()
    @ApiResponse(responseCode = "201", description = "Create a note",
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = NoteResponseDto.class)))
    // @PreAuthorize("hasRole(T(gr.rtlan.actuator_demo.auth.Permissions).ROLE_USER)")
    public ResponseEntity<NoteResponseDto> createNote(@RequestBody @Valid NoteRequestDto noteRequestDto) {
        logger.info("Creating note");
        NoteResponseDto noteResponseDto = noteService.addNote(noteRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(noteResponseDto);
    }

    @ApiResponse(responseCode = "200", description = "Get all notes")
    @GetMapping()
    public List<NoteResponseDto> getAllNotes() {
        logger.info("getAllNotes");
        List<NoteResponseDto> notes = noteService.getNotes();
        return notes;
    }

    @ApiResponse(responseCode = "200", description = "Delete a note")
    @DeleteMapping("{id}")
    public void deleteNote(@PathVariable("id") String id) {
        logger.info("Deleting note with id {}", id);
        noteService.deleteNote(id);
    }

}
