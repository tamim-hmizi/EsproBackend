package tn.esprit.esprobackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.esprobackend.entities.Module;

@Repository
public interface ModuleRepository extends JpaRepository<Module,Long> {
}
