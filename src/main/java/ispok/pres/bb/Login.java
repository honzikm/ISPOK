/* To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.pres.bb;

import org.springframework.stereotype.Component;

/**
 *
 * @author Jan Mucha <j.mucha@seznam.cz>
 */
@Component
public class Login {

    private String loginVar;

    public String getLoginVar() {
        return loginVar;
    }

    public void setLoginVar(String loginVar) {
        this.loginVar = loginVar;
    }
    
    public void setAdminLogin(){
        loginVar = "admin";
    }

}
