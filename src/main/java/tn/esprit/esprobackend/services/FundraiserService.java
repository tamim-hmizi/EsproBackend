package tn.esprit.esprobackend.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.esprobackend.entities.Fundraiser;
import tn.esprit.esprobackend.repositories.FundraiserRepository;


import java.util.List;
@Service
@AllArgsConstructor
public class FundraiserService implements IFundraiserService {

    private final FundraiserRepository fundraiserRepository;
    @Override
    public List<Fundraiser> getAllFundraisers() {
        return fundraiserRepository.findAll();
    }

    @Override
    public Fundraiser getFundraiserById(Long fundraiserId) {
        return fundraiserRepository.findById(fundraiserId).orElse(null);
    }

    @Override
    public Fundraiser addFundraiser(Fundraiser fundraiser) {
        return fundraiserRepository.save(fundraiser);
    }

    @Override
    public void removeFundraiser(Long fundraiserId) {
        fundraiserRepository.deleteById(fundraiserId);
    }

    @Override
    public Fundraiser updateFundraiser(Fundraiser fundraiser) {
        return fundraiserRepository.save(fundraiser);
    }
}
