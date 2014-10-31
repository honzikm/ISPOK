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
public class PasswordValidator implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {

        String string = o.toString();

        ResourceBundle bundle = ResourceBundle.getBundle("ispok/pres/inter/ispok", fc.getViewRoot().getLocale());

        if (string.matches("\\s")) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("password_invalid"), bundle.getString("password_valid_spaces")));
        }

        if (string.length() < 6) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("password_invalid"), bundle.getString("password_valid_short")));
        }

        if (string.length() > 40) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("password_invalid"), bundle.getString("password_valid_long")));
        }

    }
}
