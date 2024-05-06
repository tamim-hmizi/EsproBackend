package tn.esprit.esprobackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.esprobackend.entities.Skill;
import tn.esprit.esprobackend.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
}