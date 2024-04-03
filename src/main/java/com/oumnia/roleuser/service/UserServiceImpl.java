package com.oumnia.roleuser.service;
import com.oumnia.roleuser.entities.Role;
import com.oumnia.roleuser.entities.User;
import com.oumnia.roleuser.repositories.RoleRepository;
import com.oumnia.roleuser.repositories.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service//component mais pour la couche service (Instancier au dmrrg)
@Transactional//transactions vont etre delegué à spring
@AllArgsConstructor
public class UserServiceImpl implements UserService {
    //Injection de dependences avec @Autowired
    //Injection de dependences avec  constructeur avec parametres ou bien en utilisant l'annotation @AllArgsConstructor
    private UserRepository userRepository;
    private RoleRepository roleRepository;



    @Override
    public User addNewUser(User user) {
        user.setUserId(UUID.randomUUID().toString());
        return userRepository.save(user);
    }
    @Override
    public Role addNewRole(Role role) {
        return roleRepository.save(role);
    }
    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }
    @Override
    public Role findRoleByRoleName(String roleName) {
        return roleRepository.findByRoleName(roleName);
    }
    @Override
    public void addRoleToUser(String username, String rolename) {
        User user= findUserByUserName(username);
        Role role = findRoleByRoleName(rolename);
        if(user.getRoles()!=null){
            user.getRoles().add(role);//bi-
            role.getUsers().add(user);//diretionnelle
        }
       // userRepository.save(user);transactional
    }
    @Override
    public User authenticate(String userName, String password) {
        User user=userRepository.findByUsername(userName);
        if(user==null){ throw new RuntimeException("Bad credentials");}
        if(user.getPassword().equals(password)){
            return user;}
        throw new RuntimeException("Bad credentials");
    }
}
