package tn.esprit.esprobackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.esprobackend.entities.Classroom;
import tn.esprit.esprobackend.repositories.ClassroomRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ClassroomServiceImpl implements IClassroomService {
    ClassroomRepository classroomRepository;
    public List<Classroom> retrieveAllClassrooms() {
        return classroomRepository.findAll();
    }
    public Classroom retrieveClassroom(Long id) {
        return classroomRepository.findById(id).get();
    }
    public Classroom addClassroom(Classroom c) {
        return classroomRepository.save(c);
    }
    public void removeClassroom(Long id) {
        classroomRepository.deleteById(id);
    }
    public Classroom modifyClassroom(Classroom bloc) {
        return classroomRepository.save(bloc);
    }
}

