/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.dto;

import ispok.bo.Visitor;
import ispok.provider.HashProvider;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

@Configurable
public class VisitorDto extends AbstractDto {

    private String firstName;
    private String lastName;
    private Date birthDate;
    private String nin;
    private String nickname;
    private String telephone;
    private String email;
    private String sex;
    private String password;
    private String passwordHash;
    private String saltHash;
    private String idNumber;
    private Integer bonusPoints;
    private byte[] photo;
    private Long citizenshipId;
    private Long domicileId;

    @Autowired
    HashProvider hashProvider;

    public VisitorDto() {
    }

    public VisitorDto(Visitor visitor) {
        this.id = visitor.getId();
        this.firstName = visitor.getFirstName();
        this.lastName = visitor.getLastName();
        this.birthDate = visitor.getBirthDate();
        this.nin = visitor.getNin();
        this.nickname = visitor.getNickname();
        this.telephone = visitor.getTelephone();
        this.email = visitor.getEmail();
        this.sex = visitor.getSex();
        this.passwordHash = visitor.getPasswordHash();
        this.saltHash = visitor.getSaltHash();
        this.idNumber = visitor.getIdNumber();
        this.bonusPoints = visitor.getBonusPoints();
        this.photo = visitor.getPhoto().clone();
        this.citizenshipId = visitor.getCitizenship().getId();
        this.domicileId = visitor.getDomicile().getId();
    }

    /**
     * Get the value of photo
     *
     * @return the value of photo
     */
    public byte[] getPhoto() {
//        return photo;
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
     * Get the value of domicileId
     *
     * @return the value of domicileId
     */
    public Long getDomicileId() {
        return domicileId;
    }

    /**
     * Set the value of domicileId
     *
     * @param domicileId new value of domicileId
     */
    public void setDomicileId(Long domicileId) {
        this.domicileId = domicileId;
    }

    /**
     * Get the value of citizenshipId
     *
     * @return the value of citizenshipId
     */
    public Long getCitizenshipId() {
        return citizenshipId;
    }

    /**
     * Set the value of citizenshipId
     *
     * @param citizenshipId new value of citizenshipId
     */
    public void setCitizenshipId(Long citizenshipId) {
        this.citizenshipId = citizenshipId;
    }

    /**
     * Get the value of bonusPoints
     *
     * @return the value of bonusPoints
     */
    public Integer getBonusPoints() {
        return bonusPoints;
    }

    /**
     * Set the value of bonusPoints
     *
     * @param bonusPoints new value of bonusPoints
     */
    public void setBonusPoints(Integer bonusPoints) {
        this.bonusPoints = bonusPoints;
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

    public boolean hasPassword(String password) {
        String hashPassw;
        hashPassw = hashProvider.computeHash(password + saltHash);
        boolean equals = hashPassw.equals(passwordHash);
        return equals;
    }

    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
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

}
