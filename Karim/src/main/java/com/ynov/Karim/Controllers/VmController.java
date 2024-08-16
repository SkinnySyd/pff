package com.ynov.Karim.Controllers;

import com.ynov.Karim.Dto.VmRequest;
import com.ynov.Karim.Services.VmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vm")
public class VmController {

    private final VmService vmService;

    @Autowired
    public VmController(VmService vmService) {
        this.vmService = vmService;
    }

    @PostMapping("/state")
    public ResponseEntity<String> getVmState(@RequestBody VmRequest vmRequest) {
        String state = vmService.getVMState(vmRequest.getId());
        return ResponseEntity.ok(state);
    }
}
