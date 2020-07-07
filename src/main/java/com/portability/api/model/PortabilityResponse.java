package com.portability.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PortabilityResponse {

    @JsonProperty("telephoneNumber")
    private String telephoneNumber;
    @JsonProperty("serviceProviderName")
    private String serviceProviderName;
    @JsonProperty("isPortable")
    private boolean isPortable;

    public PortabilityResponse(String telephoneNumber,
                               String serviceProviderName,
                               boolean isPortable) {
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
