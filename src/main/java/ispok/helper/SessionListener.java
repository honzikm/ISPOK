/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.helper;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@WebListener
public class SessionListener implements HttpSessionListener {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public void sessionCreated(HttpSessionEvent hse) {

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent hse) {

        logger.entry();
        logger.debug("Session destroyed: {}", hse.getSession().toString());

//        Enumeration<String> attributeNames = hse.getSession().getAttributeNames();
//        logger.debug("Attributes: ");
//        while (attributeNames.hasMoreElements()) {
//            logger.debug(attributeNames.nextElement());
//        }
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null) {
            logger.debug("Username: {}", authentication.getName());
        }

        logger.exit();
    }
}
