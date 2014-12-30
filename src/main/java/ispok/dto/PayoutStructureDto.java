/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.dto;

import java.util.List;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public class PayoutStructureDto extends AbstractDto {

    private String name;
    private List<Long> payoutPlaceIds;

    /**
     * Get the value of payoutPlaceIds
     *
     * @return the value of payoutPlaceIds
     */
    public List<Long> getPayoutPlaceIds() {
        return payoutPlaceIds;
    }

    /**
     * Set the value of payoutPlaceIds
     *
     * @param payoutPlaceIds new value of payoutPlaceIds
     */
    public void setPayoutPlaceIds(List<Long> payoutPlaceIds) {
        this.payoutPlaceIds = payoutPlaceIds;
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
