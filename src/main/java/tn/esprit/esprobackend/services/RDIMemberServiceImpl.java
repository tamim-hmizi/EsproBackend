package tn.esprit.esprobackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.esprobackend.entities.*;
import tn.esprit.esprobackend.repositories.RDImemberRepository;
import tn.esprit.esprobackend.repositories.TrainingRequestRepository;
import tn.esprit.esprobackend.repositories.UserRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class RDIMemberServiceImpl implements IRDIMemberService {
    RDImemberRepository RDImemberRepository;
    UserRepository UserRepository;



    @Override
    public List<RDIMember> retrieveAllRDIMembers() {
        return RDImemberRepository.findAll();
    }
    public List<user> retrieveuserAll() {
        return UserRepository.findAll();
    }



    @Override
    public RDIMember retrieveRDIMember(Long RDIId) {
        return RDImemberRepository.findById(RDIId).get();
    }

    @Override
    public RDIMember addRDIMember(RDIMember c) {
        return RDImemberRepository.save(c);
    }

    @Override
    public void removeRDIMember(Long RDIId) {
        RDImemberRepository.deleteById(RDIId);
    }

    @Override
    public RDIMember modifyRDIMember(RDIMember RDIMember) {
        return RDImemberRepository.save(RDIMember);
    }

    @Override
    public List<RDIMember> searchByuser(user user) {
        return null;
    }

    @Override
    public List<RDIMember> searchByRDI(RDI RDI) {
        return null;
    }

    @Override
    public List<RDIMember> searchByPublication(Publication Publication) {
        return null;
    }
}

