package tn.esprit.esprobackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.esprobackend.entities.Fundraiser;

@Repository
public interface FundraiserRepository extends JpaRepository<Fundraiser,Long> {

}
