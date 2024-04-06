package tn.esprit.esprobackend.entities;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Level {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Long classroom_number ;
    @OneToMany(cascade = CascadeType.ALL, mappedBy="level")
    private Set<Classroom> classrooms;


}
