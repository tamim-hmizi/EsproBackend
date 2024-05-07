package tn.esprit.esprobackend.services;
import tn.esprit.esprobackend.entities.Module;

import java.util.List;

public interface IModuleService {
    List<Module> getAllModules();
    Module getModuleById(Long moduleId);
    Module addModule(Module module);
    void removeModule(Long moduleId);
    Module updateModule(Module module);
}
