package tn.esprit.esprobackend.entities;

import jakarta.persistence.*;
import lombok.*;
import tn.esprit.esprobackend.entities.Donation;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;
    private String password;

    private String email;

    private Role role;


    @OneToMany
            (mappedBy = "user", cascade = CascadeType.ALL)
    private List<Donation> donations;

}
