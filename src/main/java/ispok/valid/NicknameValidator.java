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
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */

@FacesValidator
public class NicknameValidator implements Validator {

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {

        ResourceBundle rb = ResourceBundle.getBundle("ispok/pres/inter/ispok", fc.getViewRoot().getLocale());

        String nickname = o.toString();
        
        String regx = "[a-zA-Z0-9]+";
        
        int nicknameLength = nickname.length();
        
        if(nicknameLength == 0){
            return;
        }
        
        if(nicknameLength < 4){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, rb.getString("nickname_invalid"), rb.getString("nickname_valid_short")));
        }
        
        if(nicknameLength > 30){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, rb.getString("nickname_invalid"), rb.getString("nickname_valid_long")));
        }
        
        if(!nickname.matches(regx)){
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, rb.getString("nickname_invalid"), rb.getString("nickname_valid_regx")));
        }
        
    }

}
