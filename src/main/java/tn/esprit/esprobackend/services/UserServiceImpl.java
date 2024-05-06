package tn.esprit.esprobackend.services;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.esprobackend.entities.user;
import tn.esprit.esprobackend.repositories.userRepo;
import java.util.List;
@Service
@AllArgsConstructor
public class UserServiceImpl implements IUserService{
    private userRepo userRepository;
    //private final PasswordEncoder passwordEncoder;
    @Override
    public List<user> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public user retrieveUser(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public user addUser(user u) {
        String pwd =u.getPassword();
      // u.setPassword(passwordEncoder.encode(pwd));
        return userRepository.save(u);
    }

    @Override
    public void removeUser(Long userId) {
       userRepository.deleteById(userId);
    }

    @Override
    public user modifyUser(Long id,user userupdated)
    {
        user userr= retrieveUser(id);
        if (userr == null) {
            throw new RuntimeException("Utilisateur introuvable pour la mise à jour");
        }
        else {

            userr.setImg(userupdated.getImg());
            userr.setEmail(userupdated.getEmail());
            userr.setRole(userupdated.getRole());
            userr.setPassword(userupdated.getPassword());
            userr.setNameU(userupdated.getNameU());
            userr.setSurnameU(userupdated.getSurnameU());
            userr.setTelnum(userupdated.getTelnum());
            userr.setAcadmics(userupdated.getAcadmics());
            userr.setPositions(userupdated.getPositions());


            return userRepository.save(userr);
        }

    }



}
