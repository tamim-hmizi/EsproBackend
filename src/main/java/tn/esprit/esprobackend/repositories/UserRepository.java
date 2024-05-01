
package tn.esprit.esprobackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.esprobackend.entities.user;

@Repository
public interface UserRepository extends JpaRepository<user,Long> {
}