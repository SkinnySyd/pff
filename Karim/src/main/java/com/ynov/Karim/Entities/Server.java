package com.ynov.Karim.Entities;

import jakarta.persistence.*;

import java.util.Optional;

@Entity
public class Server {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;

    @Column(name = "server_ip")
    private String ipAdd;
    @Column(name = "server_login")
    private String login;
    @Column(name = "server_password")
    private String password;

    @ManyToOne
    @JoinColumn(name="provider_id",nullable = false)
    private Provider provider;


    public Server() {
    }

    public Server(String name, String ipAdd, String login, String password) {
        this.name = name;
        this.ipAdd = ipAdd;
        this.login = login;
        this.password = password;
    }

    public Server(String name, String ipAdd, String login, String password, Provider provider) {
        this.name = name;
        this.ipAdd = ipAdd;
        this.login = login;
        this.password = password;
        this.provider = provider;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpAdd() {
        return ipAdd;
    }

    public void setIpAdd(String ipAdd) {
        this.ipAdd = ipAdd;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }

    @Override
    public String toString() {
        return "Server{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", ipAdd='" + ipAdd + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
