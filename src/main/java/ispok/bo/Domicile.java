/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.bo;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Domicile extends AbstractBusinessObject {

    private String address1;

    private String address2;

    @ManyToOne
    private Country country;

    @ManyToOne
    private PostalCode postalCode;

    @ManyToOne
    private City city;

    @ManyToOne
    private Region region;

    public Domicile() {
    }

    /**
     * Get the value of address2
     *
     * @return the value of address2
     */
    public String getAddress2() {
        return address2;
    }

    /**
     * Set the value of address2
     *
     * @param address2 new value of address2
     */
    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    /**
     * Get the value of address1
     *
     * @return the value of address1
     */
    public String getAddress1() {
        return address1;
    }

    /**
     * Set the value of address1
     *
     * @param address1 new value of address1
     */
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    /**
     * Get the value of country
     *
     * @return the value of country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Set the value of country
     *
     * @param country new value of country
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * Get the value of region
     *
     * @return the value of region
     */
    public Region getRegion() {
        return region;
    }

    /**
     * Set the value of region
     *
     * @param region new value of region
     */
    public void setRegion(Region region) {
        this.region = region;
    }

    /**
     * Get the value of city
     *
     * @return the value of city
     */
    public City getCity() {
        return city;
    }

    /**
     * Set the value of city
     *
     * @param city new value of city
     */
    public void setCity(City city) {
        this.city = city;
    }

    public PostalCode getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(PostalCode postalCode) {
        this.postalCode = postalCode;
    }
}
