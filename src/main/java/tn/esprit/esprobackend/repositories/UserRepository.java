package tn.esprit.esprobackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.esprobackend.entities.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {
    boolean existsByEmail(String email);

    Optional<User> findByEmail(String email);

    @Query("select c from User c where  c.email=:mail")
    User getRolFromUser(@Param("mail") String mail);
}
