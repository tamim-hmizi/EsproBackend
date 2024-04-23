package tn.esprit.esprobackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.esprobackend.entities.RDIMember;
import tn.esprit.esprobackend.entities.TrainingRequest;

@Repository
public interface TrainingRequestRepository extends JpaRepository<TrainingRequest,Long> {
}
