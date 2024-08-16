package com.ynov.Karim.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Provider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "provider_id")
    private long id;

    @Column(name = "provider_name")
    private String name;

    public Provider() {
    }

    public Provider(String name) {
        this.name = name;
    }

    public Provider(long id, String name) {
        this.id = id;
        this.name = name;
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

//    public Set<Server> getServers() {
//        return servers;
//    }
//
//    public void setServers(Set<Server> servers) {
//        this.servers = servers;
//    }
}
