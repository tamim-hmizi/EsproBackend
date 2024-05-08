package tn.esprit.esprobackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.esprobackend.entities.Skill;
import tn.esprit.esprobackend.repositories.SkillRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class SkillServiceImpl implements ISkillService {
    SkillRepository skillRepository;
    public List<Skill> retrieveAllSkills() {
        return skillRepository.findAll();
    }
    public Skill retrieveSkill(Long id) {
        return skillRepository.findById(id).get();
    }
    public Skill addSkill(Skill c) {
        return skillRepository.save(c);
    }
    public void removeSkill(Long id) {
        skillRepository.deleteById(id);
    }
    public Skill modifySkill(Skill bloc) {
        return skillRepository.save(bloc);
    }
}

