package tn.esprit.esprobackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.esprobackend.entities.Sponsor;
import tn.esprit.esprobackend.repositories.SponsorRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class SponsorServiceImpl implements ISponsorService {
    SponsorRepository sponsorRepository;

    @Override
    public List<Sponsor> retrieveAllSponsors() {
        return sponsorRepository.findAll();
    }

    @Override
    public Sponsor retrieveSponsor(Long SponsorId) {
        return sponsorRepository.findById(SponsorId).get();
    }

    @Override
    public Sponsor addSponsor(Sponsor c) {
        return sponsorRepository.save(c);
    }

    @Override
    public void removeSponsor(Long SponsorId) {
        sponsorRepository.deleteById(SponsorId);
    }

    @Override
    public Sponsor modifySponsor(Sponsor Sponsor) {
        return sponsorRepository.save(Sponsor);
    }
}
