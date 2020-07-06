package com.portability.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PortabilityResponse {

    private String telephoneNumber;
    private String serviceProviderName;
    private boolean isPortable;

    public PortabilityResponse(@JsonProperty("telephoneNumber") String telephoneNumber,
                               @JsonProperty("serviceProviderName") String serviceProviderName,
                               @JsonProperty("isPortable") boolean isPortable) {
        this.telephoneNumber = telephoneNumber;
        this.serviceProviderName = serviceProviderName;
        this.isPortable = isPortable;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getServiceProviderName() {
        return serviceProviderName;
    }

    public void setServiceProviderName(String serviceProviderName) {
        this.serviceProviderName = serviceProviderName;
    }

    public boolean isPortable() {
        return isPortable;
    }

    public void setPortable(boolean portable) {
        isPortable = portable;
    }

}
