package tn.esprit.esprobackend.services;

import tn.esprit.esprobackend.entities.Donation;


import java.util.List;

public interface IDonationService {

    List<Donation> getAllDonations();
    Donation addDonation(Donation donation);
    Donation updateDonation(Donation donation);
    public List<Donation> getAllDonationsWithFundraiser() ;

}
