package tn.esprit.esprobackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Affectation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private int semester;

    @ManyToOne
    private Calendar calendar;

    @ManyToOne
    private Module module;

//    @ManyToOne
//    private User user;

    @ManyToOne
    private Classroom classroom;

}
