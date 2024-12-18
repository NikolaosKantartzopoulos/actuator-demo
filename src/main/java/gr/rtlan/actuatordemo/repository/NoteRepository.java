package gr.rtlan.actuatordemo.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import gr.rtlan.actuatordemo.domain.Note;


public interface NoteRepository extends CrudRepository<Note, Long> {
    @Query("Select n from Note n order by n.createdAt ASC limit :limit")
    List<Note> findOldestNotes(Pageable pageable, Integer limit);

    @Modifying
    @Transactional
    @Query("DELETE FROM Note n WHERE n IN :notes")
    void deleteNotes(@Param("notes") List<Note> notes);
}
