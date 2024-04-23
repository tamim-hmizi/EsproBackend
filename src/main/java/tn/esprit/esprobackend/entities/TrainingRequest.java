package tn.esprit.esprobackend.entities;

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






public class TrainingRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
        private String theme;


    @Enumerated(EnumType.STRING)
    private UserStatus Status;

    @ManyToOne(cascade = CascadeType.ALL)
    Training Trainings;
    @ManyToOne(cascade = CascadeType.ALL)
    user UserTR;
    @ManyToOne(cascade = CascadeType.ALL)
    RDIMember RDIMember;


}
