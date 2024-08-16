package com.ynov.Karim.Services.ServicesImpl;

import com.ynov.Karim.Dto.ProviderDto;
import com.ynov.Karim.Dto.ServerDto;
import com.ynov.Karim.Dto.ServerKey;
import com.ynov.Karim.Entities.Provider;
import com.ynov.Karim.Entities.Server;
import com.ynov.Karim.Exceptions.ResourceNotFoundException;
import com.ynov.Karim.Repositories.ProviderRepository;
import com.ynov.Karim.Repositories.ServerKeyRepository;
import com.ynov.Karim.Repositories.ServerRepository;
import com.ynov.Karim.Services.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;


@Service
public class ServerServiceImpl implements ServerService {
    @Autowired
    ServerRepository serverRepository;
    @Autowired
    ProviderRepository providerRepository;
    @Autowired
    private ServerKeyRepository serverKeyRepository;

    @Override
    public List<ServerDto> getAllServer() {
        List<Server> listServer = serverRepository.findAll();
        return listServer
                .stream()
                .map(this::convertToServerDto)
                .collect(Collectors.toList());
    }

    @Override
    public ServerDto  getServerById(long id) {
            return serverRepository.findById(id).map(this::convertToServerDto)
                .orElseThrow(() -> new ResourceNotFoundException("Server not found with id " + id));
    }

    @Override
    public void createServer(ServerDto server) {
        serverRepository.save(new Server(server.getName(),server.getIpAdd(),server.getLogin(), server.getPassword(), providerRepository.findByName(server.getProvider().getName())));
    }

    @Override
    public void updateServer(Long id, ServerDto server) {
        Server newServer = serverRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Server not found with id " + id));
        newServer.setName(server.getName());
        newServer.setIpAdd(server.getIpAdd());
        newServer.setLogin(server.getLogin());
        newServer.setPassword(server.getPassword());
        newServer.setProvider(providerRepository.findByName(server.getName()));
        serverRepository.save(newServer);
    }

    @Override
    public void updateServer(ServerDto server) {
        serverRepository.save(new Server(server.getName(),server.getIpAdd(),server.getLogin(), server.getPassword(), providerRepository.findByName(server.getName())));
    }

    @Override
    public void deleteServer(long id) {
        serverRepository.deleteById(id);
    }

    @Override
    public void ConServerInfo(long id) throws IOException, InterruptedException {
        ServerKey key = serverKeyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Key not found with id " + id));
        Server server = serverRepository.findById(key.getServer().getId())
                .orElseThrow(() -> new ResourceNotFoundException("Server not found with id " + key.getServer().getId()));
        String[] sshCmd = new String[]{"ssh", "-i", key.getKeyPath(), "opc@" + server.getIpAdd(), "sudo -n shutdown -h now"};
        Process sshProcess = Runtime.getRuntime().exec(sshCmd);
        // Read output from ssh
        BufferedReader reader = new BufferedReader(new InputStreamReader(sshProcess.getInputStream()));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }
        // Wait for ssh process
        sshProcess.waitFor();
        // Check for error output
        BufferedReader errorReader = new BufferedReader(new InputStreamReader(sshProcess.getErrorStream()));
        while ((line = errorReader.readLine()) != null) {
            System.err.println(line);
        }

    }



    public ServerDto convertToServerDto(Server server){
        ServerDto serverDetails = new ServerDto();

        serverDetails.setId(server.getId());
        serverDetails.setLogin(server.getLogin());
        serverDetails.setName(server.getName());
        serverDetails.setPassword(server.getPassword());
        serverDetails.setProvider(new ProviderDto(server.getProvider().getId(), server.getProvider().getName()));
        serverDetails.setIpAdd(server.getIpAdd());
        return serverDetails;
    }


}
