package tn.esprit.esprobackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.esprobackend.entities.Classroom;
import tn.esprit.esprobackend.entities.Level;
import tn.esprit.esprobackend.repositories.ClassroomRepository;
import tn.esprit.esprobackend.repositories.LevelRepository;

import java.lang.reflect.Array;
import java.util.List;

@Service
@AllArgsConstructor
public class LevelServiceImpl implements ILevelService{
    LevelRepository levelRepository;
    ClassroomRepository classroomRepository;
    IClassroomService classroomService;
    public List<Level> retrieveAllLevels() {
        return levelRepository.findAll();
    }
    public Level retrieveLevel(Long levelId) {
        return levelRepository.findById(levelId).get();
    }
    public Level addLevel(Level c) {
        //generateClassroomsForLevel(c);
        return levelRepository.save(c);
    }
    public void removeLevel(Long levelId) {

        //add code to delete all classrooms with the level id
        List<Classroom> classrooms = classroomRepository.findByLevelId(levelId);
        if (!classrooms.isEmpty()) {
            for (Classroom classroom : classrooms) {
                classroomService.removeClassroom(classroom.getId());
            }
        }
        levelRepository.deleteById(levelId);



    }
    public Level modifyLevel(Level level) {
        return levelRepository.save(level);
    }
//    public void generateClassroomsForLevel(Level level){
//        levelRepository.save(level);
//        for (int i = 1; i <= level.getClassroom_number(); i++) {
//            Classroom classroom = new Classroom();
//            classroom.setName(level.getName()+" " + i);
//           // classroom.setLevel(level);
//            classroomRepository.save(classroom);
//        }
//    }
//public void generateClassroomsForLevel(Level level, String startYear, String endYear){
//    levelRepository.save(level);
//    for (int i = 1; i <= level.getClassroom_number(); i++) {
//        Classroom classroom = new Classroom();
////        classroom.setLevel(level);
//        classroom.setName(level.getName()+" " + i);
//        if(startYear != null) {
//            classroom.setStartYear(startYear);
//        }
//        if(endYear != null) {
//            classroom.setEndYear(endYear);
//        }
//        classroomRepository.save(classroom);
//    }
//}
    public void generateClassroomsForLevel(Level level, String startYear, String endYear){
        Level savedLevel = levelRepository.save(level);
        for (int i = 1; i <= savedLevel.getClassroom_number(); i++) {
            Classroom classroom = new Classroom();
            classroom.setName(savedLevel.getName()+" " + i);
            if(startYear != null) {
                classroom.setStartYear(startYear);
            }
            if(endYear != null) {
                classroom.setEndYear(endYear);
            }
            Classroom savedClassroom = classroomRepository.save(classroom);
            savedClassroom.setLevel(savedLevel);
            classroomRepository.save(savedClassroom);
        }
    }
    //how to make a function with arguments that i can not fill when calling it give me exemple next line













    //i want to recreate generateClassroomsForLevel to accept other 2 args startyear and end year of classroom entity and they could be not defined in the call of the function and the function always checks if they are defined or not if they are it addes the years to the classroom start next line

//    public void updateClassroomsWhenLevelIsUpdated(Level level){
//        List<Classroom> classrooms = classroomRepository.findByLevel(level);
////        for (int i = 1; i <= level.getClassroom_number(); i++) {
////            if (i > classrooms.size()) {
////                Classroom classroom = new Classroom();
////                classroom.setName(level.getName()+" " + i);
////               // classroom.setLevel(level);
////                classroomRepository.save(classroom);
////            } else {
////               // let  int k =classrooms.size() - level.getClassroom_number();
////                Classroom classroom = classrooms.get(i - 1);
////                classroom.setName(level.getName()+" " + i);
////                classroomRepository.save(classroom);
////            }
//
//        //delete all classwroomswith the level id
//        for (Classroom classroom : classrooms) {
//            classroomRepository.delete(classroom);
//        }
//
//
//        for (int i = 1; i <= level.getClassroom_number(); i++) {
//            Classroom classroom = new Classroom();
//            classroom.setName(level.getName()+" " + i);
//            //classroom.setLevel(level);
//            classroomRepository.save(classroom);
//        }
//    }

    public void updateClassroomsWhenLevelIsUpdated(Level level){
        List<Classroom> classrooms = classroomRepository.findByLevelId(level.getId());
        for (int i = 1; i <= level.getClassroom_number(); i++) {
            if (i > classrooms.size()) {
                Classroom classroom = new Classroom();
                classroom.setName(level.getName()+" " + i);
               // classroom.setLevel(level);
                classroomRepository.save(classroom);
            } else {
               //i want the else to update existing classrooms for that level and delete the extra ones give me code next line
                Classroom classroom = classrooms.get(i - 1);
                classroom.setName(level.getName()+" " + i);
                classroomRepository.save(classroom);
            }
        }
    }

public void recreateClassroomsWhenLevelUpdates(Level level, String startYear, String endYear) {
        List<Classroom> classrooms = classroomRepository.findByLevel(level);
        for (Classroom classroom : classrooms) {
            classroomService.removeClassroom(classroom.getId());
        }
        for (int i = 1; i <= level.getClassroom_number(); i++) {
            Classroom classroom = new Classroom();
            classroom.setName(level.getName()+" " + i);
            classroom.setLevel(level);

            if(startYear != null) {
                classroom.setStartYear(startYear);
            }
            if(endYear != null) {
                classroom.setEndYear(endYear);
            }
            classroomRepository.save(classroom);
        }
    }

    public String[] retrieveStartYearAndEndYearForClassroomsByLevelId(Long levelId) {
        List<Classroom> classrooms = classroomRepository.findByLevelId(levelId);
        String[] years = new String[2];
        if (!classrooms.isEmpty()) {
            years[0] = classrooms.get(0).getStartYear();
            years[1] = classrooms.get(0).getEndYear();
        }
        return years;


    }




}
