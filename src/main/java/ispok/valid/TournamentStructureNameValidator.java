/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.valid;

import ispok.helper.FacesUtil;
import ispok.service.TournamentStructureService;
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
public class TournamentStructureNameValidator implements Validator {

    @Autowired
    TournamentStructureService tournamentStructureService;

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {

        String name = o.toString();
        name = name.trim();

        if (name.length() < 4) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, FacesUtil.getString("error"), FacesUtil.getString("tournament_structure_name_short")));
        }

        if (name.length() > 255) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, FacesUtil.getString("error"), FacesUtil.getString("tournament_structure_name_long")));
        }

        if (!tournamentStructureService.isNameAvailable(o.toString())) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, FacesUtil.getString("error"), FacesUtil.getString("tournament_structure_name_exists")));
        }
    }
}
