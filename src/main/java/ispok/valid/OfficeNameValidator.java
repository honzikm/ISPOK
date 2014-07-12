/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
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
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component
public class OfficeNameValidator implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {

        ResourceBundle bundle = ResourceBundle.getBundle("ispok/pres/inter/ispok", fc.getViewRoot().getLocale());
        String string = o.toString();
       

        System.out.println("********************** " + string + " " + string.length());

        if (string.length() < 4 || string.length() > 255) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, bundle.getString("office_name_invalid"), bundle.getString("office_name_valid_length")));

        }
    }
}
