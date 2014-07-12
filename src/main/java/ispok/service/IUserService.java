/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ispok.service;

import java.util.List;
import org.springframework.transaction.annotation.Transactional;
import ispok.dto.UserDto;

/**
 *
 * @author Jan
 */
@Transactional
public interface IUserService {

    @Transactional
    public Long addUser(String username, String email, String password);

    @Transactional (readOnly = true)
    public List<UserDto> getUserByUsername(String username);

    @Transactional (readOnly = true)
    public List<UserDto> getUserByEmail(String email);
    
    @Transactional (readOnly = true)
    public List<UserDto> getAllUsers();

    @Transactional
    public void deleteUser(Long userId);
}
