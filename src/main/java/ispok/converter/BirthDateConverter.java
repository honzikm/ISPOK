/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.weaver.patterns.ISignaturePattern;
import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component
public class BirthDateConverter implements Converter {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String string) {

        logger.trace("Entering getAsObject()");

        Locale locale = fc.getViewRoot().getLocale();

        ResourceBundle rb = ResourceBundle.getBundle("ispok/pres/inter/ispok", locale);

        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, locale);
        SimpleDateFormat sdf = (SimpleDateFormat) df;

        String pattern = sdf.toPattern();
        String localPattern = sdf.toLocalizedPattern();

        logger.debug("pattern: {}", pattern);
        logger.debug("localized pattern: {}", localPattern);

        Date date;

        try {
            date = new SimpleDateFormat(pattern).parse(string);
        } catch (ParseException ex) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "invalid date", pattern);
            throw new ConverterException(msg);
        }

        if (date.after(new Date())) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, rb.getString("birthdate_invalid"), rb.getString("birthdate_valid_future"));
            throw new ConverterException(msg);
        }

        Calendar c = Calendar.getInstance();
        c.set(1850, 1, 1);

        if (date.before(c.getTime())) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, rb.getString("birthdate_invalid"), rb.getString("birthdate_valid_past"));
            throw new ConverterException(msg);
        }

        return date;
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object o) {

        Locale locale = fc.getViewRoot().getLocale();
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, locale);

        SimpleDateFormat sdf = (SimpleDateFormat) df;
        String pattern = sdf.toPattern();

        pattern = pattern.replace("yy", "yyyy");

        Date date = (Date) o;

        sdf.applyPattern(pattern);

        return sdf.format(date);
    }
}
