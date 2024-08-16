package com.ynov.Karim.Dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ProviderDto {

    private long id;

    private String name;

    public ProviderDto() {
    }

    public ProviderDto(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public ProviderDto(String name) {
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
}
