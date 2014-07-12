/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.pres.bb;

import ispok.dto.EmployeeDto;
import ispok.service.IEmployeeService;
import java.io.Serializable;
import java.util.List;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component
@SessionScoped
public class EmployeeEdit implements Serializable {

    private String username;
    private String password;
    private boolean isReceptionist;
    private boolean isCashier;
    private boolean isFloorman;
    private boolean isManager;

    private boolean changePassword;

    private List<EmployeeDto> employees;

    private List<EmployeeDto> filteredEmployees;

    @Autowired
    private IEmployeeService employeeService;

    private EmployeeDto[] selected;

    private String selectedSize;

    public List<EmployeeDto> getEmployees() {
        if (employees == null) {
            employees = employeeService.getAllEmployees();
        }
        return employees;
    }

    public void addEmployee() {

        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = ResourceBundle.getBundle("ispok/pres/inter/ispok", context.getViewRoot().getLocale());

        EmployeeDto employeeDto = new EmployeeDto(username, password, isReceptionist, isCashier, isFloorman, isManager);
        try {
            employeeDto.setId(employeeService.addEmployee(employeeDto));
            employees.add(employeeDto);
            if (filteredEmployees != null) {
                filteredEmployees.add(employeeDto);
            }
            clearEdit();
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("success"), bundle.getString("employee_add_success")));
        } catch (DataIntegrityViolationException e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, bundle.getString("fail"), e.toString()));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, bundle.getString("fail"), e.toString()));
        }
    }

    public void updateEmployee() {

        FacesContext context = FacesContext.getCurrentInstance();
        ResourceBundle bundle = ResourceBundle.getBundle("ispok/pres/inter/ispok", context.getViewRoot().getLocale());

        EmployeeDto employeeDto = selected[0];

        employeeDto.setReceptionist(isReceptionist);
        employeeDto.setCashier(isCashier);
        employeeDto.setFloorman(isFloorman);
        employeeDto.setManager(isManager);

        if (changePassword == true) {
            employeeDto.setPassword(password);
            changePassword = false;
        }
        employeeService.updateEmployee(employeeDto);
        
        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("success"), bundle.getString("employee_update_success")));
    }

    public void clearEdit() {
        this.username = null;
        this.password = null;
        this.isReceptionist = false;
        this.isCashier = false;
        this.isFloorman = false;
        this.isManager = false;

        this.changePassword = false;
        System.out.println("*********** CLEAR CANCEL" + changePassword);
    }

    public void loadEmployee() {
        username = selected[0].getUsername();
        System.out.println("*********** CLEAR LOAD");
        changePassword = false;
        password = null;
        isCashier = selected[0].isCashier();
        isReceptionist = selected[0].isReceptionist();
        isFloorman = selected[0].isFloorman();
        isManager = selected[0].isManager();
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

    public boolean isIsReceptionist() {
        return isReceptionist;
    }

    public void setIsReceptionist(boolean isReceptionist) {
        this.isReceptionist = isReceptionist;
    }

    public boolean isIsCashier() {
        return isCashier;
    }

    public void setIsCashier(boolean isCashier) {
        this.isCashier = isCashier;
    }

    public boolean isIsFloorman() {
        return isFloorman;
    }

    public void setIsFloorman(boolean isFloorman) {
        this.isFloorman = isFloorman;
    }

    public boolean isIsManager() {
        return isManager;
    }

    public void setIsManager(boolean isManager) {
        this.isManager = isManager;
    }

    public List<EmployeeDto> getFilteredEmployees() {
        return filteredEmployees;
    }

    public void setFilteredEmployees(List<EmployeeDto> filteredEmployees) {
        this.filteredEmployees = filteredEmployees;
    }

    public String getSelectedSize() {
        System.out.print("Get selected size: ");
        if (selected != null) {
            System.out.println("***************" + selected.length);
            return "***************" + selected.length;
        } else {
            System.out.println("**************** null");
            return "*************** null";
        }
    }

    public EmployeeDto[] getSelected() {
        System.out.print("Get selected: ");
        if (selected != null) {
            System.out.println("***************" + selected.length);
        } else {
            System.out.println("**************** null");
        }
        return selected;
    }

    public void setSelected(EmployeeDto[] selected) {
        System.out.print("Set selected: ");
        this.selected = selected;
        if (selected != null) {
            System.out.println("***************" + selected.length);
        } else {
            System.out.println("**************** null");
        }
    }

    public void delete() {
        for (EmployeeDto e : selected) {
            employeeService.deleteEmployee(e.getId());
            employees.remove(e);
            if (filteredEmployees != null && filteredEmployees.contains(e)) {
                filteredEmployees.remove(e);
            }
        }
        selected = new EmployeeDto[0];
    }

    public void onRowSelect(SelectEvent event) {

        System.out.println("***********************" + event.getClass());
        if (selected != null) {
            System.out.println("***************" + selected.length);
            for (EmployeeDto e : selected) {
                System.out.println(e.getUsername());
            }
        } else {
            System.out.println("**************** null");
        }
    }

    public boolean isChangePassword() {
        return changePassword;
    }

    public void setChangePassword(boolean changePassword) {
        this.changePassword = changePassword;
    }

}
