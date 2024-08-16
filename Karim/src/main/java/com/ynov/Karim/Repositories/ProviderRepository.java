package com.ynov.Karim.Repositories;

import com.ynov.Karim.Entities.Provider;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProviderRepository extends JpaRepository<Provider, Long> {
    public Provider findByName(String name);
}