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
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/note")
public class NoteApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(NoteApi.class);

    private final NoteService noteService;
    private final ConversionService conversionService;

    private final Counter getAllNotesCounter;
    private final Counter createNoteCounter;
    private final Counter deleteNoteCounter;
    private final Counter notDeletingCounter;

    public NoteApi(NoteService noteService, ConversionService conversionService, MeterRegistry meterRegistry) {
        this.noteService = noteService;
        this.conversionService = conversionService;

        this.getAllNotesCounter = Counter.builder("api_note_get")
            .tag("title", "all")
            .description("Get all notes description")
            .register(meterRegistry);

        this.createNoteCounter = Counter.builder("api_note_create")
            .tag("title", "create")
            .description("Create a new note")
            .register(meterRegistry);

        this.deleteNoteCounter = Counter.builder("api_note_delete")
            .tag("title", "delete")
            .description("Delete a note")
            .register(meterRegistry);

        this.notDeletingCounter = Counter.builder("api_note_no_delete")
            .tag("title", "no-delete")
            .description("Create or Get")
            .register(meterRegistry);
    }

    @PostMapping
    @ApiResponse(responseCode = "201", description = "Create a note",
        content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = NoteResponseDto.class)))
    public ResponseEntity<NoteResponseDto> createNote(@RequestBody @Valid NoteRequestDto noteRequestDto) {
        LOGGER.info("Creating note");
        createNoteCounter.increment();
        notDeletingCounter.increment();
        NoteResponseDto noteResponseDto = noteService.addNote(noteRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(noteResponseDto);
    }

    @GetMapping
    @ApiResponse(responseCode = "200", description = "Get all notes")
    public List<NoteResponseDto> getAllNotes() {
        LOGGER.info("getAllNotes");
        getAllNotesCounter.increment();
        notDeletingCounter.increment();
        return noteService.getNotes();
    }

    @DeleteMapping("{id}")
    @ApiResponse(responseCode = "200", description = "Delete a note")
    public void deleteNote(@PathVariable("id") String id) {
        LOGGER.info("Deleting note with id {}", id);
        deleteNoteCounter.increment();
        noteService.deleteNote(id);
    }

    @DeleteMapping("many/{limit}")
    @ApiResponse(responseCode = "200", description = "Deleted the x oldest notes")
    public void deleteOldestNotes(@PathVariable Integer limit) {
        LOGGER.info("Deleting oldest notes");
        noteService.deleteOldestNotes(limit);
    }
}
