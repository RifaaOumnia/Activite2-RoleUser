package com.oumnia.roleuser.service;

import com.oumnia.roleuser.entities.User;
import com.oumnia.roleuser.entities.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface UserService {
    User addNewUser(User user);
    Role addNewRole(Role role);
    User findUserByUserName(String userName);
    Role findRoleByRoleName(String roleName);
    void addRoleToUser(String username, String rolename);
    User authenticate(String userName,String password);



}
