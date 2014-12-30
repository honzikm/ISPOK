/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.converter;

import ispok.helper.FacesUtil;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@FacesConverter(value = "positiveFloatConverter")
public class PositiveFloatConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        
        Float f = null;
        try {
            f = new Float(string);
        } catch (NumberFormatException e) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", FacesUtil.getString("float_invalid"));
            throw new ConverterException(msg);
        }
        if (f < 0) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "", FacesUtil.getString("float_positive_invalid"));
            throw new ConverterException(msg);
        }
        return f;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        Float f = (Float) o;

        return f.toString();
    }
}
