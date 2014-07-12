/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.bo;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Country extends AbstractBusinessObject {

    @Column(nullable = false, length = 2)
    private String alpha2;
    @Column(nullable = false, length = 3)
    private String alpha3;
    @Column(nullable = false, length = 100)
    private String cze;
    @Column(nullable = false, length = 100)
    private String deu;
    @Column(nullable = false, length = 100)
    private String eng;
    @Column(nullable = false, length = 100)
    private String spa;
    @Column(nullable = false, length = 100)
    private String fra;
    @Column(nullable = false, length = 100)
    private String ita;
    @Column(nullable = false, length = 100)
    private String nld;

    public Country() {
    }

    public Country(Long id, String langEn) {
        this.id = id;
        this.eng = langEn;
    }

    public String getCze() {
        return cze;
    }

    public void setCze(String cze) {
        this.cze = cze;
    }

    public String getDeu() {
        return deu;
    }

    public void setDeu(String deu) {
        this.deu = deu;
    }

    public String getEng() {
        return eng;
    }

    public void setEng(String eng) {
        this.eng = eng;
    }

    public String getSpa() {
        return spa;
    }

    public void setSpa(String spa) {
        this.spa = spa;
    }

    public String getFra() {
        return fra;
    }

    public void setFra(String fra) {
        this.fra = fra;
    }

    public String getIta() {
        return ita;
    }

    public void setIta(String ita) {
        this.ita = ita;
    }

    public String getNld() {
        return nld;
    }

    public void setNld(String nld) {
        this.nld = nld;
    }

    /**
     * Get the value of alpha3
     *
     * @return the value of alpha3
     */
    public String getAlpha3() {
        return alpha3;
    }

    /**
     * Set the value of alpha3
     *
     * @param alpha3 new value of alpha3
     */
    public void setAlpha3(String alpha3) {
        this.alpha3 = alpha3;
    }

    /**
     * Get the value of alpha2
     *
     * @return the value of alpha2
     */
    public String getAlpha2() {
        return alpha2;
    }

    /**
     * Set the value of alpha2
     *
     * @param alpha2 new value of alpha2
     */
    public void setAlpha2(String alpha2) {
        this.alpha2 = alpha2;
    }

}
