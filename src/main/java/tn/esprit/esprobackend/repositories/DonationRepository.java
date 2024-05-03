package tn.esprit.esprobackend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.esprobackend.entities.Donation;


@Repository
public interface DonationRepository extends JpaRepository<Donation,Long>   {

}
