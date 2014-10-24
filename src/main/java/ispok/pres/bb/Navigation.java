/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.pres.bb;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component
public class Navigation {

    /**
     * Creates a new instance of Common
     */
    public Navigation() {
    }

    /**
     *
     * @return
     */
    public String goAdminEmployees() {
        return "/admin/management/employees.xhtml";
    }

    public String goAdminOffices() {
        return "/admin/mangement/employees.xhtml";
    }

    public void logout() {
        System.out.println("********************** LOGOUT");
//        SecurityContextHolder.clearContext();
        ServletRequestAttributes sra = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest req = sra.getRequest();
        try {
            req.logout();
        } catch (ServletException ex) {
            Logger.getLogger(Navigation.class.getName()).log(Level.SEVERE, null, ex);
        }
//        SecurityContextHolder.getContext().getAuthentication().setAuthenticated(false);
//        SecurityContextHolder.createEmptyContext();
//        return "/ispok/index.xhtml";
    }

    public String goVisitors() {
        return "/admin/management/visitors/visitors.xhtml";
    }

    public String goEditVisitor() {
        return "/admin/management/visitors/edit.xhtml";
    }

    public String goNewVisitor() {
        return "/admin/management/visitors/newvisitor.xhtml";
    }

}
