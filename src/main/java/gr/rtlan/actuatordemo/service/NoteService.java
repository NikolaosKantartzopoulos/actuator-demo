package gr.rtlan.actuatordemo.service;

import java.time.Clock;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import gr.rtlan.actuatordemo.domain.Note;
import gr.rtlan.actuatordemo.dto.NoteRequestDto;
import gr.rtlan.actuatordemo.dto.NoteResponseDto;
import gr.rtlan.actuatordemo.repository.NoteRepository;

@Service
public class NoteService {

    private final ModelMapper modelMapper;
    private final Clock clock;
    Logger logger = LoggerFactory.getLogger(NoteService.class);

    private final NoteRepository noteRepository;

    public NoteService(NoteRepository noteRepository, ModelMapper modelMapper, Clock clock) {
        this.noteRepository = noteRepository;
        this.modelMapper = modelMapper;
        this.clock = clock;
    }

    public NoteResponseDto addNote(NoteRequestDto noteRequestDto) {
        logger.info("Note Service: Create a note");

        ZonedDateTime zonedDateTime = ZonedDateTime.ofInstant(clock.instant(), ZoneId.systemDefault());
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE dd MMMM yyyy HH:mm:ss");
        String formattedDate = zonedDateTime.format(formatter);
        noteRequestDto.setBody("This note was created at " + formattedDate);

        Note createdNote = noteRepository.save(modelMapper.map(noteRequestDto, Note.class));
        return modelMapper.map(createdNote, NoteResponseDto.class);
    }

    public ArrayList<NoteResponseDto> getNotes() {
        logger.info("Note Service: Get all notes");
        ArrayList<Note> notes = (ArrayList<Note>) noteRepository.findAll();
        return notes.stream()
            .map(note -> modelMapper.map(note, NoteResponseDto.class))
            .collect(Collectors.toCollection(ArrayList::new));
    }

    public void deleteNote(String id) {
        logger.info("Note Service: Delete a note with id {}", id);
        noteRepository.deleteById(Long.parseLong(id));
    }

    public void deleteOldestNotes(Integer limit) {
        logger.info("Note Service: Delete first {} notes", limit);
        Pageable pageable = PageRequest.of(0, limit);
        List<Note> oldestNotes = noteRepository.findOldestNotes(pageable, limit);

        if (!oldestNotes.isEmpty()) {
            noteRepository.deleteNotes(oldestNotes);
            logger.info("Deleted {} oldest notes.", oldestNotes.size());
        } else {
            logger.info("No notes to delete.");
        }
    }
}
