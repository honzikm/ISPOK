/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.pres.bb;

import ispok.pokerclock.ActiveTournametsHolder;
import ispok.dto.LevelDto;
import ispok.dto.OfficeDto;
import ispok.dto.PayoutPlaceDto;
import ispok.dto.PayoutStructureDto;
import ispok.dto.SeriesDto;
import ispok.dto.TournamentDto;
import ispok.dto.TournamentStructureDto;
import ispok.helper.FacesUtil;
import ispok.service.OfficeService;
import ispok.service.PayoutStructureService;
import ispok.service.SeriesService;
import ispok.service.TournamentService;
import ispok.service.TournamentStructureService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.SessionScoped;
import org.primefaces.model.LazyDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

@Component
@SessionScoped
public class TournamentEdit {

    @Autowired
    private SeriesService seriesService;
    @Autowired
    private OfficeService officeService;
    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private TournamentStructureService tournamentStructureService;
    @Autowired
    private LazyDataModel<TournamentDto> tournamentLazyDataModel;
    @Autowired
    private PayoutStructureService payoutStructureService;
    @Autowired
    private ActiveTournametsHolder activeTournametsHolder;

    private TournamentDto tournament = new TournamentDto();
    private TournamentDto selectedTournament;
    private List<TournamentDto> filteredTournaments;
    private List<TournamentStructureDto> tournamentStructures;
    private List<PayoutPlaceDto> payoutPlaces = new ArrayList<>(20);
    private Long payoutStructureId;
    private List<PayoutStructureDto> payoutStructures;
    private Long tournamentStructureId;
    private TournamentStructureDto tournamentStructure;
    private boolean newLevelStructure;
    private List<LevelDto> levels = new ArrayList<>(20);

    /**
     * Get the value of payoutPlaces
     *
     * @return the value of payoutPlaces
     */
    public List<PayoutPlaceDto> getPayoutPlaces() {
        return payoutPlaces;
    }

    /**
     * Set the value of payoutPlaces
     *
     * @param payoutPlaces new value of payoutPlaces
     */
    public void setPayoutPlaces(List<PayoutPlaceDto> payoutPlaces) {
        this.payoutPlaces = payoutPlaces;
    }

    /**
     * Get the value of payoutStructureId
     *
     * @return the value of payoutStructureId
     */
    public Long getPayoutStructureId() {
        return payoutStructureId;
    }

    /**
     * Set the value of payoutStructureId
     *
     * @param payoutStructureId new value of payoutStructureId
     */
    public void setPayoutStructureId(Long payoutStructureId) {
        this.payoutStructureId = payoutStructureId;
        PayoutStructureDto payoutStructure = payoutStructureService.getStructureById(payoutStructureId);
        payoutPlaces.clear();
        for (Long id : payoutStructure.getPayoutPlaceIds()) {
            payoutPlaces.add(payoutStructureService.getPlaceById(id));
        }
        tournament.setPayoutStructureId(payoutStructureId);
    }

    /**
     * Get the value of payoutStructures
     *
     * @return the value of payoutStructures
     */
    public List<PayoutStructureDto> getPayoutStructures() {
        payoutStructures = payoutStructureService.getAll();
        if (payoutStructures != null && !payoutStructures.isEmpty()) {
            setPayoutStructureId(payoutStructures.get(0).getId());
        } else {
            payoutPlaces.clear();
        }
        return payoutStructures;
    }

    /**
     * Set the value of payoutStructures
     *
     * @param payoutStructures new value of payoutStructures
     */
    public void setPayoutStructures(List<PayoutStructureDto> payoutStructures) {
        this.payoutStructures = payoutStructures;
    }

    /**
     * Get the value of tournamentStructureId
     *
     * @return the value of tournamentStructureId
     */
    public Long getTournamentStructureId() {
        return tournamentStructureId;
    }

    /**
     * Set the value of tournamentStructureId
     *
     * @param tournamentStructureId new value of tournamentStructureId
     */
    public void setTournamentStructureId(Long tournamentStructureId) {
        this.tournamentStructureId = tournamentStructureId;
        levels.clear();
        TournamentStructureDto tournamentStructure = null;
        for (TournamentStructureDto tsd : tournamentStructures) {
            if (tsd.getId().equals(tournamentStructureId)) {
                tournamentStructure = tsd;
            }
        }
        for (Long l : tournamentStructure.getLevelIds()) {
            LevelDto ldto = tournamentStructureService.getLevelById(l);
            levels.add(ldto);
        }
        tournament.setTournamentStructureId(tournamentStructureId);
    }

    /**
     * Get the value of tournamentStructure
     *
     * @return the value of tournamentStructure
     */
    public TournamentStructureDto getTournamentStructure() {
        return tournamentStructure;
    }

    /**
     * Set the value of tournamentStructure
     *
     * @param tournamentStructure new value of tournamentStructure
     */
    public void setTournamentStructure(TournamentStructureDto tournamentStructure) {
        this.tournamentStructure = tournamentStructure;
    }

    public List<TournamentStructureDto> getTournamentStructures() {
        tournamentStructures = tournamentStructureService.getAll();
        if (tournamentStructures != null && !tournamentStructures.isEmpty()) {
            setTournamentStructureId(tournamentStructures.get(0).getId());
        } else {
            levels.clear();
        }
        return tournamentStructures;
    }

    public void setTournamentStructures(List<TournamentStructureDto> tournamentStructures) {
        this.tournamentStructures = tournamentStructures;
    }

    /**
     * Get the value of newLevelStructure
     *
     * @return the value of newLevelStructure
     */
    public boolean getNewLevelStructure() {
        return newLevelStructure;
    }

    /**
     * Set the value of newLevelStructure
     *
     * @param newLevelStructure new value of newLevelStructure
     */
    public void setNewLevelStructure(boolean newLevelStructure) {
        this.newLevelStructure = newLevelStructure;
    }

    public List<OfficeDto> getOffices() {
        return officeService.getAll();
    }

    public List<SeriesDto> getSeries() {
        return seriesService.getAll();
    }

    public List<LevelDto> getLevels() {
        return levels;
    }

    /**
     *
     * @return
     */
    public LazyDataModel<TournamentDto> getTournamentLazyDataModel() {
        return tournamentLazyDataModel;
    }

    /**
     *
     * @return
     */
    public TournamentDto getTournament() {
        return tournament;
    }

    /**
     *
     * @param tournament
     */
    public void setTournament(TournamentDto tournament) {
        this.tournament = tournament;
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

    public String add() {
        TournamentDto tdto = new TournamentDto(tournament);
        tdto.setId(new Long(0));
        tdto.setFinish(null);
        tdto.setLevelNumber(0);
        tdto.setLevelTime(0);
        tournamentService.save(tdto);
        FacesUtil.addInfoMessage("success", "tournament_add_success");
        return "/admin/management/tournaments/tournaments.xhtml";
    }

    public void delete() {
        if (activeTournametsHolder.contains(tournament.getId()) || tournament.getFinish() != null) {
            FacesUtil.addErrorMessage("failed", "tournament_delete_fail");
            return;
        }
        try {
            tournamentService.delete(selectedTournament.getId());
        } catch (DataIntegrityViolationException dive) {
            FacesUtil.addErrorMessage("failed", "tournament_delete_fail");
            return;
        }
        FacesUtil.addInfoMessage("success", "tournament_delete_success");
    }
}
