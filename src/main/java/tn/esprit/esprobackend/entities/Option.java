package tn.esprit.esprobackend.entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long classroom_number ;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="option")
    private Set<Classroom> classrooms;
}
