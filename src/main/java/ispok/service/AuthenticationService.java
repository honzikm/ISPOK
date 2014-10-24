/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import ispok.bo.Employee;
import ispok.dao.GenericDao;
import ispok.dto.EmployeeDto;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.cache.NullUserCache;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * Authentication provider
 *
 * @author Jan Mucha
 */
//Configuration in applicationContext-security.xml
public class AuthenticationService extends AbstractUserDetailsAuthenticationProvider {

    private static final Logger logger = LogManager.getLogger();
    private GenericDao genericDAO;
    private TransactionTemplate transactionTemplate;
    @Autowired
    private IEmployeeService employeeService;

    public AuthenticationService() {
        this.setUserCache(new NullUserCache());
    }

    @Override
    protected void additionalAuthenticationChecks(UserDetails ud, UsernamePasswordAuthenticationToken upat) throws AuthenticationException {
        // do nothing
    }

    /**
     * @param username
     * @param upat
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected UserDetails retrieveUser(final String username, final UsernamePasswordAuthenticationToken upat) throws AuthenticationException {
        //only public methods can be marked as transactional
        return (UserDetails) transactionTemplate.execute(new TransactionCallback() {

            @Override
            public Object doInTransaction(TransactionStatus status) {
                try {
                    UserDetails ud = null;

                    Employee employee = null;
                    EmployeeDto employeeDto = null;
                    ispok.bo.User user = null;
                    try {
                        employee = genericDAO.getByPropertyUnique("username", username, Employee.class);

                        employeeDto = employeeService.getEmployeeByUsername(username);

                    } catch (EmptyResultDataAccessException erdaex) {
                    }
//                    try {
//                        user = genericDAO.getByPropertyUnique("username", username, ispok.bo.User.class);
//                    } catch (EmptyResultDataAccessException e) {
//                    }

                    if (employee == null) {
                        throw new BadCredentialsException("Uzivatel neexistuje");
                    }

                    String password = (String) upat.getCredentials();
                    List<GrantedAuthority> auths = new ArrayList<GrantedAuthority>();

                    if (user != null && user.hasPassword(password)) {
                        auths.add(new SimpleGrantedAuthority("ROLE_USER"));

                        ud = new User(user.getUsername(), user.getPassword(), auths);
                    } else if (employee != null && employee.hasPassword(password)) {
                        if (employee.isManager() == true) {
                            auths.add(new SimpleGrantedAuthority("ROLE_MANAGER"));
                        }
                        if (employee.isCashier() == true) {
                            auths.add(new SimpleGrantedAuthority("ROLE_CASHIER"));
                        }
                        if (employee.isReceptionist() == true) {
                            auths.add(new SimpleGrantedAuthority("ROLE_RECEPTIONIST"));
                        }
                        if (employee.isFloorman() == true) {
                            auths.add(new SimpleGrantedAuthority("ROLE_FLOORMAN"));
                        }
                        ud = new User(employee.getUsername(), employee.getPassword(), auths);
                    } else {
                        AuthenticationException e = new BadCredentialsException("Neplatne heslo");
                        throw e;
                    }

                    return ud;
                } catch (AuthenticationException e) {
                    status.setRollbackOnly();
                    throw e;
                } catch (Exception e) {
                    logger.error("Error occured during retrieve User call", e);
                    status.setRollbackOnly();
                    throw new RuntimeException(e);
                }

            }
        });
    }

    public void setGenericDAO(GenericDao genericDAO) {
        this.genericDAO = genericDAO;
    }

    public void setTransactionTemplate(TransactionTemplate transactionTemplate) {
        this.transactionTemplate = transactionTemplate;
    }
}
