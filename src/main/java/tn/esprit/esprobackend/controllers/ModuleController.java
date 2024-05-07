package tn.esprit.esprobackend.controllers;

import lombok.AllArgsConstructor;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.esprobackend.entities.Module;
import tn.esprit.esprobackend.entities.Skill;
import tn.esprit.esprobackend.services.IModuleService;
import org.springframework.http.ResponseEntity;
import org.apache.pdfbox.pdmodel.PDDocument;
import tn.esprit.esprobackend.services.ISkillService;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@AllArgsConstructor
@RequestMapping("/module")
public class ModuleController {

    IModuleService moduleService;
    ISkillService skillService;

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

    ///////////////////////////////////////////////////////////////////////////////////////

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        // Check if the file is empty
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        try {
            // Extract data from the file
            String extractedData = extractDataFromFile(file);

            // Return the extracted data in the response body
            return ResponseEntity.ok(extractedData);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to process file");
        }
    }

    private String extractDataFromFile(MultipartFile file) throws IOException {
        try (PDDocument document = PDDocument.load(file.getInputStream())) {
            PDFTextStripper stripper = new PDFTextStripper();
            String pdfText = stripper.getText(document); // Leave as it is

            // Extract module name
            String moduleName = extractModuleName(pdfText);

            // Extract ECTS value
            String ects = extractECTS(pdfText);

            // Extract HE value
            String he = extractHE(pdfText);

            // Extract module objective
            String objective = extractObjective(pdfText);

            // Fetch all skills from the database
            List<Skill> allSkills = skillService.getAllSkills();

            Module m = new Module();
            m.setDescription(objective);
            m.setEcts(Long.parseLong(ects));
            m.setTeaching_hours(Long.parseLong(he));
            m.setName(moduleName);

            // Initialize the set of skills for the module
            Set<Skill> moduleSkills = new HashSet<>();

            // Compare each skill with the extracted text
            for (Skill skill : allSkills) {
                // Convert skill name to lowercase for case-insensitive comparison
                String skillName = skill.getName().toLowerCase();
                if (pdfText.toLowerCase().contains(skillName)) {
                    // If the skill is found in the extracted text, add it to the module
                    moduleSkills.add(skill);
                }
            }

            // Set the skills for the module
            m.setSkills(moduleSkills);

            // Save the module to the database
            moduleService.addModule(m);

            return moduleName + "\n" + objective + "\nECTS: " + ects + "\nHE: " + he;
        }
    }


    private String extractModuleName(String text) {
        String moduleName = "";
        // Regular expression pattern to match "Module : [module_name]"
        Pattern pattern = Pattern.compile("Module : (.+?)\\n", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);

        // Find the first match
        if (matcher.find()) {
            moduleName = matcher.group(1); // Extract the module name
        }

        return moduleName;
    }

    private String extractValueByHeader(String text, String[] headers) {
        String value = "";

        // Iterate over each header name
        for (String header : headers) {
            // Regular expression pattern to match the header and its corresponding value
            Pattern pattern = Pattern.compile(header + "\\s+.*?\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s+(.*?)\\s+", Pattern.DOTALL);
            Matcher matcher = pattern.matcher(text);

            // Find the match
            if (matcher.find()) {
                // Extract the value corresponding to the header index
                if (header.equals("HE") || header.equals("Cours intégré")) {
                    String heValue = matcher.group(3).trim(); // Extract the HE value
                    // Remove the "h" character from the HE value and any non-numeric characters
                    value = heValue.replaceAll("[^\\d.]", ""); // Remove non-numeric characters
                } else if (header.equals("ECTS")) {
                    String ectsValue = matcher.group(3).trim(); // Extract the ECTS value
                    // Remove any non-numeric characters from the ECTS value
                    value = ectsValue.replaceAll("[^\\d.]", ""); // Remove non-numeric characters
                }
                // Exit loop once a match is found
                break;
            }
        }

        return value;
    }

    private String extractObjective(String text) {
        String objective = "";
        // Regular expression pattern to match the objective
        Pattern pattern = Pattern.compile("Objectif du module :\\s*(.*?)\\s*(?=Mode d’évaluation :)", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(text);

        // Find the match
        if (matcher.find()) {
            objective = matcher.group(1).trim(); // Extract the objective
        }

        return objective;
    }

    private String extractECTS(String text) {
        return extractValueByHeader(text, new String[]{"ECTS"});
    }

    private String extractHE(String text) {
        return extractValueByHeader(text, new String[]{"HE", "Cours intégré"});
    }



}












