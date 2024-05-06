package tn.esprit.esprobackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.esprobackend.entities.Module;
import tn.esprit.esprobackend.repositories.ModuleRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ModuleServiceImpl implements IModuleService {
    ModuleRepository moduleRepository;
    public List<Module> retrieveAllModules() {
        return moduleRepository.findAll();
    }
    public Module retrieveModule(Long id) {
        return moduleRepository.findById(id).get();
    }
    public Module addModule(Module c) {
        return moduleRepository.save(c);
    }
    public void removeModule(Long id) {
        moduleRepository.deleteById(id);
    }
    public Module modifyModule(Module bloc) {
        return moduleRepository.save(bloc);
    }
}

