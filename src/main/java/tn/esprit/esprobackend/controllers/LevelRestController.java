package tn.esprit.esprobackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprobackend.entities.Level;
import tn.esprit.esprobackend.services.IClassroomService;
import tn.esprit.esprobackend.services.ILevelService;

import java.util.List;
@RestController
@AllArgsConstructor
@RequestMapping("/level")

public class LevelRestController {
    ILevelService levelService;
    IClassroomService classroomService;
    // http://localhost:8089/tpfoyer/level/retrieve-all-levels
    @GetMapping("/retrieve-all-levels")
    public List<Level> getLevels() {
        List<Level> listLevels = levelService.retrieveAllLevels();
        return listLevels;
    }
    // http://localhost:8089/tpfoyer/level/retrieve-level/8
    @GetMapping("/retrieve-level/{level-id}")
    public Level retrieveLevel(@PathVariable("level-id") Long chId) {
        Level level = levelService.retrieveLevel(chId);
        return level;
    }
    // http://localhost:8089/tpfoyer/level/add-level
//    @PostMapping("/add-level")
//    public Level addLevel(@RequestBody Level c) {
//
//
////        Level level = levelService.addLevel(c);
//        levelService.generateClassroomsForLevel(c);
//        return c;
//    }
    // http://localhost:8089/tpfoyer/level/add-level
//    @PostMapping("/add-level")
//    //i want the function to take the startYear and endYear as arguments
//    public Level addLevel(@RequestBody Level c, @RequestParam String startYear, @RequestParam String endYear) {
//        levelService.generateClassroomsForLevel(c, startYear, endYear);
//        return c;
//    }
    @PostMapping("/add-level/{startYear}/{endYear}")
    public Level addLevel(@RequestBody Level c, @PathVariable String startYear, @PathVariable String endYear) {
        levelService.generateClassroomsForLevel(c, startYear, endYear);
        return c;
    }
    // http://localhost:8089/tpfoyer/level/remove-level/{level-id}
    @DeleteMapping("/remove-level/{level-id}")
    public void removeLevel(@PathVariable("level-id") Long chId) {
        levelService.removeLevel(chId);
    }
    // http://localhost:8089/tpfoyer/level/modify-level
    @PutMapping("/modify-level/{startYear}/{endYear}")
    public Level modifyLevel(@RequestBody Level c, @PathVariable String startYear, @PathVariable String endYear) {

        Level level = levelService.modifyLevel(c);
        levelService.recreateClassroomsWhenLevelUpdates(c, startYear, endYear);

        return level;
    }
    // http://localhost:8089/tpfoyer/level/update-classrooms-when-level-is-updated
//    @PutMapping("/update-classrooms-when-level-is-updated")
//    public void updateClassroomsWhenLevelIsUpdated(@RequestBody Level c) {
//        levelService.updateClassroomsWhenLevelIsUpdated(c);
//    }
//    // http://localhost:8089/tpfoyer/level/generate-classrooms-for-level
//    @PutMapping("/generate-classrooms-for-level")
//    public void generateClassroomsForLevel(@RequestBody Level c) {
//        levelService.generateClassroomsForLevel(c);
//    }
    //i need an api to add level and then generate classrooms for this level
    //do it for me in the next line
    //http://localhost:8089/tpfoyer/level/add-level-and-generate-classrooms
//    @PostMapping("/add-level-and-generate-classrooms")
//    public Level addLevelAndGenerateClassrooms(@RequestBody Level c) {
//
//        levelService.generateClassroomsForLevel(c);
//        Level level = levelService.addLevel(c);
//        return level;
//        //this api give me error 500 when implmenting in in front end give me solution in the next line
//        //the error is because the level is not saved in the database before generating the classrooms
//        //so you need to save the level first then generate the classrooms
//        //give me code and where to put it
//        //in the service layer in the addLevel method
//    }
    @GetMapping("/retrieve-classroom-years/{level-id}")
    public List retrieveClassroomYears(@PathVariable("level-id") Long levelId) {
        return levelService.returnStartYearAndEndYearForClassroomByLevelId(levelId);
    }


}
