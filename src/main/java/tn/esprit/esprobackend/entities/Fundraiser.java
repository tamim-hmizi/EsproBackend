package tn.esprit.esprobackend.entities;

import jakarta.persistence.*;
import lombok.*;
import java.util.Set;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Fundraiser {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String description;
    @Lob
    @Column(length = 1000000)
    private byte[] displayPicture;


    @OneToMany(mappedBy = "fundraiser", cascade = CascadeType.ALL)
    private Set<Donation> donations;

}
