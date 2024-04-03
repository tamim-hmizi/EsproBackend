package tn.esprit.esprobackend.controllers;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprobackend.entities.Module;
import tn.esprit.esprobackend.services.IModuleService;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/module")
public class ModuleController {

    IModuleService moduleService;

    //////////////////////////////////////// SHOW ALL ////////////////////////////////////////


    // http://localhost:8089/esprobackend/module/retrieve-all-modules
    @GetMapping("/retrieve-all-modules")
    public List<Module> getAllModules() {
        List<Module> ModuleList = moduleService.getAllModules();
        return ModuleList;
    }


    //////////////////////////////////////// SHOW BY ID ////////////////////////////////////////

    // http://localhost:8089/esprobackend/module/retrieve-module/id
    @GetMapping("/retrieve-module/{module-id}")
    public Module getModuleById(@PathVariable("module-id") Long ModuleId) {
        Module module = moduleService.getModuleById(ModuleId);
        return module;
    }


    //////////////////////////////////////// ADD ////////////////////////////////////////

    // http://localhost:8089/esprobackend/module/add-modules
    @PostMapping("/add-modules")
    public Module addModule(@RequestBody Module m) {
        Module module = moduleService.addModule(m);
        return module;
    }

    //////////////////////////////////////// DELETE ////////////////////////////////////////

    // http://localhost:8089/esprobackend/module/remove-module/id
    @DeleteMapping("/remove-module/{module-id}")
    public void removeModule(@PathVariable("module-id") Long ModuleId) {
        moduleService.removeModule(ModuleId);
    }

    //////////////////////////////////////// UPDATE ////////////////////////////////////////

    // http://localhost:8089/esprobackend/module/update-skill
    @PutMapping("/update-module")
    public Module updateModule(@RequestBody Module m) {
        Module module = moduleService.updateModule(m);
        return module;
    }
}
