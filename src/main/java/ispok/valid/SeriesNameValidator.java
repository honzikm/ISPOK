/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.valid;

import ispok.dto.SeriesDto;
import ispok.helper.FacesUtil;
import ispok.service.CommonService;
import ispok.service.SeriesService;
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

        String string = (String) o;
        string = string.trim();

        Object param = uic.getAttributes().get("seriesId");
        Long id = new Long(0);

        if (param != null) {
            id = (Long) param;
        }

        if (string.length() < 4 || string.length() > 255) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, FacesUtil.getString("series_name_invalid"), FacesUtil.getString("series_name_invalid")));
        }

        SeriesDto seriesTmp = seriesService.getByName(string);
        if (seriesTmp != null && !seriesTmp.getId().equals(id)) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, FacesUtil.getString("series_name_exist"), FacesUtil.getString("series_name_exist")));
        }
    }
}
