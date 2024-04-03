package tn.esprit.esprobackend.services;

import tn.esprit.esprobackend.entities.Donation;
import tn.esprit.esprobackend.entities.Module;

import java.util.List;

public interface IDonationService {

    List<Donation> getAllDonations();
    Donation getDonationById(Long donationId);
    Donation addDonation(Donation donation);
    Donation updateDonation(Donation donation);

}
