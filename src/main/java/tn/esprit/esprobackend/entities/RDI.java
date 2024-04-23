package tn.esprit.esprobackend.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RDI {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String theme;
    private String keywords;
    private Date dateCreation;

    @Enumerated(EnumType.STRING)
    private TypeRecherche typeR;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<ResearchAxis> ResearchAxis;
    @JsonIgnore

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Training> Trainings;
    @JsonIgnore

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "RDI", fetch = FetchType.LAZY)
    private Set<RDIMember> RDIMembers;
}
