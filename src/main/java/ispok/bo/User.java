/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.bo;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

import ispok.provider.HashProvider;

/**
 * Entity, which represents user of the system
 *
 * @author mickapa1
 */
@Entity
@Table(name = "users") //user je SQL klicove slovo, nejde ho pouzit po pojmenovani tabulky
@Configurable(preConstruction = true)
public class User extends AbstractBusinessObject {

    @Column(unique = true, nullable = false)
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    @Column(length = 40, nullable = false) //40 je hash od SHA1
    private String salt;
    @Column(length = 40, nullable = false) //40 je hash od SHA1
    private String password;


    @Autowired
    private transient HashProvider hashProvider; //transient fields are not persisted

    public User() {
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

//    public HashProvider getHashProvider() {
//        return hashProvider;
//    }
//
//    public void setHashProvider(HashProvider hashProvider) {
//        this.hashProvider = hashProvider;
//    }
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
        if (hashProvider.computeHash(password + salt).equals(this.password)) {
            return true;
        }
        return false;
    }


}
