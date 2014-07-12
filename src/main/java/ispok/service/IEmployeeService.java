/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.dto.EmployeeDto;
import java.util.List;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Jan
 */
@Transactional
public interface IEmployeeService {

    @Transactional(readOnly = true)
    public EmployeeDto getEmployeeByUsername(String username);

    @Transactional(readOnly = true)
    public List<EmployeeDto> getAllEmployees();

//    @Transactional
//    public Long addEmployee(String username, String password, boolean receptionist, boolean cashier, boolean floorman, boolean manager);

    @Transactional
    public Long addEmployee(EmployeeDto employeeDto);
    
    @Transactional
    public void updateEmployee(EmployeeDto employeeDto);

    @Transactional
    public void deleteEmployee(Long id);
}
