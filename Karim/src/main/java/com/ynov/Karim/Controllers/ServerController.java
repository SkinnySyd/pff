package com.ynov.Karim.Controllers;

import com.ynov.Karim.Dto.ServerDto;
import com.ynov.Karim.Entities.Server;
import com.ynov.Karim.Repositories.ServerRepository;
import com.ynov.Karim.Services.ServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)

@RestController
@RequestMapping("/api/servers")
public class ServerController {

    @Autowired
    private ServerRepository serverRepository;

    @Autowired
    private ServerService serverService;

    @GetMapping("/all")
    public ResponseEntity<List<ServerDto>> getAllProject(){
        List<ServerDto> servers = serverService.getAllServer();
        return ResponseEntity.ok(servers);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getServerById(@PathVariable int id) {
        return ResponseEntity.ok
                (serverRepository.findByName
                        (serverService.getServerById(id).getName()));
    }

    @PostMapping("/create")
    public ResponseEntity<Server> createProject(@RequestBody ServerDto server){
             serverService.createServer(server);
            return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/update")
    public ResponseEntity<?> updateProject(@RequestBody ServerDto server){
            serverService.createServer(server);
            return new ResponseEntity<>(HttpStatus.OK);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateServerById(@PathVariable long id, @RequestBody ServerDto server){
        serverService.createServer(server);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteServerById(@PathVariable long id){
        serverService.deleteServer(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/name")
    public ResponseEntity<Server>   getServerByName(@RequestBody ServerDto serverArg){
        String str = serverArg.getName();
        Server server = serverRepository.findByName(str);
        return ResponseEntity.ok(server);
    }

    @GetMapping("/connect/{id}")
    public ResponseEntity<?> connectServer(@PathVariable int id) throws IOException, InterruptedException {
        serverService.ConServerInfo(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
