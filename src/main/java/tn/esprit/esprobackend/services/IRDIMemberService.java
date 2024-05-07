package tn.esprit.esprobackend.services;

import tn.esprit.esprobackend.entities.*;

import java.util.List;

public interface IRDIMemberService {
    public List<RDIMember> retrieveAllRDIMembers();
    public List<User> retrieveuserAll() ;

    public RDIMember retrieveRDIMember(Long RDIId);
    public RDIMember addRDIMember(RDIMember c);
    public void removeRDIMember(Long RDIId);
    public RDIMember modifyRDIMember(RDIMember RDIMember);
    public List<RDIMember> searchByuser( User user );
    public List<RDIMember> searchByRDI(RDI RDI);
    public List<RDIMember> searchByPublication(Publication Publication);
    public RDIMember findRDIMemberByUser(User user);


}
