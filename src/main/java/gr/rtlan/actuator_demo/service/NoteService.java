package gr.rtlan.actuator_demo.service;

import java.util.ArrayList;

import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import gr.rtlan.actuator_demo.domain.Note;
import gr.rtlan.actuator_demo.dto.NoteRequestDto;
import gr.rtlan.actuator_demo.dto.NoteResponseDto;
import gr.rtlan.actuator_demo.repository.NoteRepository;

@Service
public class NoteService {

    private final ModelMapper modelMapper;
    Logger logger = LoggerFactory.getLogger(NoteService.class);

    private final NoteRepository noteRepository;
    private final ConversionService conversionService;

    public NoteService(NoteRepository noteRepository, ConversionService conversionService, ModelMapper modelMapper) {
        this.noteRepository = noteRepository;
        this.conversionService = conversionService;
        this.modelMapper = modelMapper;
    }

    public NoteResponseDto addNote(NoteRequestDto noteRequestDto) {
        logger.info("Note Service: Create a note");
        Note createdNote = noteRepository.save(modelMapper.map(noteRequestDto, Note.class));
        return modelMapper.map(createdNote, NoteResponseDto.class);
    }

    public ArrayList<NoteResponseDto> getNotes() {
        logger.info("Note Service: Get all notes");
        return new ArrayList<NoteResponseDto>();
    }
}
