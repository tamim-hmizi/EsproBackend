package tn.esprit.esprobackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprobackend.entities.Option;
import tn.esprit.esprobackend.services.IOptionService;

import java.util.List;



@RestController
@AllArgsConstructor
@RequestMapping("/option")
public class OptionRestController {
    IOptionService optionService;
    // http://localhost:8089/tpfoyer/option/retrieve-all-options
    @GetMapping("/retrieve-all-options")
    public List<Option> getOptions() {
        List<Option> listOptions = optionService.retrieveAllOptions();
        return listOptions;
    }
    // http://localhost:8089/tpfoyer/option/retrieve-option/8
    @GetMapping("/retrieve-option/{option-id}")
    public Option retrieveOption(@PathVariable("option-id") Long chId) {
        Option option = optionService.retrieveOption(chId);
        return option;
    }
    // http://localhost:8089/tpfoyer/option/add-option
    @PostMapping("/add-option/{startYear}/{endYear}")
    public Option addOption(@RequestBody Option c, @PathVariable String startYear, @PathVariable String endYear) {
       // Option option = optionService.addOption(c);
        optionService.generateClassroomsForOption(c,startYear,endYear);
        return c;
    }
    // http://localhost:8089/tpfoyer/option/remove-option/{option-id}
    @DeleteMapping("/remove-option/{option-id}")
    public void removeOption(@PathVariable("option-id") Long chId) {
        optionService.removeOption(chId);
    }
    // http://localhost:8089/tpfoyer/option/modify-option
    @PutMapping("/modify-option/{startYear}/{endYear}")
    public Option modifyOption(@RequestBody Option c, @PathVariable String startYear, @PathVariable String endYear) {
        Option option = optionService.modifyOption(c);
        optionService.recreateClassroomsWhenOptionUpdates(c,startYear,endYear);
        return option;
    }
    // http://localhost:8089/tpfoyer/option/update-classrooms-when-option-is-updated
//    @PutMapping("/update-classrooms-when-option-is-updated")
//    public void updateClassroomsWhenOptionIsUpdated(@RequestBody Option c) {
//        optionService.updateClassroomsWhenOptionIsUpdated(c);
//    }
//    // http://localhost:8089/tpfoyer/option/generate-classrooms-for-option
//    @PutMapping("/generate-classrooms-for-option")
//    public void generateClassroomsForOption(@RequestBody Option c) {
//        optionService.generateClassroomsForOption(c);
//    }

}
