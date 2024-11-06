package com.scm.entity;





import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "Contact")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Contact {

    @Id
    
    @Column(name = "id")

    private String id;

    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private String profile;

    @Column(length = 1000)
    private String description;
    private boolean favorite=false;
    

    @ManyToOne
    private User1 user;


    public void setDeleted(boolean b) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setDeleted'");
    }





}
