package com.example.async.models;

import javax.persistence.*;

@Entity
@Table(name = "TB_USERS")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(name = "id")
    private Long id;
    @Column(name = "user_name")
    private String name;
    @Column(name = "user_email")
    private String email;

    public User(){

    }

    public User(String name, String email, Long id) {
        this.name = name;
        this.email = email;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
