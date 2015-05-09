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
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.SessionScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component
@SessionScoped
public class EmployeeEdit implements Serializable {

    private EmployeeDto employee = new EmployeeDto();
    private boolean changePassword;
    private boolean editDisabled = true;
    private boolean editRolesDisabled = true;
    private List<EmployeeDto> employees;
    private List<EmployeeDto> filteredEmployees;

    @Autowired
    private EmployeeService employeeService;

    private EmployeeDto[] selected;
    private String selectedSize;

    @PostConstruct
    void init() {
        employees = employeeService.getAllEmployees();
    }

    public boolean isEditDisabled() {
        return editDisabled;
    }

    public boolean isEditRolesDisabled() {
        return editRolesDisabled;
    }

    public List<EmployeeDto> getEmployees() {
//        employees = employeeService.getAllEmployees();
        return employees;
    }

    public EmployeeDto getEmployee() {
        return employee;
    }

    public void setEmployee(EmployeeDto employee) {
        this.employee = employee;
    }

    public void addEmployee() {
        try {
            EmployeeDto newEmployee = new EmployeeDto(employee);
            newEmployee.setId(employeeService.addEmployee(newEmployee));
            if (filteredEmployees != null) {
                filteredEmployees.add(newEmployee);

            }
            employees.add(newEmployee);
        } catch (DataIntegrityViolationException e) {
        } catch (Exception e) {
        }
        FacesUtil.addInfoMessage("success", "employee_add_success");
        clearEdit();
    }

    public void updateEmployee() {
        EmployeeDto employeeDto = selected[0];
        employeeDto.setReceptionist(employee.isReceptionist());
        employeeDto.setCashier(employee.isCashier());
        employeeDto.setFloorman(employee.isFloorman());
        employeeDto.setManager(employee.isManager());

        if (employee.getPassword() == null || employee.getPassword().isEmpty()) {
        } else {
            employeeDto.setPassword(employee.getPassword());
            changePassword = false;
        }
        employeeService.updateEmployee(employeeDto);
        FacesUtil.addInfoMessage("success", "employee_update_success");
    }

    public void clearEdit() {
        this.employee = new EmployeeDto();
        employee.setUsername("");
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

    public List<EmployeeDto> getFilteredEmployees() {
        return filteredEmployees;
    }

    public void setFilteredEmployees(List<EmployeeDto> filteredEmployees) {
        this.filteredEmployees = filteredEmployees;
    }

    public EmployeeDto[] getSelected() {
        return selected;
    }

    public void setSelected(EmployeeDto[] selected) {
        this.selected = selected;
    }

    public void onRowSelect(SelectEvent event) {

        boolean tmp = true;
        editRolesDisabled = true;

        Authentication a = SecurityContextHolder.getContext().getAuthentication();
        for (GrantedAuthority ga : a.getAuthorities()) {
            if (ga.getAuthority().equals("ROLE_MANAGER")) {
                tmp = false;
                editRolesDisabled = false;
                break;
            }
        }
        if (tmp && selected != null && selected.length > 0 && selected[0].getUsername().equals(a.getName())) {
            tmp = false;
        }

        if (editDisabled != tmp) {
            editDisabled = tmp;
            RequestContext.getCurrentInstance().update("dataForm:employeesTableId:editButton");
            RequestContext.getCurrentInstance().update("dataForm:contextMenu");
            RequestContext.getCurrentInstance().update("newEmployeeDialogId");
        }
    }

    public boolean isChangePassword() {
        return changePassword;
    }

    public void setChangePassword(boolean changePassword) {
        this.changePassword = changePassword;
    }

}
