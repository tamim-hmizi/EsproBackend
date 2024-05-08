package tn.esprit.esprobackend.repositories;

import tn.esprit.esprobackend.entities.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VacationRepository extends JpaRepository<Vacation,Long> {
}
