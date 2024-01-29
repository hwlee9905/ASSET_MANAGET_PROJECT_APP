package org.example.domain.manager.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Manager {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String loginid;
    private String password;
    private String name;
    private String email;


    public Manager(String loginid, String password, String name, String email) {
        this.loginid = loginid;
        this.password = password;
        this.name = name;
        this.email = email;
    }

    public Manager() {

    }
}
