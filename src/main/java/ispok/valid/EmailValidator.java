/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.valid;

import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * Custom validator example. This validator is defined using the spring bean and
 * it is referenced by the binding attribute in the h:validator.
 *
 * @author user
 */
@FacesValidator
public class EmailValidator implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {

        ResourceBundle rb = ResourceBundle.getBundle("ispok/pres/inter/ispok", fc.getViewRoot().getLocale());

        String email = o.toString();

        String regx = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        if (email.length() == 0) {
            return;
        }

        if (!email.matches(regx)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, rb.getString("email_invalid"), rb.getString("email_invalid")));

        }
       
    }
}
