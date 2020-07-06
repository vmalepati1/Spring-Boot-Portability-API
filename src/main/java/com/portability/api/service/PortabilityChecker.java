package com.portability.api.service;

import com.portability.api.model.PortabilityResponse;

public interface PortabilityChecker {

    PortabilityResponse checkNumber(String tn) throws Exception;

}
