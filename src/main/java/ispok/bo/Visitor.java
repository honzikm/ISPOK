/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.bo;

import ispok.provider.HashProvider;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.springframework.beans.factory.annotation.Autowired;

@Entity(name = "visitor")
public class Visitor extends AbstractBusinessObject {

    @Column(nullable = false, name = "first_name")
    private String firstName;
    @Column(nullable = false, name = "last_name")
    private String lastName;
    @Column(nullable = false, name = "birth_date")
    @Temporal(TemporalType.DATE)
    private Date birthDate;
    @Column(nullable = false)
    private String nin;
    @Column
    private String nickname;
    @Column
    private String telephone;
    @Column(unique = true)
    private String email;
    @Column(length = 1)
    private String sex;
    @Column(nullable = false, name = "password_hash")
    private String passwordHash;
    @Column(nullable = false, name = "salt_hash")
    private String saltHash;
    @Column(nullable = true, name = "id_number")
    private String idNumber;
    @Column(nullable = false, name = "bonus_points")
    private Integer bonusPoints;

    @Column
    private byte[] photo;

    @ManyToOne
    private Country citizenship;

    @ManyToOne
    private Domicile domicile;

    @Autowired
    @Transient
    private HashProvider hashProvider; //transient fields are not persisted

    public Visitor() {
    }

    public HashProvider getHashProvider() {
        return hashProvider;
    }

    public void setHashProvider(HashProvider hashProvider) {
        this.hashProvider = hashProvider;
    }

    public void setPassword(String password) {
        this.saltHash = hashProvider.computeHash(System.nanoTime() + "");
        this.passwordHash = hashProvider.computeHash(password + saltHash);
    }

    public boolean hasPassword(String password) {
        String hashPassw;
        hashPassw = hashProvider.computeHash(password + saltHash);
        return hashPassw.equals(this.passwordHash);
    }

    /**
     * Get the value of domicile
     *
     * @return the value of domicile
     */
    public Domicile getDomicile() {
        return domicile;
    }

    /**
     * Set the value of domicile
     *
     * @param domicile new value of domicile
     */
    public void setDomicile(Domicile domicile) {
        this.domicile = domicile;
    }

    /**
     * Get the value of citizenship
     *
     * @return the value of citizenship
     */
    public Country getCitizenship() {
        return citizenship;
    }

    /**
     * Set the value of citizenship
     *
     * @param citizenship new value of citizenship
     */
    public void setCitizenship(Country citizenship) {
        this.citizenship = citizenship;
    }

    /**
     * Get the value of photo
     *
     * @return the value of photo
     */
    public byte[] getPhoto() {
        return photo;
    }

    /**
     * Set the value of photo
     *
     * @param photo new value of photo
     */
    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    /**
     * Get the value of points
     *
     * @return the value of points
     */
    public Integer getBonusPoints() {
        return bonusPoints;
    }

    /**
     * Set the value of points
     *
     * @param points new value of points
     */
    public void setBonusPoints(Integer points) {
        this.bonusPoints = points;
    }

    /**
     * Get the value of idNumber
     *
     * @return the value of idNumber
     */
    public String getIdNumber() {
        return idNumber;
    }

    /**
     * Set the value of idNumber
     *
     * @param idNumber new value of idNumber
     */
    public void setIdNumber(String idNumber) {
        this.idNumber = idNumber;
    }

    /**
     * Get the value of saltHash
     *
     * @return the value of saltHash
     */
    public String getSaltHash() {
        return saltHash;
    }

    /**
     * Set the value of saltHash
     *
     * @param saltHash new value of saltHash
     */
    public void setSaltHash(String saltHash) {
        this.saltHash = saltHash;
    }

    /**
     * Get the value of passwordHash
     *
     * @return the value of passwordHash
     */
    public String getPasswordHash() {
        return passwordHash;
    }

    /**
     * Set the value of passwordHash
     *
     * @param passwordHash new value of passwordHash
     */
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    /**
     * Get the value of nickname
     *
     * @return the value of nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * Set the value of nickname
     *
     * @param nickname new value of nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * Get the value of sex
     *
     * @return the value of sex
     */
    public String getSex() {
        return sex;
    }

    /**
     * Set the value of sex
     *
     * @param sex new value of sex
     */
    public void setSex(String sex) {
        this.sex = sex;
    }

    /**
     * Get the value of email
     *
     * @return the value of email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Set the value of email
     *
     * @param email new value of email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Get the value of telephone
     *
     * @return the value of telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Set the value of telephone
     *
     * @param telephone new value of telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /**
     * Get the value of nin
     *
     * @return the value of nin
     */
    public String getNin() {
        return nin;
    }

    /**
     * Set the value of nin
     *
     * @param nin new value of nin
     */
    public void setNin(String nin) {
        this.nin = nin;
    }

    /**
     * Get the value of birthDate
     *
     * @return the value of birthDate
     */
    public Date getBirthDate() {
        return birthDate;
    }

    /**
     * Set the value of birthDate
     *
     * @param birthDate new value of birthDate
     */
    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    /**
     * Get the value of firstName
     *
     * @return the value of firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the value of firstName
     *
     * @param firstName new value of firstName
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Get the value of lastName
     *
     * @return the value of lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the value of lastName
     *
     * @param lastName new value of lastName
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
