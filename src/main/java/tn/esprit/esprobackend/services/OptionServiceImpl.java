package tn.esprit.esprobackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.esprobackend.entities.Classroom;
import tn.esprit.esprobackend.entities.Option;
import tn.esprit.esprobackend.repositories.ClassroomRepository;
import tn.esprit.esprobackend.repositories.OptionRepository;

import java.util.List;
@Service
@AllArgsConstructor
public class OptionServiceImpl implements IOptionService{
    OptionRepository optionRepository;
    ClassroomRepository classroomRepository;
    IClassroomService classroomService;
    public List<Option> retrieveAllOptions() {
        return optionRepository.findAll();
    }
    public Option retrieveOption(Long optionId) {
        return optionRepository.findById(optionId).get();
    }
    public Option addOption(Option c) {
        return optionRepository.save(c);
    }
    public void removeOption(Long optionId) {


        List<Classroom> classrooms = classroomRepository.findByOptionId(optionId);
        if (!classrooms.isEmpty()) {
            for (Classroom classroom : classrooms) {
                classroomService.removeClassroom(classroom.getId());
            }
        }
        optionRepository.deleteById(optionId);
    }
    public Option modifyOption(Option option) {
        return optionRepository.save(option);
    }
//    public void generateClassroomsForOption(Option option){
//        optionRepository.save(option);
//        for (int i = 1; i <= option.getClassroom_number(); i++) {
//            Classroom classroom = new Classroom();
//            classroom.setName(option.getName()+" " + i);
//            //classroom.setOption(option);
//            classroomRepository.save(classroom);
//        }
//    }
public void generateClassroomsForOption(Option option, String startYear, String endYear){
    Option savedoption= optionRepository.save(option);
    for (int i = 1; i <= option.getClassroom_number(); i++) {
        Classroom classroom = new Classroom();
        classroom.setName(option.getName()+" " + i);
        if(startYear != null) {
            classroom.setStartYear(startYear);
        }
        if(endYear != null) {
            classroom.setEndYear(endYear);
        }
        Classroom savedClassroom = classroomRepository.save(classroom);
        savedClassroom.setOption(savedoption);
        classroomRepository.save(savedClassroom);
    }
}


    public void updateClassroomsWhenOptionIsUpdated(Option option){
        List<Classroom> classrooms = classroomRepository.findByOption(option);
        for (int i = 1; i <= option.getClassroom_number(); i++) {
            if (i > classrooms.size()) {
                Classroom classroom = new Classroom();
                classroom.setName(option.getName()+" " + i);
                //classroom.setOption(option);
                classroomRepository.save(classroom);
            } else {
                Classroom classroom = classrooms.get(i - 1);
                classroom.setName(option.getName()+" " + i);
                classroomRepository.save(classroom);
            }
        }
    }

//    public void recreateClassroomsWhenOptionUpdates(Option option){
//        List<Classroom> classrooms = classroomRepository.findByOption(option);
//        for (Classroom classroom : classrooms) {
//            classroomService.removeClassroom(classroom.getId());
//            } for (int i = 1; i <= option.getClassroom_number(); i++) {
//            Classroom classroom = new Classroom();
//            classroom.setName(option.getName()+" " + i);
//            classroom.setOption(option);
//            classroomRepository.save(classroom);
//
//        }
//    }
public void recreateClassroomsWhenOptionUpdates(Option option, String startYear, String endYear) {
    List<Classroom> classrooms = classroomRepository.findByOption(option);
    for (Classroom classroom : classrooms) {
        classroomService.removeClassroom(classroom.getId());
    }
    for (int i = 1; i <= option.getClassroom_number(); i++) {
        Classroom classroom = new Classroom();
        classroom.setName(option.getName()+" " + i);
        classroom.setOption(option);
        if(startYear != null) {
            classroom.setStartYear(startYear);
        }
        if(endYear != null) {
            classroom.setEndYear(endYear);
        }
        classroomRepository.save(classroom);
    }
}


public String[] retrieveStartYearAndEndYearForClassroomsByLevelId(Long optionId) {
    List<Classroom> classrooms = classroomRepository.findByOptionId(optionId);
    String[] years = new String[2];
    if (!classrooms.isEmpty()) {
        years[0] = classrooms.get(0).getStartYear();
        years[1] = classrooms.get(0).getEndYear();
    }
    return years;


}}
