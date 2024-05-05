package tn.esprit.esprobackend.entities;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;
import tn.esprit.esprobackend.entities.Donation;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstname;
    private String lastname;
    private String role;
    private String email;
    private String password;
    private Long telephone_number;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Donation> donations;



}
