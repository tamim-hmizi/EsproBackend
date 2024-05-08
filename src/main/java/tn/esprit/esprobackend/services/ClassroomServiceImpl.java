package tn.esprit.esprobackend.services;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.esprobackend.entities.Classroom;
import tn.esprit.esprobackend.repositories.ClassroomRepository;
import tn.esprit.esprobackend.repositories.LevelRepository;
import tn.esprit.esprobackend.repositories.OptionRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ClassroomServiceImpl implements IClassroomService{
    ClassroomRepository classroomRepository;
    LevelRepository levelRepository;
    OptionRepository optionRepository;
    public List<Classroom> retrieveAllClassrooms() {
        return classroomRepository.findAll();
    }
    public Classroom retrieveClassroom(Long classroomId) {
        return classroomRepository.findById(classroomId).get();
    }
    public Classroom addClassroom(Classroom c) {
        return classroomRepository.save(c);
    }
    public void removeClassroom(Long classroomId) {
        classroomRepository.deleteById(classroomId);
    }
    public Classroom modifyClassroom(Classroom classroom) {
        return classroomRepository.save(classroom);
    }


}

