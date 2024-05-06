package tn.esprit.esprobackend.services;

import tn.esprit.esprobackend.entities.Module;
import java.util.List;
public interface IModuleService {
    public List<Module> retrieveAllModules();
    public Module retrieveModule(Long ModuleId);
    public Module addModule(Module c);
    public void removeModule(Long ModuleId);
    public Module modifyModule(Module Module);
}
