package tn.esprit.esprobackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonIgnore
    private Set<Affectation> affectations;
}
