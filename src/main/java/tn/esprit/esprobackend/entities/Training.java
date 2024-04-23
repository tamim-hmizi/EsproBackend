package tn.esprit.esprobackend.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor






public class Training {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        private String DescriptionT;
    private Date Date_Creation;
    private String TitleT;
    private String type;
    private String place;
    @ManyToMany(mappedBy="Trainings", cascade = CascadeType.ALL)
    private Set<RDI> RDIs;
    @ManyToMany( cascade = CascadeType.ALL)
    private Set<user> members;
    @ManyToOne(cascade = CascadeType.ALL)
    user Trainer;


}
