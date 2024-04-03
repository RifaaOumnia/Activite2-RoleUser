package com.oumnia.roleuser;
import com.oumnia.roleuser.entities.Role;
import com.oumnia.roleuser.entities.User;
import com.oumnia.roleuser.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.SQLOutput;
import java.util.stream.Stream;
@SpringBootApplication
public class RoleUserApplication {
    public static void main(String[] args) {
        SpringApplication.run(RoleUserApplication.class, args);}



    @Bean
    CommandLineRunner start(UserService userService){
        return args -> {
            //AJOUTER USER
            User u = new User();
            u.setUsername("user1");
            u.setPassword("123456");
            userService.addNewUser(u);
            ////////////////////////////
            User u2 = new User();
            u2.setUsername("admin");
            u2.setPassword("123456");
            userService.addNewUser(u2);

            //AJOUTER ROLES
            Stream.of("STUDENT","USER","ADMIN").forEach(r->{
            Role role1= new Role();
            role1.setRoleName(r);
            userService.addNewRole(role1);
        });

        //DONNER ROLE A UN USSER
        userService.addRoleToUser("user1","STUDENT");
        userService.addRoleToUser("user1","USER");
        userService.addRoleToUser("admin","ADMIN");
        userService.addRoleToUser("admin","USER");

       //AUTHENTIFICATION
        try{
            User user=userService.authenticate("user1","123456");
            System.out.println(user.getUserId());
            System.out.println(user.getUsername());
            System.out.println("Role=>");
            user.getRoles().forEach(r->{
                System.out.println("Role"+r);});}
        catch(Exception exception){
            exception.printStackTrace();}};
    }
}
