package gr.rtlan.actuator_demo.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(schema = "actuator-demo", name = "note")
@Data
public class Note {
    @Id
    @Column(name = "note_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long noteId;

    @Column(name = "title")
    private String title;

    @Column(name = "body")
    private String body;
}
