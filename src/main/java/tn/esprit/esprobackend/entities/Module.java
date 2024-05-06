package tn.esprit.esprobackend.entities;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import java.util.Set;


@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Module {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    private Long teaching_hours;
    private Long ects;
    @ManyToMany
    @JoinTable(
            name = "module_skill",
            joinColumns = @JoinColumn(name = "module_id"),
            inverseJoinColumns = @JoinColumn(name = "skill_id"))
    private Set<Skill> skills;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "module")
    @JsonBackReference
    private Set<Affectation> affectations;

}

