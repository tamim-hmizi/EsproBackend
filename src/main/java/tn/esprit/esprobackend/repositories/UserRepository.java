package tn.esprit.esprobackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.esprobackend.entities.Skill;
import tn.esprit.esprobackend.entities.User;

public interface UserRepository extends JpaRepository<User,Long> {

}
