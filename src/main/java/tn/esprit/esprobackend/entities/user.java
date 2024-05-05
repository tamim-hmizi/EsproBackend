package tn.esprit.esprobackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long idU;
    private String nameU;
    private String surnameU;
    private String password;
    private String email;
    private Long  telnum;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String img; //Bolb


    // cascade=CascadeType.ALL: non logique si j ajoute un user j ajoute une position : same operation


}
