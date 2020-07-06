package com.portability.api.service;

import com.portability.api.model.PortabilityResponse;
import org.springframework.stereotype.Service;

@Service
public class PeerlessService implements PortabilityChecker {

    @Override
    public PortabilityResponse checkNumber(String tn) throws Exception {
        return null;
    }

}
