package com.ynov.Karim.Services.ServicesImpl;

import com.ynov.Karim.Dto.ProviderDto;
import com.ynov.Karim.Entities.Provider;
import com.ynov.Karim.Exceptions.ResourceNotFoundException;
import com.ynov.Karim.Repositories.ProviderRepository;
import com.ynov.Karim.Services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class ProviderServiceImpl implements ProviderService {

    @Autowired
    ProviderRepository providerRepository;
    @Override
    public List<ProviderDto> getAllProvider() {
        List<Provider> listProviders = providerRepository.findAll();
        return listProviders
                .stream()
                .map(this::convertToProviderDto)
                .collect(Collectors.toList());
    }

    @Override
    public ProviderDto getProviderById(long id) {
        return providerRepository.findById(id).map(this::convertToProviderDto)
                .orElseThrow(() -> new ResourceNotFoundException("Server not found with id " + id));
    }

    @Override
    public void createProvider(ProviderDto provider) {
        providerRepository.save(new Provider(provider.getName()));

    }

    @Override
    public void updateProvider(Long id, ProviderDto provider) {
        Provider newProvider = providerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Provider not found with id " + id));
        newProvider.setName(provider.getName());
        providerRepository.save(newProvider);
    }

    @Override
    public void updateProvider(ProviderDto provider) {
        providerRepository.save(new Provider(provider.getName()));

    }

    @Override
    public void deleteProvider(long id) {
        providerRepository.delete(providerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Provider not found with id " + id)));
    }

    public ProviderDto convertToProviderDto(Provider provider){
        ProviderDto providerDetails = new ProviderDto();
        providerDetails.setId(provider.getId());
        providerDetails.setName(provider.getName());
        return providerDetails;
    }
}
