package com.portability.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.List;

public class TelephoneNumberList implements Serializable {

    List<TelephoneNumber> telephoneNumbers;

    public TelephoneNumberList(@JsonProperty("tnItem")List<TelephoneNumber> telephoneNumbers) {
        this.telephoneNumbers = telephoneNumbers;
    }

    public List<TelephoneNumber> getTelephoneNumbers() {
        return telephoneNumbers;
    }

    public void setTelephoneNumbers(List<TelephoneNumber> telephoneNumbers) {
        this.telephoneNumbers = telephoneNumbers;
    }

    public static class TelephoneNumber implements Serializable {

        long telephoneNumber;

        public TelephoneNumber(@JsonProperty("tn") long telephoneNumber) {
            this.telephoneNumber = telephoneNumber;
        }

        public long getTelephoneNumber() {
            return telephoneNumber;
        }

        public void setTelephoneNumber(long telephoneNumber) {
            this.telephoneNumber = telephoneNumber;
        }

    }
}
