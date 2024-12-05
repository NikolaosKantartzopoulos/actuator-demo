package gr.rtlan.actuatordemo.repository;

import org.springframework.data.repository.CrudRepository;

import gr.rtlan.actuatordemo.domain.Note;


public interface NoteRepository extends CrudRepository<Note, Long> {
}
