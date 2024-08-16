package com.ynov.Karim.Dto;

import com.ynov.Karim.Entities.Server;
import jakarta.persistence.*;

@Entity
public class ServerKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String keyPath;
    @ManyToOne
    @JoinColumn(name = "server_id")
    private Server server ;

    public ServerKey() {
    }

    public ServerKey(String keyPath, Server server) {
        this.keyPath = keyPath;
        this.server = server;
    }

    public ServerKey(long id, String keyPath, Server server) {
        this.id = id;
        this.keyPath = keyPath;
        this.server = server;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getKeyPath() {
        return keyPath;
    }

    public void setKeyPath(String keyPath) {
        this.keyPath = keyPath;
    }

    public Server getServer() {
        return server;
    }

    public void setServer(Server server) {
        this.server = server;
    }
}
