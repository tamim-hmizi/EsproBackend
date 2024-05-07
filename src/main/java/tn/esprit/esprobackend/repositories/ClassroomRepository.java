package tn.esprit.esprobackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.esprit.esprobackend.entities.Classroom;
import tn.esprit.esprobackend.entities.Level;
import tn.esprit.esprobackend.entities.Option;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom,Long>{
   

    List<Classroom> findByOption(Option option);

    List<Classroom> findByLevel(Level level);


    List<Classroom> findByLevelId(Long id);

    long countByLevel(Level level);
    Classroom getClassroomById(Long id);

    List<Classroom> findByOptionId(Long optionId);

    List findStartYearAndEndYearForClassroomByLevelId(Long levelId);


    // void deleteAllWithLevel(Level level);
}
