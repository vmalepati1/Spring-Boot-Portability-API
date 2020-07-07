package com.portability.api.service;

import com.portability.api.exceptions.APIRequestException;
import com.portability.api.model.PortabilityResponse;
import com.portability.api.utils.PeerlessXML;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class PeerlessService implements PortabilityChecker {

    private static final String REQUEST_URL = "https://aniweb02.peerlessnetwork.com:8181/animateapi/axis/APIService";

    private static final String CUSTOMER = "PEERL";
    private static final String PASS_CODE = ">!@#$%^&*()*&^%$#@!";
    private static final String USER_ID = "user@customer.com";

    private final RestTemplate restTemplate;

    public PeerlessService() {
        restTemplate = new RestTemplate();
    }

    @Override
    public PortabilityResponse checkNumber(String tn) throws Exception {
        PortabilityResponse result = new PortabilityResponse(tn, "", false);

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.set("Content-Type", "text/xml");

        HttpEntity<String> request = new HttpEntity<>(PeerlessXML.generatePortabilityXML(CUSTOMER, PASS_CODE, USER_ID, tn), headers);

        ResponseEntity<String> response;

        try {
            response = restTemplate.postForEntity(REQUEST_URL, request, String.class);
        } catch (Exception e) {
            throw new APIRequestException();
        }

        String responseXML = response.getBody();

        if (responseXML == null) {
            throw new APIRequestException();
        }

        if (PeerlessXML.responseReturnedError(responseXML)) {
            System.out.println("PeerlessService: Peerless API response returned an error:");
            System.out.println(responseXML);
            throw new APIRequestException();
        }

        return PeerlessXML.extractPortabilityResponse(responseXML);
    }

}
