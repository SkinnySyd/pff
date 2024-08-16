package com.ynov.Karim.Services.ServicesImpl;

import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.core.ComputeClient;
import com.oracle.bmc.core.model.Instance;
import com.oracle.bmc.core.requests.GetInstanceRequest;
import com.oracle.bmc.core.responses.GetInstanceResponse;
import com.ynov.Karim.Services.VmService;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class VmServiceImpl implements VmService {

    private final ComputeClient computeClient;

    public VmServiceImpl() throws IOException {
        AuthenticationDetailsProvider provider = new ConfigFileAuthenticationDetailsProvider("DEFAULT");
        this.computeClient = new ComputeClient(provider);
    }

    public String getVMState(String instanceId) {
        GetInstanceRequest request = GetInstanceRequest.builder()
                .instanceId(instanceId)
                .build();

        GetInstanceResponse response = computeClient.getInstance(request);
        Instance instance = response.getInstance();
        return instance.getLifecycleState().toString();
    }
}
