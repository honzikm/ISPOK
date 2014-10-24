/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import ispok.provider.HashProvider;

/**
 *
 * @author Jan
 */
@Entity
@Table(name = "admins")
@Configurable(preConstruction=true)
public class Admin extends AbstractBusinessObject {

    private String name;
    @Column(length = 40, nullable = false) //40 je hash od SHA1
    private String salt;
    @Column(length = 40, nullable = false) //40 je hash od SHA1
    private String password;

    @Autowired
    private transient HashProvider hashProvider; //transient fields are not persisted

    public HashProvider getHashProvider() {
        return hashProvider;
    }

    public void setHashProvider(HashProvider hashProvider) {
        this.hashProvider = hashProvider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.salt = hashProvider.computeHash(System.nanoTime() + "");
        this.password = hashProvider.computeHash(password + salt);
    }

    public boolean hasPassword(String password) {
        String hashPassw;
        hashPassw = hashProvider.computeHash(password + salt);
        return hashPassw.equals(this.password);
    }
}
