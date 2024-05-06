package tn.esprit.esprobackend.entities;

import tn.esprit.esprobackend.entities.Affectation;
import jakarta.persistence.*;
import lombok.*;
import java.util.Date;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class Vacation {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long duration;

    private String type;

    private Date start_date;

    private Date end_date;

    @ManyToOne
    private User user;
}
