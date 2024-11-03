package com.scm.entity;

import java.util.*;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

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
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;


@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User1 implements UserDetails {

    @Id
    private String userId;

    @Column(name = "User_Name")
    private String name;

    @Getter(value = AccessLevel.NONE)
    private String password;

    @Column(length = 1000)
    private String about;

    @Column(unique = true, nullable = false)
    private String email;
    private String phoneNumber;

    @Column(length = 1000)
    private String profilePic = "";
    private boolean enabled = true;
    private boolean emailVerified = false;
    private boolean phoneVerified = false;
    private Provider provider = Provider.SELF;
    private String providerUserId;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
       private List<Contact> contacts = new ArrayList<>();



       List<String> rolelList = new ArrayList<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
    //List of roles [admin, user,]
    //collection of simpleGrantedAuthority[roles{ADMIN, USER}]
        Collection<SimpleGrantedAuthority> roles = rolelList.stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList());
       
       return roles;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public String getPassword() {
        return this.password;
    }
 

}
