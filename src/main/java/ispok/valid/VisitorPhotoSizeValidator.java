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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.primefaces.model.NativeUploadedFile;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@FacesValidator
public class VisitorPhotoSizeValidator implements Validator {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void validate(FacesContext fc, UIComponent uic, Object o) throws ValidatorException {

        logger.debug("Validating file: " + o);

        ResourceBundle rb = ResourceBundle.getBundle("ispok/pres/inter/ispok", fc.getViewRoot().getLocale());

        NativeUploadedFile nuf = (NativeUploadedFile) o;

        long fileSize = nuf.getSize();
        String fileContentType = nuf.getContentType();
        String fileName = nuf.getFileName();

        logger.debug("File size: " + fileSize + " , File content type: " + fileContentType + ", File name: " + fileName);

        if(fileSize == 0){
            return;
        }
        
        if (!fileContentType.contains("image")) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, rb.getString("photo_invalid"), rb.getString("photo_valid_type")));
        }
        if (fileSize >= 1000000) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, rb.getString("photo_invalid"), rb.getString("photo_valid_size")));
        }
    }
}
