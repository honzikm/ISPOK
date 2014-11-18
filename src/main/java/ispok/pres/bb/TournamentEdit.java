/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.pres.bb;

import ispok.dto.TournamentDto;
import ispok.helper.TournamentLazyDataModel;
import ispok.service.TournamentService;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@SessionScoped
public class TournamentEdit {
    
    @Autowired
    private TournamentService tournamentService;

    private LazyDataModel<TournamentDto> tournamentLazyModel;
    private TournamentDto selectedTournament;
    private List<TournamentDto> filteredTournaments;

    @PostConstruct
    public void init() {
        tournamentLazyModel = new TournamentLazyDataModel(tournamentService);
    }

    public LazyDataModel<TournamentDto> getTournamentLazyModel() {
        return tournamentLazyModel;
    }

    /**
     * Get the value of selectedTournament
     *
     * @return the value of selectedTournament
     */
    public TournamentDto getSelectedTournament() {
        return selectedTournament;
    }

    /**
     * Set the value of selectedTournament
     *
     * @param selectedTournament new value of selectedTournament
     */
    public void setSelectedTournament(TournamentDto selectedTournament) {
        this.selectedTournament = selectedTournament;
    }

    /**
     * Get the value of filteredTournaments
     *
     * @return the value of filteredTournaments
     */
    public List<TournamentDto> getFilteredTournaments() {
        return filteredTournaments;
    }

    /**
     * Set the value of filteredTournaments
     *
     * @param filteredTournaments new value of filteredTournaments
     */
    public void setFilteredTournaments(List<TournamentDto> filteredTournaments) {
        this.filteredTournaments = filteredTournaments;
    }

}
