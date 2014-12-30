/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.bo;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Entity
public class PayoutStructure extends AbstractBusinessObject {

    @Column(nullable = false)
    private String name;
    @ManyToMany
    @JoinTable
    private List<PayoutPlace> payoutPlaces;
    @OneToMany(mappedBy = "tournamentStructure")
    private List<Tournament> tournaments;

    public List<Tournament> getTournaments() {
        return tournaments;
    }

    public void setTournaments(List<Tournament> tournaments) {
        this.tournaments = tournaments;
    }

    public List<PayoutPlace> getPayoutPlaces() {
        return payoutPlaces;
    }

    public void setPayoutPlaces(List<PayoutPlace> payoutPlaces) {
        this.payoutPlaces = payoutPlaces;
    }

    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
    }

}
