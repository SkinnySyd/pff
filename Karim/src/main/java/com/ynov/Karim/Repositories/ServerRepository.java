package com.ynov.Karim.Repositories;

import com.ynov.Karim.Entities.Provider;
import com.ynov.Karim.Entities.Server;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServerRepository extends JpaRepository<Server, Long> {
    Server findByName(String str);
}