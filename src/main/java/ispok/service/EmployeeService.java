/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.bo.Employee;
import ispok.dto.EmployeeDto;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan
 */
@Component
public class EmployeeService extends AbstractDataAccessService implements IEmployeeService {

    @Override
    public EmployeeDto getEmployeeByUsername(String username) {
        Employee e = genericDao.getByPropertyUnique("username", username, Employee.class);
        EmployeeDto ed = new EmployeeDto(e.getId(), e.getUsername(), e.getPassword(), e.isReceptionist(), e.isCashier(), e.isFloorman(), e.isManager());
        return ed;
    }

    @Override
    public Long addEmployee(EmployeeDto employeeDto) {
        Employee e = new Employee(employeeDto);
        return genericDao.saveOrUpdate(e).getId();
    }

    @Override
    public List<EmployeeDto> getAllEmployees() {
        List<Employee> employees = genericDao.getAll(Employee.class);
        List<EmployeeDto> employeeDtos = new ArrayList<>(employees.size());

        for (Employee e : employees) {
            employeeDtos.add(new EmployeeDto(e));
        }
        return employeeDtos;
    }

    @Override
    public void deleteEmployee(Long id) {
        genericDao.removeById(id, Employee.class);
    }

    @Override
    public void updateEmployee(EmployeeDto employeeDto) {
        Employee e = genericDao.getById(employeeDto.getId(), Employee.class);
        String password = employeeDto.getPassword();
        if (password != e.getPassword()) {
            e.setPassword(password);
            employeeDto.setPassword(e.getPassword());
        }

        e.setIsReceptionist(employeeDto.isReceptionist());
        e.setIsCashier(employeeDto.isCashier());
        e.setIsReceptionist(employeeDto.isFloorman());
        e.setIsManager(employeeDto.isManager());

        genericDao.saveOrUpdate(e);
    }
}
