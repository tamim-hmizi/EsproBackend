package tn.esprit.esprobackend.services;

import tn.esprit.esprobackend.entities.Level;

import java.util.List;

public interface ILevelService {
public List<Level> retrieveAllLevels();
    public Level retrieveLevel(Long levelId);
    public Level addLevel(Level c);
    public void removeLevel(Long levelId);
    public Level modifyLevel(Level level);
    //public void generateClassroomsForLevel(Level level);
    public void updateClassroomsWhenLevelIsUpdated(Level level);
    //public void recreateClassroomsWhenLevelUpdates(Level level);
    public String[] retrieveStartYearAndEndYearForClassroomsByLevelId(Long levelId);
    public void recreateClassroomsWhenLevelUpdates(Level level, String startYear, String endYear);
    public void generateClassroomsForLevel(Level level, String startYear, String endYear);
    //public List returnStartYearAndEndYearForClassroomByLevelId(Long levelId);
}