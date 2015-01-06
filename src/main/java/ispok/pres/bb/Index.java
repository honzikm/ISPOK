/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.pres.bb;

import ispok.dto.TournamentDto;
import ispok.service.TournamentService;
import java.util.Date;
import java.util.List;
import javax.faces.bean.SessionScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component
@SessionScoped
public class Index {

    @Autowired
    TournamentService tournamentService;

    public List<TournamentDto> getUpcomingTournaments() {
        Date date = new Date();
        return tournamentService.getUpcoming(date);
    }
}
