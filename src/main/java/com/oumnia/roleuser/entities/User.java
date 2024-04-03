package com.oumnia.roleuser.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
@Table(name="USERS")
@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class User {
    @Id
    private String userId;
    @Column(name="USER_NAME",unique = true,length = 20)
    private String username;
    private String password;
    @ManyToMany(mappedBy = "users",fetch =FetchType.EAGER)//Dans la classe role y a un attribut qui s'appel users
    private List<Role> roles=new ArrayList<>();

}
