package tn.esprit.esprobackend.services;

import tn.esprit.esprobackend.entities.Fundraiser;

import java.util.List;

public interface IFundraiserService {

    List<Fundraiser> getAllFundraisers();
    Fundraiser getFundraiserById(Long fundraiserId);
    Fundraiser updateFundraiser(Fundraiser fundraiser);
    Fundraiser addFundraiser(Fundraiser fundraiser);
    void removeFundraiser(Long fundraiserId);
}
