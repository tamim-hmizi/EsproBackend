package tn.esprit.esprobackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class Classroom {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String classroomSalle;
    private String startYear;
    private String endYear;

    @ManyToOne

    Level level;
    @ManyToOne

    Option option;

    @ManyToMany(cascade = CascadeType.ALL)

    private Set<Module> modules;

<<<<<<< HEAD
//   @ManyToMany(cascade = CascadeType.ALL)
//    private Set<Affectation> affectations;


   //@OneToMany(mappedBy="classroom", cascade = CascadeType.ALL)
    //@JsonBackReference
    //private Set <Affectation> affectations;
=======

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "classroom")
    @JsonBackReference
    private Set<Affectation> affectations;


>>>>>>> houssem
}
