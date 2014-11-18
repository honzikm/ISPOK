/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.bo;

import ispok.dto.EmployeeDto;
import ispok.provider.HashProvider;
import javax.persistence.Column;
import javax.persistence.Entity;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

/**
 *
 * @author Jan
 */
@Entity
@Configurable(preConstruction = true, autowire = Autowire.BY_NAME, dependencyCheck = true)
public class Employee extends AbstractBusinessObject {

    @Column(unique = true, nullable = false)
    private String username;
    @Column(length = 40, nullable = false) //40 je hash od SHA1
    private String salt;
    @Column(length = 40, nullable = false) //40 je hash od SHA1
    private String password;

    @Column(nullable = false, name = "receptionist")
    private boolean isReceptionist;
    @Column(nullable = false, name = "cashier")
    private boolean isCashier;
    @Column(nullable = false, name = "floorman")
    private boolean isFloorman;
    @Column(nullable = false, name = "manager")
    private boolean isManager;

    @Autowired(required = true)
    private transient HashProvider hashProvider; 

    public Employee() {
    }

    public Employee(EmployeeDto employeeDto) {
        this.username = employeeDto.getUsername();
        this.salt = hashProvider.computeHash(System.nanoTime() + "");
        this.password = hashProvider.computeHash(employeeDto.getPassword() + salt);
        this.isReceptionist = employeeDto.isReceptionist();
        this.isCashier = employeeDto.isCashier();
        this.isFloorman = employeeDto.isFloorman();
        this.isManager = employeeDto.isManager();
    }

    public HashProvider getHashProvider() {
        return hashProvider;
    }

    public void setHashProvider(HashProvider hashProvider) {
        this.hashProvider = hashProvider;
    }

    public Boolean isReceptionist() {
        return isReceptionist;
    }

    public void setIsReceptionist(Boolean isReceptionist) {
        this.isReceptionist = isReceptionist;
    }

    public Boolean isCashier() {
        return isCashier;
    }

    public void setIsCashier(Boolean isCashier) {
        this.isCashier = isCashier;
    }

    public Boolean isFloorman() {
        return isFloorman;
    }

    public void setIsFloorman(Boolean isFloorman) {
        this.isFloorman = isFloorman;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
//        if (hashProvider == null) {
//            hashProvider = new SHA1Provider();
//        }
        hashPassw = hashProvider.computeHash(password + salt);
        return hashPassw.equals(this.password);
    }

    public boolean isManager() {
        return isManager;
    }

    /**
     * @param isAdmin the isAdmin to set
     */
    public void setIsManager(boolean isAdmin) {
        this.isManager = isAdmin;
    }
}
