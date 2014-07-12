/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.valid;

import ispok.dto.EmployeeDto;
import ispok.service.IEmployeeService;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

/**
 * Custom validator example. This validator is defined using the spring bean and
 * it is referenced by the binding attribute in the h:validator.
 *
 * @author user
 */
@Component
public class UsernameValidator implements Validator {

    @Autowired
    IEmployeeService employeeService;

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {

        String string = o.toString();

        String regx = "[a-zA-Z0-9]+";

        ResourceBundle bundle = ResourceBundle.getBundle("ispok/pres/inter/ispok", fc.getViewRoot().getLocale());

        if (string.length() < 4) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username", bundle.getString("username_length_valid")));
        }

        if (!string.matches(regx)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username", bundle.getString("username_chars_valid")));
        }

        EmployeeDto employeeDto = null;

        try {
            employeeDto = employeeService.getEmployeeByUsername(string);
        } catch (EmptyResultDataAccessException erdae) {
        } catch (Exception e) {
        }

        if (employeeDto != null) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Invalid username", bundle.getString("username_exists_valid")));
        }
    }
}
