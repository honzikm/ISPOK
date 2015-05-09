/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.helper;

import ispok.dto.VisitorDto;
import ispok.pokerclock.TournamentController;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public class SitoutsLazyDataModel extends LazyDataModel<VisitorDto> {

    private TournamentController tournamentController;

    @Override
    public List<VisitorDto> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {

        List<VisitorDto> sitoutPlayers = tournamentController.getSitoutPlayers();

        switch (sortField) {
            case "id":
                sitoutPlayers.sort(new Comparator<VisitorDto>() {
                    @Override
                    public int compare(VisitorDto o1, VisitorDto o2) {
                        if (Objects.equals(o1.getId(), o1.getId())) {
                            return 0;
                        }
                        return o1.getId() < o2.getId() ? -1 : 1;
                    }
                });
                break;
            default:
                break;
        }

        return sitoutPlayers;
    }

    public void setTournamentController(TournamentController tournamentController) {
        this.tournamentController = tournamentController;
    }

}
