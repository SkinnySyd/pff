package com.ynov.Karim.CloudProviders;

import com.oracle.bmc.auth.ConfigFileAuthenticationDetailsProvider;
import com.oracle.bmc.core.ComputeClient;
import com.oracle.bmc.core.model.Instance;
import com.oracle.bmc.core.requests.GetInstanceRequest;
import com.oracle.bmc.core.responses.GetInstanceResponse;

public class OciInstanceChecker {

    private final ComputeClient computeClient;

    public OciInstanceChecker() throws Exception {
        // Load the OCI configuration from the config file
        ConfigFileAuthenticationDetailsProvider provider =
                new ConfigFileAuthenticationDetailsProvider("DEFAULT");

        // Create the ComputeClient
        this.computeClient = new ComputeClient(provider);
    }
    public String getInstanceState(String instanceId) {
        // Prepare the request
        GetInstanceRequest getInstanceRequest = GetInstanceRequest.builder()
                .instanceId(instanceId)
                .build();

        // Execute the request
        GetInstanceResponse getInstanceResponse = computeClient.getInstance(getInstanceRequest);

        // Retrieve the instance
        Instance instance = getInstanceResponse.getInstance();

        // Return the lifecycle state of the instance
        return instance.getLifecycleState().getValue();
    }
}