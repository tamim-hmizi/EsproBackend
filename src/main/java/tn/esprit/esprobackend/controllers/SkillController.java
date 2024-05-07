package tn.esprit.esprobackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprobackend.entities.Skill;
import tn.esprit.esprobackend.services.ISkillService;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/skill")
public class SkillController {

    ISkillService skillService;


    //////////////////////////////////////// SHOW ALL ////////////////////////////////////////


    // http://localhost:8089/esprobackend/skill/retrieve-all-skills
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping("/retrieve-all-skills")
    public List<Skill> getSkills() {
        List<Skill> SkillsList = skillService.getAllSkills();
        return SkillsList;
    }


    //////////////////////////////////////// SHOW BY ID ////////////////////////////////////////

    // http://localhost:8089/esprobackend/skill/retrieve-skill/id
    @GetMapping("/retrieve-skill/{skill-id}")
    public Skill getSkillById(@PathVariable("skill-id") Long SkillId) {
        Skill skill = skillService.getSkillById(SkillId);
        return skill;
    }


    //////////////////////////////////////// ADD ////////////////////////////////////////

    // http://localhost:8089/esprobackend/skill/add-skill
    @PostMapping("/add-skill")
    public Skill addSkill(@RequestBody Skill s) {
        Skill skill = skillService.addSkill(s);
        return skill;
    }

    //////////////////////////////////////// DELETE ////////////////////////////////////////

    // http://localhost:8089/esprobackend/skill/remove- skill/id
    @DeleteMapping("/remove-skill/{skill-id}")
    public void removeSkill(@PathVariable("skill-id") Long SkillId) {
        skillService.removeSkill(SkillId);
    }

    //////////////////////////////////////// UPDATE ////////////////////////////////////////

    // http://localhost:8089/esprobackend/skill/update-skill
    @PutMapping("/update-skill")
    public Skill updateSkill(@RequestBody Skill s) {
        Skill skill = skillService.updateSkill(s);
        return skill;
    }

}
