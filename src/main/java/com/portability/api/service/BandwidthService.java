package com.portability.api.service;

import com.bandwidth.iris.sdk.IrisClient;
import com.bandwidth.iris.sdk.model.LnpChecker;
import com.bandwidth.iris.sdk.model.NumberPortabilityRequest;
import com.bandwidth.iris.sdk.model.NumberPortabilityResponse;
import com.portability.api.model.PortabilityResponse;
import org.springframework.stereotype.Service;

@Service
public class BandwidthService implements PortabilityChecker {

    private static final String ACCOUNT_ID = "youraccountid";
    private static final String USERNAME = "yourusername";
    private static final String PASSWORD = "yourpassword";

    private IrisClient client;

    public BandwidthService() {
        client = new IrisClient(ACCOUNT_ID, USERNAME, PASSWORD);
    }

    @Override
    public PortabilityResponse checkNumber(String tn) throws Exception {
        PortabilityResponse result = new PortabilityResponse(tn, "", false);
        NumberPortabilityRequest request = new NumberPortabilityRequest();

        request.getTnList().add(tn);
        NumberPortabilityResponse response = LnpChecker.checkLnp(client, request, "true");

        if (response.getPortableNumbers().size() >= 1) {
            result.setPortable(true);
            result.setServiceProviderName(response.getSupportedLosingCarriers().get(0).toString());
        }

        return result;
    }
}
