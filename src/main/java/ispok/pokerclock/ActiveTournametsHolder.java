/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.pokerclock;

import ispok.dto.LevelDto;
import ispok.dto.PayoutPlaceDto;
import ispok.dto.PayoutStructureDto;
import ispok.dto.TournamentDto;
import ispok.dto.TournamentStructureDto;
import ispok.service.PayoutStructureService;
import ispok.service.TournamentService;
import ispok.service.TournamentStructureService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ApplicationScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component
@ApplicationScoped
public class ActiveTournametsHolder {

    @Autowired
    private TournamentService tournamentService;
    @Autowired
    private TournamentStructureService tournamentStructureService;
    @Autowired
    private PayoutStructureService payoutStructureService;

    List<TournamentController> tournamentControllers = new ArrayList<>(20);

    public synchronized TournamentController loadTournament(Long id) {
        for (TournamentController tc : tournamentControllers) {
            if (tc.getTournamentDto().getId().equals(id)) {
                return tc;
            }
        }

        TournamentController tc = new TournamentController();
        TournamentDto tournamentDto = tournamentService.getById(id);
        TournamentStructureDto tournamentStructureDto = tournamentStructureService.getById(tournamentDto.getTournamentStructureId());
        List<LevelDto> levelDtos = tournamentStructureService.getLevelsByTournamentStructureId(tournamentStructureDto.getId());
        PayoutStructureDto payoutStructureDto = payoutStructureService.getStructureById(tournamentDto.getPayoutStructureId());
        List<PayoutPlaceDto> payoutPlaceDtos = new ArrayList<>(50);
        for (Long placaId : payoutStructureDto.getPayoutPlaceIds()) {
            PayoutPlaceDto payoutPlaceDto = payoutStructureService.getPlaceById(placaId);
            payoutPlaceDtos.add(payoutPlaceDto);
        }
        tc.setTournamentDto(tournamentDto);
        tc.setTournamentStructureDto(tournamentStructureDto);
        tc.setLevels(levelDtos);
        tc.setPayoutStructure(payoutStructureDto);
        tc.setPayoutPlaces(payoutPlaceDtos);

        tournamentControllers.add(tc);
        return tc;
    }

    public boolean contains(Long id) {
        for (TournamentController tc : tournamentControllers) {
            if (tc.getTournamentDto().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
