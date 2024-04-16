package tn.esprit.esprobackend.controllers;


import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.esprobackend.entities.Fundraiser;
import tn.esprit.esprobackend.services.IFundraiserService;

import java.util.List;
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
    public Fundraiser addFundraiser(@RequestBody Fundraiser f) {
        Fundraiser addedFundraiser = fundraiserService.addFundraiser(f);
        return addedFundraiser;
    }


    //////////////////////////////////////// DELETE ////////////////////////////////////////


    @DeleteMapping("/remove-fundraiser/{fundraiser-id}")
    public void removeFundraiser(@PathVariable("fundraiser-id") Long FundraiserId) {
        fundraiserService.removeFundraiser(FundraiserId);
    }

    //////////////////////////////////////// UPDATE ////////////////////////////////////////


    @PutMapping("/update-fundraiser")
    public Fundraiser updateFundraiser(@RequestBody Fundraiser f) {
        Fundraiser fundraiser = fundraiserService.updateFundraiser(f);
        return fundraiser;
    }


}
