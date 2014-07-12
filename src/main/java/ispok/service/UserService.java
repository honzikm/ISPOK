/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import ispok.bo.User;
import ispok.dto.UserDto;

/**
 *
 * @author Jan
 */
@Component
public class UserService extends AbstractDataAccessService implements IUserService {

    @Override
    public Long addUser(String username, String email, String password) {
        User user = new User(username, email);
        user.setPassword(password);
        return genericDao.saveOrUpdate(user).getId();
    }

    @Override
    public List<UserDto> getUserByUsername(String username) {
        List<User> user;
        List<UserDto> userDtos = new ArrayList<UserDto>();

        user = genericDao.getByProperty("username", username, User.class);

        for (User u : user) {
            userDtos.add(getUserDto(u));
        }
        return userDtos;
    }

    @Override
    public List<UserDto> getUserByEmail(String email) {
        List<User> user;
        List<UserDto> userDtos = new ArrayList<UserDto>();

        user = genericDao.getByProperty("email", email, User.class);

        for (User u : user) {
            userDtos.add(getUserDto(u));
        }
        return userDtos;
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = genericDao.getAll(User.class);
        List<UserDto> userDtos = new ArrayList<UserDto>(users.size());

        for (User u : users) {
            userDtos.add(getUserDto(u));
        }
        return userDtos;
    }

    private UserDto getUserDto(User u) {
        return new UserDto(u.getId(), u.getUsername(), u.getEmail());
    }

    @Override
    public void deleteUser(Long userId) {
        genericDao.removeById(userId, User.class);
    }
}
