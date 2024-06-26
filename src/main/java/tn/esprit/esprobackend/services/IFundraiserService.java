package tn.esprit.esprobackend.services;

import org.springframework.web.multipart.MultipartFile;
import tn.esprit.esprobackend.entities.Fundraiser;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IFundraiserService {

    List<Fundraiser> getAllFundraisers();
    Fundraiser getFundraiserById(Long fundraiserId);
    Fundraiser updateFundraiser(Fundraiser fundraiser) throws IOException;;
    Fundraiser addFundraiser(Fundraiser fundraiser) throws IOException;;
    Optional<Fundraiser> findById(Long id);

    void removeFundraiser(Long id);
}
