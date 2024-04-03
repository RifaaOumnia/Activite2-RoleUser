package com.oumnia.roleuser.repositories;

import com.oumnia.roleuser.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
    User findByUsername(String userName);
}
