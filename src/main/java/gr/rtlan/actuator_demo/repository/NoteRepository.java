package gr.rtlan.actuator_demo.repository;

import org.springframework.data.repository.CrudRepository;

import gr.rtlan.actuator_demo.domain.Note;


public interface NoteRepository extends CrudRepository<Note, Long> {
}
