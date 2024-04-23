package tn.esprit.esprobackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.esprobackend.entities.Training;

@Repository
public interface TrainingRepository extends JpaRepository<Training,Long> {
}
