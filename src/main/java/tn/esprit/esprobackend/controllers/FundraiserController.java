package tn.esprit.esprobackend.controllers;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tn.esprit.esprobackend.entities.Donation;
import tn.esprit.esprobackend.entities.Fundraiser;
import tn.esprit.esprobackend.services.IFundraiserService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/fundraiser")
public class FundraiserController {

    IFundraiserService fundraiserService;


    //////////////////////////////////////// SHOW ALL ////////////////////////////////////////



    @GetMapping("/retrieve-all-fundraisers")
    public List<Fundraiser> getFundraisers() {
        List<Fundraiser> FundraiserList = fundraiserService.getAllFundraisers();
        return FundraiserList;
    }


    //////////////////////////////////////// SHOW BY ID ////////////////////////////////////////


    @GetMapping("/retrieve-fundraiser/{fundraiser-id}")
    public Fundraiser getFundraiserById(@PathVariable("fundraiser-id") Long FundraiserId) {
        Fundraiser fundraiser = fundraiserService.getFundraiserById(FundraiserId);
        return fundraiser;
    }


    //////////////////////////////////////// ADD ////////////////////////////////////////


    @PostMapping("/add-fundraiser")
    public ResponseEntity<Fundraiser> addFundraiser(@RequestParam("name") String name,
                                                    @RequestParam("description") String description,
                                                    @RequestParam("moneytocollect") String moneytocollect,
                                                    @RequestParam("photoFile") MultipartFile photoFile) {
        try {
            Fundraiser fundraiser = new Fundraiser();
            fundraiser.setName(name);
            fundraiser.setMoneytocollect(Long.parseLong(moneytocollect));
            fundraiser.setDescription(description);
            fundraiser.setDisplayPicture(photoFile.getBytes());

            Fundraiser addedFundraiser = fundraiserService.addFundraiser(fundraiser);
            return ResponseEntity.status(HttpStatus.CREATED).body(addedFundraiser);
        } catch (IOException e) {
            // Handle file-related errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }








    //////////////////////////////////////// DELETE ////////////////////////////////////////


    @DeleteMapping("/remove-fundraiser/{fundraiser-id}")
    public void removeFundraiser(@PathVariable("fundraiser-id") Long FundraiserId) {
        fundraiserService.removeFundraiser(FundraiserId);
    }

    //////////////////////////////////////// UPDATE ////////////////////////////////////////


        @PutMapping("/update-fundraiser/{id}")
    public ResponseEntity<Fundraiser> updateFundraiser(@PathVariable("id") Long id,
                                                       @RequestParam("name") String name,
                                                       @RequestParam("description") String description,
                                                       @RequestParam("moneytocollect") String moneytocollect,
                                                       @RequestParam(value = "photoFile", required = false) MultipartFile photoFile) {
        try {
            // Retrieve the existing fundraiser by ID
            Fundraiser existingFundraiser = fundraiserService.getFundraiserById(id);

            if (existingFundraiser == null) {
                // If the fundraiser with the given ID doesn't exist, return 404 Not Found
                return ResponseEntity.notFound().build();
            }

            // Update the fundraiser with the new data
            existingFundraiser.setName(name);
            existingFundraiser.setMoneytocollect(Long.parseLong(moneytocollect));
            existingFundraiser.setDescription(description);

            if (photoFile != null) {
                // If a new photo file is provided, update the display picture
                existingFundraiser.setDisplayPicture(photoFile.getBytes());
            }

            // Save the updated fundraiser
            Fundraiser updatedFundraiser = fundraiserService.updateFundraiser(existingFundraiser);

            // Return the updated fundraiser with a 200 OK response
            return ResponseEntity.ok(updatedFundraiser);
        } catch (IOException e) {
            // Handle file-related errors
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @GetMapping("/{fundraiserId}/totalDonation")
    public ResponseEntity<?> getTotalDonation(@PathVariable Long fundraiserId) {
        Optional<Fundraiser> fundraiserOptional = fundraiserService.findById(fundraiserId);

        if (fundraiserOptional.isPresent()) {
            Fundraiser fundraiser = fundraiserOptional.get();
            List<Donation> donations = fundraiser.getDonations();

            if (donations != null) {
                // Filter out any null donations and sum the amounts
                double totalDonation = donations.stream()
                        .filter(Objects::nonNull)
                        .mapToDouble(Donation::getAmount)
                        .sum();

                return ResponseEntity.ok(totalDonation);
            } else {
                // Handle case where donations list is null
                return ResponseEntity.ok(0.0);
            }
        } else {
            // Handle case where fundraiser is not found
            return ResponseEntity.notFound().build();
        }
    }






}
