package tn.esprit.esprobackend.services;

import tn.esprit.esprobackend.entities.Skill;
import java.util.List;
public interface ISkillService {
    public List<Skill> retrieveAllSkills();
    public Skill retrieveSkill(Long SkillId);
    public Skill addSkill(Skill c);
    public void removeSkill(Long SkillId);
    public Skill modifySkill(Skill Skill);
}
