package com.ynov.Karim.Controllers;

import com.ynov.Karim.Dto.ProviderDto;
import com.ynov.Karim.Dto.ServerDto;
import com.ynov.Karim.Entities.Provider;
import com.ynov.Karim.Entities.Server;
import com.ynov.Karim.Repositories.ProviderRepository;
import com.ynov.Karim.Services.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@RestController
public class ProviderController {
    @Autowired
    ProviderRepository providerRepository;
    @Autowired
    ProviderService providerService;


    @GetMapping("/providers/all")
    public ResponseEntity<  List<Provider>   > getAllProviders(){
        return ResponseEntity.ok(providerRepository.findAll());
    }


    @PostMapping("/providers/name")
    public ResponseEntity<Provider>   getProvider(@RequestBody ProviderDto providerArg){
        String str = providerArg.getName();
        Provider provider = providerRepository.findByName(str);
        return ResponseEntity.ok(provider);
    }
    @PostMapping("/providers/create")
    public ResponseEntity<Server> createProvider(@RequestBody ProviderDto provider){
        providerRepository.save(new Provider(provider.getName()));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PutMapping("/providers/update/{id}")
    public ResponseEntity<?> updateProvider(@RequestBody ProviderDto provider,@PathVariable Long id){
        Optional<Provider> nwProvider = providerRepository.findById(id);
        nwProvider.get().setName(provider.getName());
        providerRepository.save(nwProvider.get());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/providers/delete/{id}")
    public ResponseEntity<?> deleteProviderById(@PathVariable long id){
        providerService.deleteProvider(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
//        List<Provider> providers = providerRepository.findAll();
