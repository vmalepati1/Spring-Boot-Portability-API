package com.portability.api.model;

import java.io.Serializable;
import java.util.List;

public class InteliquentPortabilityResponse implements Serializable {

    private String status;
    private String statusCode;
    private List<ServiceAvailable> serviceAvailable;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public List<ServiceAvailable> getServiceAvailable() {
        return serviceAvailable;
    }

    public void setServiceAvailable(List<ServiceAvailable> serviceAvailable) {
        this.serviceAvailable = serviceAvailable;
    }

    public static class ServiceAvailable {
        private String telephoneNumber;
        private boolean isPortable;
        private String serviceProviderName;
        private String localRoutingNumber;
        private String rateCenterTier;
        private String notPortableReason;

        public String getTelephoneNumber() {
            return telephoneNumber;
        }

        public void setTelephoneNumber(String telephoneNumber) {
            this.telephoneNumber = telephoneNumber;
        }

        public boolean getIsPortable() {
            return isPortable;
        }

        public void setIsPortable(boolean isPortable) {
            this.isPortable = isPortable;
        }

        public String getServiceProviderName() {
            return serviceProviderName;
        }

        public void setServiceProviderName(String serviceProviderName) {
            this.serviceProviderName = serviceProviderName;
        }

        public String getLocalRoutingNumber() {
            return localRoutingNumber;
        }

        public void setLocalRoutingNumber(String localRoutingNumber) {
            this.localRoutingNumber = localRoutingNumber;
        }

        public String getRateCenterTier() {
            return rateCenterTier;
        }

        public void setRateCenterTier(String rateCenterTier) {
            this.rateCenterTier = rateCenterTier;
        }

        public String getNotPortableReason() {
            return notPortableReason;
        }

        public void setNotPortableReason(String notPortableReason) {
            this.notPortableReason = notPortableReason;
        }
    }

}
