package com.scm.entity;

import java.util.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User1 {

    @Id
    private String userId;

    @Column(name = "User_Name")
    private String name;

   
    private String password;

    @Column(length = 1000)
    private String about;

    @Column(unique = true, nullable = false)
    private String email;
    private String phoneNumber;

    @Column(length = 1000)
    private String profilePic;
    private boolean enabled=false;
    private boolean emailVerified=false;
    private boolean phoneVerified=false;

    private Provider provider=Provider.SELF;
    private String providerUserId;


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Contact> contacts = new ArrayList<>();


 

}
