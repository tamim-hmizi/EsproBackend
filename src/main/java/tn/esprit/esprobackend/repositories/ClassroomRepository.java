package tn.esprit.esprobackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.esprobackend.entities.Classroom;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom,Long> {
}
