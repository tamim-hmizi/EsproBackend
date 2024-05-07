package tn.esprit.esprobackend.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;
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
    private Long moneytocollect;
    @Lob
    @Column(length = 1000000)
    private byte[] displayPicture;



    @OneToMany(mappedBy = "fundraiser", cascade = CascadeType.ALL)
    @JsonBackReference
    private List<Donation> donations = new ArrayList<>();


}
