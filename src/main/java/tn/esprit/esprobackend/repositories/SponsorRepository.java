package tn.esprit.esprobackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.esprobackend.entities.Sponsor;

public interface SponsorRepository extends JpaRepository<Sponsor,Long> {
}
