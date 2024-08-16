package com.ynov.Karim.Dto;

import com.ynov.Karim.Entities.Provider;

public class ServerDto {


    private long id;
    private String name;

    private String ipAdd;
    private String login;
    private String password;

    private ProviderDto provider;

    public ServerDto() {
    }

    public ServerDto(String name) {
        this.name = name;
    }

    public ServerDto(String name, String ipAdd, String login, String password, ProviderDto provider) {
        this.name = name;
        this.ipAdd = ipAdd;
        this.login = login;
        this.password = password;
        this.provider = provider;
    }

    public ServerDto(long id, String name, String ipAdd, String login, String password, ProviderDto provider) {
        this.id = id;
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

    public ProviderDto getProvider() {
        return provider;
    }

    public void setProvider(ProviderDto provider) {
        this.provider = provider;
    }
}
