package com.portability.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PortabilityResponse {

    private String telephoneNumber;
    private String serviceProviderName;
    private boolean isPortable;

    public PortabilityResponse(@JsonProperty("telephoneNumber") String telephoneNumber,
                               @JsonProperty("telephoneNumber") String serviceProviderName,
                               @JsonProperty("telephoneNumber") boolean isPortable) {
        this.telephoneNumber = telephoneNumber;
        this.serviceProviderName = serviceProviderName;
        this.isPortable = isPortable;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public String getServiceProviderName() {
        return serviceProviderName;
    }

    public boolean isPortable() {
        return isPortable;
    }

}
