package tn.esprit.esprobackend.services;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.esprobackend.entities.Module;
import tn.esprit.esprobackend.repositories.ModuleRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ModuleService implements IModuleService {

    private final ModuleRepository moduleRepository;

    @Override
    public List<Module> getAllModules(){
        return moduleRepository.findAll();
    }

    @Override
    public Module getModuleById(Long moduleId){
        return moduleRepository.findById(moduleId).orElse(null);
    }

    @Override
    public Module addModule(Module module){
        return moduleRepository.save(module);
    }

    @Override
    public void removeModule(Long moduleId) {
        moduleRepository.deleteById(moduleId);

    }

    @Override
    public Module updateModule(Module module){
        return moduleRepository.save(module);
    }
}
