/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.pres.bb;

import ispok.dto.EmployeeDto;
import ispok.helper.FacesUtil;
import ispok.service.EmployeeService;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
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

//    private String username;
//    private String password;
    private EmployeeDto employee;

//    private boolean isReceptionist;
//    private boolean isCashier;
//    private boolean isFloorman;
//    private boolean isManager;
    private boolean changePassword;

    private List<EmployeeDto> employees;

    private List<EmployeeDto> filteredEmployees;

    @Autowired
    private EmployeeService employeeService;

    private EmployeeDto[] selected;

    private String selectedSize;

    public List<EmployeeDto> getEmployees() {
//        if (employees == null) {
        employees = employeeService.getAllEmployees();
//        }
        return employees;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }

    public void addEmployee() {

//        FacesContext context = FacesContext.getCurrentInstance();
//        ResourceBundle bundle = ResourceBundle.getBundle("ispok/pres/inter/ispok", context.getViewRoot().getLocale());
//        EmployeeDto employeeDto = new EmployeeDto(username, password, "", isReceptionist, isCashier, isFloorman, isManager);
        try {
            EmployeeDto newEmployee = new EmployeeDto(employee);
            newEmployee.setId(employeeService.addEmployee(newEmployee));
            if (filteredEmployees != null) {
                filteredEmployees.add(newEmployee);
            }
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("success"), bundle.getString("employee_add_success")));
        } catch (DataIntegrityViolationException e) {
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, bundle.getString("fail"), e.toString()));
        } catch (Exception e) {
//            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_FATAL, bundle.getString("fail"), e.toString()));
        }
//        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("success"), bundle.getString("employee_add_success")));
//        FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("success"), bundle.getString("employee_add_success")));
        FacesUtil.addInfoMessage("success", "employee_add_success");

        clearEdit();
    }

    public void updateEmployee() {

//        FacesContext context = FacesContext.getCurrentInstance();
//        ResourceBundle bundle = ResourceBundle.getBundle("ispok/pres/inter/ispok", context.getViewRoot().getLocale());
        EmployeeDto employeeDto = selected[0];

        employeeDto.setReceptionist(employee.isReceptionist());
        employeeDto.setCashier(employee.isCashier());
        employeeDto.setFloorman(employee.isFloorman());
        employeeDto.setManager(employee.isManager());

        if (changePassword == true) {
            employeeDto.setPassword(employee.getPassword());
            changePassword = false;
        }
        employeeService.updateEmployee(employeeDto);
        FacesUtil.addInfoMessage("success", "employee_update_success");

//        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, bundle.getString("success"), bundle.getString("employee_update_success")));
    }

    public void clearEdit() {
        this.employee = new EmployeeDto();
        this.changePassword = false;
    }

    public void loadEmployee() {
        if (selected.length == 0) {
            FacesUtil.addMessage(new FacesMessage(FacesMessage.SEVERITY_WARN, FacesUtil.getString("warn"), FacesUtil.getString("no_item_selected")));
            RequestContext.getCurrentInstance().addCallbackParam("showDialog", false);
            return;
        }
        employee = new EmployeeDto(selected[0]);
        changePassword = false;
        employee.setPassword("");
//        username = selected[0].getUsername();
//        System.out.println("*********** CLEAR LOAD");
//        password = null;
//        isCashier = selected[0].isCashier();
//        isReceptionist = selected[0].isReceptionist();
//        isFloorman = selected[0].isFloorman();
//        isManager = selected[0].isManager();
//        changePassword = false;
        RequestContext.getCurrentInstance().addCallbackParam("showDialog", true);
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

        FacesUtil.addInfoMessage("success", "employee_delete_success");
    }

//    public boolean isIsReceptionist() {
//        return isReceptionist;
//    }
//
//    public void setIsReceptionist(boolean isReceptionist) {
//        this.isReceptionist = isReceptionist;
//    }
//
//    public boolean isIsCashier() {
//        return isCashier;
//    }
//
//    public void setIsCashier(boolean isCashier) {
//        this.isCashier = isCashier;
//    }
//
//    public boolean isIsFloorman() {
//        return isFloorman;
//    }
//
//    public void setIsFloorman(boolean isFloorman) {
//        this.isFloorman = isFloorman;
//    }
//
//    public boolean isIsManager() {
//        return isManager;
//    }
//
//    public void setIsManager(boolean isManager) {
//        this.isManager = isManager;
//    }
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
