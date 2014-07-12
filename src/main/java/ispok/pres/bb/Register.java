/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.pres.bb;

import java.util.List;
import javax.persistence.NoResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;
import ispok.dto.UserDto;
import ispok.helper.FacesUtil;
import ispok.service.IUserService;

/**
 *
 * @author Jan
 */
@Component
public class Register {

    private String username;
    private String email;
    private String password;

    @Autowired
    IUserService userService;

    public String save() {
        try {
            userService.addUser(username, email, password);
        } catch (DataIntegrityViolationException e) {
            FacesUtil.addMessage("User not registered");
            List<UserDto> users = userService.getUserByUsername(username);
            if (!users.isEmpty()) {
                FacesUtil.addMessage("Username already exist");
            }
            users = userService.getUserByEmail(email);
            if (!users.isEmpty()) {
                FacesUtil.addMessage("Email already exist");
            }
            return "register.xhtml";
        }

        FacesUtil.addMessage("User was sucessfully registered");

        return "index.xhtml";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
