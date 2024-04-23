package tn.esprit.esprobackend.entities;

import jakarta.persistence.*;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String descriptionP;
    private String subjectP;
    private Date dateP;
    private TypeP TypeP;
    // Le champ pour le lien (URL)
    private String link;

    @ManyToMany( cascade = CascadeType.ALL, fetch = FetchType.LAZY)

    private Set<RDIMember> chercheurs = new HashSet<>();
}
