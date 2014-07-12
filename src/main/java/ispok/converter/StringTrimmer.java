/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */

@Component
public class StringTrimmer implements Converter {

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {
        return string.trim();
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {
        return (String) o;
    }

}
