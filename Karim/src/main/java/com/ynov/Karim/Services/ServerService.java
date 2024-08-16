package com.ynov.Karim.Services;

import com.ynov.Karim.Dto.ServerDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;


public interface ServerService {
    List<ServerDto> getAllServer();
    ServerDto getServerById(long id);
    void createServer(ServerDto server);
    void updateServer(Long id, ServerDto server);
    void updateServer(ServerDto server);
    void deleteServer(long id);
    void ConServerInfo(long id) throws IOException, InterruptedException;


}
