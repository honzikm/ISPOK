/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.bo;

import java.io.Serializable;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
public class BetsetId implements Serializable {

    private Float bigBlind;
    private Float smallBlind;
    private Float ante;

    public BetsetId(float bigBlind, float smallBlind, float ante) {
        this.bigBlind = bigBlind;
        this.smallBlind = smallBlind;
        this.ante = ante;
    }

    @Override
    public int hashCode() {
        return ((bigBlind.hashCode() << 20) & 0xFFF00000) | ((smallBlind.hashCode() << 10) & 0xFFC00) | ante.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof BetsetId)) {
            return false;
        }
        Betset bs = (Betset) obj;
        return (this.bigBlind == bs.getBigBlind() && this.smallBlind == bs.getSmallBlind() && this.ante == bs.getAnte());
    }
}
