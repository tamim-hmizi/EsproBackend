package tn.esprit.esprobackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprobackend.entities.Skill;
import tn.esprit.esprobackend.services.ISkillService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/skill")
public class SkillRestController {
    ISkillService skillService;
    // http://localhost:8089/esprobackend/skill/retrieve-all-skills
    @GetMapping("/retrieve-all-skills")
    public List<Skill> getSkills() {
        List<Skill> listSkills = skillService.retrieveAllSkills();
        return listSkills;
    }
    // http://localhost:8089/esproeackend/skill/retrieve-skill/8
    @GetMapping("/retrieve-skill/{skill-id}")
    public Skill retrieveSkill(@PathVariable("skill-id") Long chId) {
        Skill skill = skillService.retrieveSkill(chId);
        return skill;
    }
    // http://localhost:8089/esprobackend/skill/add-skill
    @PostMapping("/add-skill")
    public Skill addSkill(@RequestBody Skill c) {
        Skill skill = skillService.addSkill(c);
        return skill;
    }
    // http://localhost:8089/esprobackend/skill/remove-skill/{skill-id}
    @DeleteMapping("/remove-skill/{skill-id}")
    public void removeSkill(@PathVariable("skill-id") Long chId) {
        skillService.removeSkill(chId);
    }
    // http://localhost:8089/esprobackend/skill/modify-skill
    @PutMapping("/modify-skill")
    public Skill modifySkill(@RequestBody Skill c) {
        Skill skill = skillService.modifySkill(c);
        return skill;
    }
}

