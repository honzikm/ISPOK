/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.pokerclock;

import ispok.dto.TournamentSessionDto;
import ispok.dto.VisitorDto;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public class Sitout {

    private VisitorDto visitor;

    private TournamentSessionDto tournamentSession;

    private Long id;

    Sitout(VisitorDto visitorDto, TournamentSessionDto tournamentSessionDto) {
        this.visitor = visitorDto;
        this.tournamentSession = tournamentSessionDto;
        this.id = visitorDto.getId();
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public Long getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get the value of tournamentSession
     *
     * @return the value of tournamentSession
     */
    public TournamentSessionDto getTournamentSession() {
        return tournamentSession;
    }

    /**
     * Set the value of tournamentSession
     *
     * @param tournamentSession new value of tournamentSession
     */
    public void setTournamentSession(TournamentSessionDto tournamentSession) {
        this.tournamentSession = tournamentSession;
    }

    /**
     * Get the value of visitor
     *
     * @return the value of visitor
     */
    public VisitorDto getVisitor() {
        return visitor;
    }

    /**
     * Set the value of visitor
     *
     * @param visitor new value of visitor
     */
    public void setVisitor(VisitorDto visitor) {
        this.visitor = visitor;
    }

}
