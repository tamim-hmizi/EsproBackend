package tn.esprit.esprobackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.esprobackend.entities.RDI;
import tn.esprit.esprobackend.entities.RDIMember;

import java.time.LocalDate;

@Repository
public interface RDImemberRepository extends JpaRepository<RDIMember,Long> {

}
