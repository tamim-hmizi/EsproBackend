package tn.esprit.esprobackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.esprobackend.entities.Donation;
import tn.esprit.esprobackend.entities.Module;
import tn.esprit.esprobackend.entities.User;
import tn.esprit.esprobackend.repositories.DonationRepository;
import tn.esprit.esprobackend.repositories.UserRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class DonationService implements IDonationService {

    private final DonationRepository donationRepository;
    private final UserRepository userRepository; // Inject the UserRepository

    @Override
    public Donation addDonation(Donation donation) {
        return donationRepository.save(donation);
    }


    public Donation updateDonation(Donation donation){
        return donationRepository.save(donation);
    }

    @Override
    public List<Donation> getAllDonations() {
        return donationRepository.findAll();
    }

    @Override
    public List<Donation> getAllDonationsWithFundraiser() {
        return donationRepository.findAllWithFundraiser();
    }


}
