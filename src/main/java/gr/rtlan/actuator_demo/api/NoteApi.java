package gr.rtlan.actuator_demo.api;

import java.util.ArrayList;

import jakarta.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gr.rtlan.actuator_demo.dto.NoteRequestDto;
import gr.rtlan.actuator_demo.dto.NoteResponseDto;
import gr.rtlan.actuator_demo.service.NoteService;

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
    // @PreAuthorize("hasRole(T(gr.rtlan.actuator_demo.auth.Permissions).ROLE_USER)")
    public ResponseEntity<NoteResponseDto> createNote(@RequestBody @Valid NoteRequestDto noteRequestDto) {
        logger.info("Creating note");
        NoteResponseDto noteResponseDto = noteService.addNote(noteRequestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(noteResponseDto);
    }

    @GetMapping()
    public ArrayList<NoteResponseDto> getAllNotes() {
        logger.info("getAllNotes");
        ArrayList<NoteResponseDto> notes = noteService.getNotes();
        return notes;
    }

}
