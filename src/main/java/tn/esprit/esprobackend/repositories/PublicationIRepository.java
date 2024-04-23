package tn.esprit.esprobackend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import tn.esprit.esprobackend.entities.Publication;
import tn.esprit.esprobackend.entities.ResearchAxis;

import java.util.List;

@Repository
public interface PublicationIRepository extends JpaRepository<Publication,Long> {


}
