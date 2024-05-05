package tn.esprit.esprobackend.entities;

import jakarta.persistence.*;
import lombok.*;


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

}
