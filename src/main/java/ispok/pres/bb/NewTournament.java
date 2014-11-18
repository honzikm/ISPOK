/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.pres.bb;

import javax.faces.bean.SessionScoped;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component
@SessionScoped
public class NewTournament {

    private String series;

    /**
     * Get the value of series
     *
     * @return the value of series
     */
    public String getSeries() {
        return series;
    }

    /**
     * Set the value of series
     *
     * @param series new value of series
     */
    public void setSeries(String series) {
        this.series = series;
    }

}
