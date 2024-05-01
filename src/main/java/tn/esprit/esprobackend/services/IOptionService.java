package tn.esprit.esprobackend.services;

import tn.esprit.esprobackend.entities.Option;

import java.util.List;
public interface IOptionService {
    public List<Option> retrieveAllOptions();
    public Option retrieveOption(Long optionId);
    public Option addOption(Option c);
    public void removeOption(Long optionId);
    public Option modifyOption(Option option);
   // public void generateClassroomsForOption(Option option);
    public void updateClassroomsWhenOptionIsUpdated(Option option);
   // public void recreateClassroomsWhenOptionUpdates(Option option);
   public void recreateClassroomsWhenOptionUpdates(Option option, String startYear, String endYear);
    public void generateClassroomsForOption(Option option, String startYear, String endYear);
}
