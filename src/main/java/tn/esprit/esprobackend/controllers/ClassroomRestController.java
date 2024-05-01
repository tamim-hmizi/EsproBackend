package tn.esprit.esprobackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprobackend.entities.Classroom;
import tn.esprit.esprobackend.services.IClassroomService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/classroom")
public class ClassroomRestController {
    IClassroomService classroomService;
    // http://localhost:8089/tpfoyer/classroom/retrieve-all-classrooms
    @GetMapping("/retrieve-all-classrooms")
    public List<Classroom> getClassrooms() {
        List<Classroom> listClassrooms = classroomService.retrieveAllClassrooms();
        return listClassrooms;
    }
    // http://localhost:8089/tpfoyer/classroom/retrieve-classroom/8
    @GetMapping("/retrieve-classroom/{classroom-id}")
    public Classroom retrieveClassroom(@PathVariable("classroom-id") Long chId) {
        Classroom classroom = classroomService.retrieveClassroom(chId);
        return classroom;
    }
    // http://localhost:8089/tpfoyer/classroom/add-classroom
    @PostMapping("/add-classroom")
    public Classroom addClassroom(@RequestBody Classroom c) {
        Classroom classroom = classroomService.addClassroom(c);
        return classroom;
    }
    // http://localhost:8089/tpfoyer/classroom/remove-classroom/{classroom-id}
    @DeleteMapping("/remove-classroom/{classroom-id}")
    public void removeClassroom(@PathVariable("classroom-id") Long chId) {
        classroomService.removeClassroom(chId);
        //updateLevelClassroomNumberOnClassroomDelete(getClassroomById(chId));
    }
    // http://localhost:8089/tpfoyer/classroom/modify-classroom
    @PutMapping("/modify-classroom")
    public Classroom modifyClassroom(@RequestBody Classroom c) {
        Classroom classroom = classroomService.modifyClassroom(c);
        return classroom;
 }
//    // http://localhost:8089/tpfoyer/classroom/update-classrooms-when-level-is-updated
//    @PutMapping("/update-classrooms-when-level-is-updated")
//    public void updateClassroomsWhenLevelIsUpdated(@RequestBody Classroom c) {
//        classroomService.updateClassroomsWhenLevelIsUpdated(c);
//    }
//    // http://localhost:8089/tpfoyer/classroom/generate-classrooms-for-level
//@PutMapping("/generate-classrooms-for-level")
//public void generateClassroomsForLevel(@RequestBody Classroom c) {
//    classroomService.generateClassroomsForLevel(c);
//}
//    // http://localhost:8089/tpfoyer/classroom/generate-classrooms-for-option
//    @PutMapping("/generate-classrooms-for-option")
//    public void generateClassroomsForOption(@RequestBody Classroom c) {
//        classroomService.generateClassroomsForOption(c);
//    }
//    // http://localhost:8089/tpfoyer/classroom/update-classrooms-when-option-is-updated
//    @PutMapping("/update-classrooms-when-option-is-updated")
//    public void updateClassroomsWhenOptionIsUpdated(@RequestBody Classroom c) {
//        classroomService.updateClassroomsWhenOptionIsUpdated(c);
//    }

}

