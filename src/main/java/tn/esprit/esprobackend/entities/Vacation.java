package tn.esprit.esprobackend.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Date;

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
