package tn.esprit.esprobackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.esprobackend.entities.Skill;
import tn.esprit.esprobackend.repositories.SkillRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class SkillService implements ISkillService {

    private final SkillRepository skillRepository;

    @Override
    public List<Skill> getAllSkills() {
        return skillRepository.findAll();
    }

    @Override
    public Skill getSkillById(Long skillId) {
        return skillRepository.findById(skillId).orElse(null);
    }

    @Override
    public Skill addSkill(Skill skill) {
        return skillRepository.save(skill);
    }

    @Override
    public void removeSkill(Long skillId) {
        skillRepository.deleteById(skillId);
    }

    @Override
    public Skill updateSkill(Skill skill) {
        return skillRepository.save(skill);
    }
}
