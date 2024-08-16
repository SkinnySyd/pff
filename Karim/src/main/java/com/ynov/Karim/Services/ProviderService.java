package com.ynov.Karim.Services;

import com.ynov.Karim.Dto.ProviderDto;

import java.io.IOException;
import java.util.List;

public interface ProviderService {

    List<ProviderDto> getAllProvider();
    ProviderDto getProviderById(long id);
    void createProvider(ProviderDto Provider);
    void updateProvider(Long id, ProviderDto Provider);
    void updateProvider(ProviderDto Provider);
    void deleteProvider(long id);
}
