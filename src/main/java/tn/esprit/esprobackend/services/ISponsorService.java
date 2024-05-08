package tn.esprit.esprobackend.services;

import java.util.List;
import tn.esprit.esprobackend.entities.Sponsor;
public interface ISponsorService {
    public List<Sponsor> retrieveAllSponsors();
    public Sponsor retrieveSponsor(Long SponsorId);
    public Sponsor addSponsor(Sponsor c);
    public void removeSponsor(Long SponsorId);
    public Sponsor modifySponsor(Sponsor Sponsor);
}
