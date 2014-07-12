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
public class TelephoneValidator implements Validator{

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        
        ResourceBundle rb = ResourceBundle.getBundle("ispok/pres/inter/ispok", fc.getViewRoot().getLocale());
        
        String telephoneNumber = o.toString();
        String regx = "^[+ .]?[01]?[01]?\\(?[2-9]\\d{1,15}\\)?[*]*$";
        
        if(!telephoneNumber.matches(regx)){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, rb.getString("telephone_invalid"), rb.getString("telephone_valid_pattern")));
        }
        
    }
    
    
    
}
