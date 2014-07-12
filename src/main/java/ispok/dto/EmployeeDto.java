/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.dto;

import ispok.bo.Employee;

/**
 *
 * @author Jan
 */
public class EmployeeDto extends AbstractDto {

    private String username;
    private String password;
    private boolean receptionist;
    private boolean cashier;
    private boolean floorman;
    private boolean manager;

    public EmployeeDto(Long id, String username, String password, boolean isReceptionist, boolean isCashier, boolean isFloorman, boolean isManager) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.receptionist = isReceptionist;
        this.cashier = isCashier;
        this.floorman = isFloorman;
        this.manager = isManager;
    }

    public EmployeeDto() {
    }
    
    

    public EmployeeDto(String username, String password, boolean receptionist, boolean cashier, boolean floorman, boolean manager) {
        this(null, username, password, receptionist, cashier, floorman, manager);
    }

    public EmployeeDto(Employee employee) {
        this(employee.getId(), employee.getUsername(), employee.getPassword(), employee.isReceptionist(), employee.isCashier(), employee.isFloorman(), employee.isManager());
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isReceptionist() {
        return receptionist;
    }

    public void setReceptionist(boolean receptionist) {
        this.receptionist = receptionist;
    }

    public boolean isCashier() {
        return cashier;
    }

    public void setCashier(boolean cashier) {
        this.cashier = cashier;
    }

    public boolean isFloorman() {
        return floorman;
    }

    public void setFloorman(boolean floorman) {
        this.floorman = floorman;
    }

    public boolean isManager() {
        return manager;
    }

    public void setManager(boolean manager) {
        this.manager = manager;
    }
}
