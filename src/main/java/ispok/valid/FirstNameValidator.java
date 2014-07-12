/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.valid;

import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.springframework.stereotype.Component;

/**
 * Custom validator example. This validator is defined using the spring bean and
 * it is referenced by the binding attribute in the h:validator.
 *
 * @author user
 */
@Component
public class FirstNameValidator implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {

        ResourceBundle rb = ResourceBundle.getBundle("ispok/pres/inter/ispok", fc.getViewRoot().getLocale());

        String string = o.toString();

        String regx = "[A-Z][ a-zA-Z]*";

        if (string.length() > 80) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, rb.getString("firstname_invalid"), rb.getString("firstname_valid_length")));
        }

        if (!string.matches(regx)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, rb.getString("firstname_invalid"), rb.getString("firstname_valid_pattern")));
        }
        
    }
}
