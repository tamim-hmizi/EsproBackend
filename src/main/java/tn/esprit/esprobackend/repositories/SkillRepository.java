package tn.esprit.esprobackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.esprobackend.entities.Skill;

@Repository
public interface SkillRepository extends JpaRepository<Skill,Long> {
}
