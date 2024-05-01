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

public class Calendar {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;

    private String academic_year;

    private Date start_date;

    private Date end_date;

    private String archive;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "calendar")
    @JsonBackReference
    private Set<Affectation> affectations;
}
