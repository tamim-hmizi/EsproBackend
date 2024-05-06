package tn.esprit.esprobackend.repositories;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.esprobackend.entities.user;
@Repository
public interface userRepo extends JpaRepository<user,Long>{
Optional<user> findByEmail(String email);
    @Transactional
    @Modifying
    @Query("update user u set u.password= ?2 where u.email= ?1")
    void updatePassword(String email,String password);
}
