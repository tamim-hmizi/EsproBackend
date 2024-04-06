package tn.esprit.esprobackend.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import tn.esprit.esprobackend.entities.user;
@Repository
public interface userRepo extends JpaRepository<user,Long>{

    Optional<user> findByEmail(String email);
}
