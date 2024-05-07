package tn.esprit.esprobackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.esprobackend.entities.RDI;

@Repository
public interface RDIRepository extends JpaRepository<RDI,Long> {
    boolean existsByTheme(String theme);
    


}
