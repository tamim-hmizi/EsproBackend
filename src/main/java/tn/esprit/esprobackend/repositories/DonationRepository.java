package tn.esprit.esprobackend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.esprobackend.entities.Donation;

import java.util.List;


@Repository
public interface DonationRepository extends JpaRepository<Donation,Long>   {

    @Query("SELECT d FROM Donation d JOIN FETCH d.fundraiser")
    List<Donation> findAllWithFundraiser();

}
