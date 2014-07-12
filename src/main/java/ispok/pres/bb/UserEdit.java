/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.pres.bb;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ispok.helper.FacesUtil;
import ispok.service.IUserService;

/**
 *
 * @author Jan
 */
@Component
public class UserEdit {

    private Long[] selectedUsers;

    @Autowired
    IUserService userService;

    public void deleteSelectedUsers() {

        int cnt = selectedUsers.length;
        for (int i = 0; i < cnt; i++) {
            userService.deleteUser(selectedUsers[i]);
        }
        FacesUtil.addMessage("User delete successful");
    }

    public Long[] getSelectedUsers() {
        return selectedUsers;
    }

    public void setSelectedUsers(Long[] selectedUsers) {
        this.selectedUsers = selectedUsers;
    }

    public IUserService getUserService() {
        return userService;
    }

    public void setUserService(IUserService userService) {
        this.userService = userService;
    }
}
