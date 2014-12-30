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

    public static final int INVALID_USERNAME = 1;
    public static final int INVALID_PASSWORD = 2;

    private String loginVar;
    private String username;
    private String password;
    private int loginFailedReason;

    /**
     *
     * @return
     */
    public int getLoginFailedReason() {
        return loginFailedReason;
    }

    /**
     *
     * @param loginFailedReason
     */
    public void setLoginFailedReason(int loginFailedReason) {
        this.loginFailedReason = loginFailedReason;
    }

//    @Autowired
//    AuthenticationManager authenticationManager;
    /**
     * Get the value of password
     *
     * @return the value of password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Set the value of password
     *
     * @param password new value of password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Get the value of username
     *
     * @return the value of username
     */
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLoginVar() {
        return loginVar;
    }

    public void setLoginVar(String loginVar) {
        this.loginVar = loginVar;
    }

    public void setAdminLogin() {
        loginVar = "admin";
    }

    public void setVisitorLogin() {
        loginVar = "visitor";
    }

//    public String login() throws ServletException, IOException {
//        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
//        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("static/j_spring_security_check");
//        dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
//        FacesContext.getCurrentInstance().responseComplete();
//        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
//        HttpServletRequest request = ((HttpServletRequest) context.getRequest());
//        ServletResponse response = ((ServletResponse) context.getResponse());
//        RequestDispatcher dispatcher = request.getRequestDispatcher("/static/j_spring_security_check");
//        dispatcher.forward(request, response);
//        FacesContext.getCurrentInstance().responseComplete();
//        return "index.xhtml";
//
//                    Authentication request = new UsernamePasswordAuthenticationToken(this.getUserName(), this.getPassword());
//            Authentication result = authenticationManager.authenticate(request);
//            SecurityContextHolder.getContext().setAuthentication(result);
//        } catch (AuthenticationException e) {
//            e.printStackTrace();
//            return "incorrect";
//        }
//        return "correct";
//        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
//        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("static/j_spring_security_check?j_username=" + username + "&j_password=" + password);
//        RequestDispatcher dispatcher = ((ServletRequest) context.getRequest()).getRequestDispatcher("index.xhtml");
//        dispatcher.forward((ServletRequest) context.getRequest(), (ServletResponse) context.getResponse());
//        FacesContext.getCurrentInstance().responseComplete();
//        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
//        context.redirect("static/j_spring_security_check?j_username=" + username + "&j_password=" + password);
//        return "static/j_spring_security_check?j_username=" + username + "&j_password=" + password;
//        return null;
//    }
}
