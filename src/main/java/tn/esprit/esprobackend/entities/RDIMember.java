package tn.esprit.esprobackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RDIMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RDIPost position;
    @JsonIgnore

    @ManyToMany(mappedBy = "chercheurs",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Publication> publications = new HashSet<>();
    private Date dateP;


    @ManyToOne
    private RDI RDI;

    @ManyToOne
    private user user;
}
