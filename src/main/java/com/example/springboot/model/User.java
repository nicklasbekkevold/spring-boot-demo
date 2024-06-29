package com.example.springboot.model;

import jakarta.persistence.*;

@Entity
@SequenceGenerator(name="user_id_generator", allocationSize=100)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_generator")
    private int id;

    private int version;
    private String name;

    private User() {
        this.version = 1;
    }

    public User(int version, String name) {
        this.version = version;
        this.name = name;
    }

    public User(String name) {
        this.version = 1;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "User{id=%d, version=%d, name='%s'}".formatted(id, version, name);
    }
}