package tn.esprit.esprobackend.services;

import tn.esprit.esprobackend.entities.Classroom;

import java.util.List;
public interface IClassroomService {
    public List<Classroom> retrieveAllClassrooms();
    public Classroom retrieveClassroom(Long classroomId);
    public Classroom addClassroom(Classroom c);
    public void removeClassroom(Long classroomId);
    public Classroom modifyClassroom(Classroom classroom);


}
