/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.pres.bb;

import ispok.helper.FacesUtil;
import ispok.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan
 */
@Component
public class StaffEdit {

    private String username;
    private String password;
    private boolean isReceptionist;
    private boolean isCashier;
    private boolean isFloorman;
    private boolean isManager;

    private Long[] selectedEmployees;

    @Autowired
    EmployeeService employeeService;

    public void addEmployee() {
        try {
//            employeeService.addEmployee(username, password, isReceptionist, isCashier, isFloorman, isManager);
            FacesUtil.addMessage("User was sucessfully registered");

        } catch (DataIntegrityViolationException e) {
            FacesUtil.addMessage("User already exists");
        }
    }

    public void deleteSelectedEmployees() {
        int cnt = selectedEmployees.length;
        for (int i = 0; i < cnt; i++) {
            employeeService.deleteEmployee(selectedEmployees[i]);
        }
        FacesUtil.addMessage("User delete successful");
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

    public boolean getIsReceptionist() {
        return isReceptionist;
    }

    public void setIsReceptionist(boolean isReceptionist) {
        this.isReceptionist = isReceptionist;
    }

    public boolean getIsCashier() {
        return isCashier;
    }

    public void setIsCashier(boolean isCashier) {
        this.isCashier = isCashier;
    }

    public boolean getIsFloorman() {
        return isFloorman;
    }

    public void setIsFloorman(boolean isFloorman) {
        this.isFloorman = isFloorman;
    }

    public boolean getIsManager() {
        return isManager;
    }

    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }

    public Long[] getSelectedEmployees() {
        return selectedEmployees;
    }

    public void setSelectedEmployees(Long[] selectedEmployees) {
        this.selectedEmployees = selectedEmployees;
    }
}
