package tn.esprit.esprobackend.services;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.esprobackend.entities.Publication;
import tn.esprit.esprobackend.entities.RDI;
import tn.esprit.esprobackend.entities.RDIMember;
import tn.esprit.esprobackend.entities.user;
import tn.esprit.esprobackend.repositories.RDImemberRepository;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class RDIMemberServiceImpl implements IRDIMemberService {
    @Autowired

    RDImemberRepository RDImemberRepository;
    @Autowired

    userRepo  UserRepository;



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
    @Override

    public RDIMember findRDIMemberByUser(user user){return RDImemberRepository.findRDIMemberByUser(user)  ;}

}

