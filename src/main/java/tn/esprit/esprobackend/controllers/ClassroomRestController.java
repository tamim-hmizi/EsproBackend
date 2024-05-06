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
    // http://localhost:8089/esprobackend/classroom/retrieve-all-classrooms
    @GetMapping("/retrieve-all-classrooms")
    public List<Classroom> getClassrooms() {
        List<Classroom> listClassrooms = classroomService.retrieveAllClassrooms();
        return listClassrooms;
    }
    // http://localhost:8089/esproeackend/classroom/retrieve-classroom/8
    @GetMapping("/retrieve-classroom/{classroom-id}")
    public Classroom retrieveClassroom(@PathVariable("classroom-id") Long chId) {
        Classroom classroom = classroomService.retrieveClassroom(chId);
        return classroom;
    }
    // http://localhost:8089/esprobackend/classroom/add-classroom
    @PostMapping("/add-classroom")
    public Classroom addClassroom(@RequestBody Classroom c) {
        Classroom classroom = classroomService.addClassroom(c);
        return classroom;
    }
    // http://localhost:8089/esprobackend/classroom/remove-classroom/{classroom-id}
    @DeleteMapping("/remove-classroom/{classroom-id}")
    public void removeClassroom(@PathVariable("classroom-id") Long chId) {
        classroomService.removeClassroom(chId);
    }
    // http://localhost:8089/esprobackend/classroom/modify-classroom
    @PutMapping("/modify-classroom")
    public Classroom modifyClassroom(@RequestBody Classroom c) {
        Classroom classroom = classroomService.modifyClassroom(c);
        return classroom;
    }
}

