package tn.esprit.esprobackend.entities;

import tn.esprit.esprobackend.entities.Affectation;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Classroom {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classroom")
    @JsonBackReference
    private Set<Affectation> affectations;
}
