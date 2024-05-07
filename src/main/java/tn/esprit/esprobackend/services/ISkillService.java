package tn.esprit.esprobackend.services;
import tn.esprit.esprobackend.entities.Skill;
import java.util.List;

public interface ISkillService {

    List<Skill> getAllSkills();
    Skill getSkillById(Long skillId);
    Skill addSkill(Skill skill);
    void removeSkill(Long skillId);
    Skill updateSkill(Skill skill);

}
