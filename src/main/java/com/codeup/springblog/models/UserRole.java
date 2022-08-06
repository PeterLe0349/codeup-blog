package com.codeup.springblog.models;

import javax.persistence.*;

@Entity
@Table(name = "user_roles")
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "role")
    private String role;

    public UserRole() {
    }

    public UserRole(long userId, String role) {
        this.userId = userId;
        this.role = role;
    }

}
