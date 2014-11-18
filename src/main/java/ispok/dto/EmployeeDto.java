/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.dto;

import ispok.bo.Employee;
import ispok.provider.HashProvider;
import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

/**
 *
 * @author Jan
 */
@Configurable(autowire = Autowire.BY_NAME, preConstruction = true)
public class EmployeeDto extends AbstractDto {

    private String username;
    private String password;
    private String salt;
    private boolean receptionist;
    private boolean cashier;
    private boolean floorman;
    private boolean manager;

    @Autowired(required = true)
    private transient HashProvider hashProvider;

    /**
     *
     * @param id
     * @param username
     * @param password
     * @param salt
     * @param isReceptionist
     * @param isCashier
     * @param isFloorman
     * @param isManager
     */
    public EmployeeDto(Long id, String username, String password, String salt, boolean isReceptionist, boolean isCashier, boolean isFloorman, boolean isManager) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.salt = salt;
        this.receptionist = isReceptionist;
        this.cashier = isCashier;
        this.floorman = isFloorman;
        this.manager = isManager;
    }

    /**
     *
     */
    public EmployeeDto() {
    }

    public EmployeeDto(EmployeeDto employeeDto) {
        this.id = employeeDto.id;
        this.username = employeeDto.username;
        this.password = employeeDto.password;
        this.salt = employeeDto.salt;
        this.receptionist = employeeDto.receptionist;
        this.cashier = employeeDto.cashier;
        this.floorman = employeeDto.floorman;
        this.manager = employeeDto.manager;
    }

    /**
     *
     * @param username
     * @param password
     * @param salt
     * @param receptionist
     * @param cashier
     * @param floorman
     * @param manager
     */
    public EmployeeDto(String username, String password, String salt, boolean receptionist, boolean cashier, boolean floorman, boolean manager) {
        this(null, username, password, salt, receptionist, cashier, floorman, manager);
    }

    /**
     *
     * @param employee
     */
    public EmployeeDto(Employee employee) {
        this(employee.getId(), employee.getUsername(), employee.getPassword(), employee.getSalt(), employee.isReceptionist(), employee.isCashier(), employee.isFloorman(), employee.isManager());
    }

    /**
     * Get the value of salt
     *
     * @return the value of salt
     */
    public String getSalt() {
        return salt;
    }

    /**
     * Set the value of salt
     *
     * @param salt new value of salt
     */
    public void setSalt(String salt) {
        this.salt = salt;
    }

    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getPassword() {
        return password;
    }

    /**
     *
     * @param password
     * @return
     */
    public boolean hasPassword(String password) {
        String hashPassw;
        hashPassw = hashProvider.computeHash(password + salt);
        return hashPassw.equals(this.password);
    }

    /**
     *
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     *
     * @return
     */
    public boolean isReceptionist() {
        return receptionist;
    }

    /**
     *
     * @param receptionist
     */
    public void setReceptionist(boolean receptionist) {
        this.receptionist = receptionist;
    }

    /**
     *
     * @return
     */
    public boolean isCashier() {
        return cashier;
    }

    /**
     *
     * @param cashier
     */
    public void setCashier(boolean cashier) {
        this.cashier = cashier;
    }

    /**
     *
     * @return
     */
    public boolean isFloorman() {
        return floorman;
    }

    /**
     *
     * @param floorman
     */
    public void setFloorman(boolean floorman) {
        this.floorman = floorman;
    }

    /**
     *
     * @return
     */
    public boolean isManager() {
        return manager;
    }

    /**
     *
     * @param manager
     */
    public void setManager(boolean manager) {
        this.manager = manager;
    }
}
