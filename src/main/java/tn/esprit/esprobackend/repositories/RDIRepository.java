package tn.esprit.esprobackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.esprobackend.entities.RDI;
import tn.esprit.esprobackend.entities.RDIMember;

import java.util.List;
import java.util.Set;

@Repository
public interface RDIRepository extends JpaRepository<RDI,Long> {
    boolean existsByTheme(String theme);

    RDI findRDIByRDIMembersIsContaining(Set<RDIMember> RDIMember);
}
