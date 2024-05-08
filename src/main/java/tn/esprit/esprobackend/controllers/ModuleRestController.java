package tn.esprit.esprobackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprobackend.entities.Module;
import tn.esprit.esprobackend.services.IModuleService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/module")
public class ModuleRestController {
    IModuleService moduleService;
    // http://localhost:8089/esprobackend/module/retrieve-all-modules
    @GetMapping("/retrieve-all-modules")
    public List<Module> getModules() {
        List<Module> listModules = moduleService.retrieveAllModules();
        return listModules;
    }
    // http://localhost:8089/esproeackend/module/retrieve-module/8
    @GetMapping("/retrieve-module/{module-id}")
    public Module retrieveModule(@PathVariable("module-id") Long chId) {
        Module module = moduleService.retrieveModule(chId);
        return module;
    }
    // http://localhost:8089/esprobackend/module/add-module
    @PostMapping("/add-module")
    public Module addModule(@RequestBody Module c) {
        Module module = moduleService.addModule(c);
        return module;
    }
    // http://localhost:8089/esprobackend/module/remove-module/{module-id}
    @DeleteMapping("/remove-module/{module-id}")
    public void removeModule(@PathVariable("module-id") Long chId) {
        moduleService.removeModule(chId);
    }
    // http://localhost:8089/esprobackend/module/modify-module
    @PutMapping("/modify-module")
    public Module modifyModule(@RequestBody Module c) {
        Module module = moduleService.modifyModule(c);
        return module;
    }
}

