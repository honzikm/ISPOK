/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.pres.bb;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

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

    public String logout() {
        System.out.println("********************** LOGOUT");
        SecurityContextHolder.clearContext();

        return "/index";
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
