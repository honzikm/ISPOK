/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.valid;

import ispok.service.SeriesService;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Configurable
@FacesValidator
public class SeriesNameValidator implements Validator {

    @Autowired
    SeriesService seriesService;

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {
        ResourceBundle rb = ResourceBundle.getBundle("ispok/pres/inter/ispok", fc.getViewRoot().getLocale());

        String string = (String) o;
        string = string.trim();
        if (string.length() < 4 || string.length() > 255) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, rb.getString("series_name_invalid"), rb.getString("series_name_invalid")));
        }
        if (seriesService.nameExists(string)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, rb.getString("series_name_exist"), rb.getString("series_name_exist")));
        }
    }
}
