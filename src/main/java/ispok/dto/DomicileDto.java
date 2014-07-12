/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.dto;

public class DomicileDto extends AbstractDto {

    private String address1;
    private String address2;
    private Long cityId;
    private Long regionId;
    private Long countryId;
    private Long postalCodeId;

    public DomicileDto() {
    }

    /**
     * Get the value of regionId
     *
     * @return the value of regionId
     */
    public Long getRegionId() {
        return regionId;
    }

    /**
     * Set the value of regionId
     *
     * @param regionId new value of regionId
     */
    public void setRegionId(Long regionId) {
        this.regionId = regionId;
    }

    /**
     * Get the value of cityId
     *
     * @return the value of cityId
     */
    public Long getCityId() {
        return cityId;
    }

    /**
     * Set the value of cityId
     *
     * @param cityId new value of cityId
     */
    public void setCityId(Long cityId) {
        this.cityId = cityId;
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
     * Get the value of countryId
     *
     * @return the value of countryId
     */
    public Long getCountryId() {
        return countryId;
    }

    /**
     * Set the value of countryId
     *
     * @param countryId new value of countryId
     */
    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    /**
     * Get the value of postalCodeId
     *
     * @return the value of postalCodeId
     */
    public Long getPostalCodeId() {
        return postalCodeId;
    }

    /**
     * Set the value of postalCodeId
     *
     * @param postalCodeId new value of postalCodeId
     */
    public void setPostalCodeId(Long postalCodeId) {
        this.postalCodeId = postalCodeId;
    }

}
