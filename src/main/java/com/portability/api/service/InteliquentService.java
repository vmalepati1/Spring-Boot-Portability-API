package com.portability.api.service;

import com.portability.api.model.InteliquentAuthResponse;
import com.portability.api.model.InteliquentPortabilityResponse;
import com.portability.api.model.PortabilityResponse;
import com.portability.api.model.TelephoneNumberList;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class InteliquentService implements PortabilityChecker {

    private static final String PRIVATE_KEY = "YOURPRIVATEKEY";
    private static final String CLIENT_ID = "YOURCONSUMERKEY";
    private static final String CLIENT_SECRET = "YOURCONSUMERSECRET";
    private static final String CLIENT_CREDENTIALS = "client_credentials";

    private final RestTemplate restTemplate;

    private String accessToken;

    public InteliquentService() {
        restTemplate = new RestTemplate();

        authenticate();
    }

    private void authenticate() {
        String url = "https://services-token.inteliquent.com/oauth2/token";

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.set("charset", "UTF-8");

        // create a map for post parameters
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", CLIENT_ID);
        map.add("client_secret", CLIENT_SECRET);
        map.add("grant_type", CLIENT_CREDENTIALS);

        // build the request
        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        // send POST request
        ResponseEntity<InteliquentAuthResponse> response = restTemplate.postForEntity(url, request, InteliquentAuthResponse.class);

        // check response status code
        if (response.getStatusCode() == HttpStatus.OK) {
            InteliquentAuthResponse authResponse = response.getBody();
            if (authResponse != null) {
                accessToken = authResponse.getAccessToken();
            }
        } else {
            System.out.println("InteliquentService: Failed to authenticate!");
        }
    }

    @Override
    public PortabilityResponse checkNumber(String tn) throws Exception {
        PortabilityResponse result = new PortabilityResponse(tn, "", false);

        String url = "https://services.inteliquent.com/Services/1.0.0/portInAvailability";

        // create headers
        HttpHeaders headers = new HttpHeaders();
        // set `content-type` header
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(accessToken);

        // create a map for post parameters
        Map<String, Object> map = new HashMap<>();
        map.put("privateKey", PRIVATE_KEY);
        map.put("returnServiceProviderName", true);
        map.put("wireless", "Y");
        map.put("tnList", new TelephoneNumberList(Collections.singletonList(new TelephoneNumberList.TelephoneNumber(Long.parseLong(tn)))));

        // build the request
        HttpEntity<Map<String, Object>> request = new HttpEntity<>(map, headers);
        // send POST request
        ResponseEntity<InteliquentPortabilityResponse> response = restTemplate.postForEntity(url, request, InteliquentPortabilityResponse.class);

        // check response status code
        if (response.getStatusCode() == HttpStatus.OK) {
            InteliquentPortabilityResponse portabilityResponse = response.getBody();
            if (portabilityResponse != null) {
                result.setTelephoneNumber(portabilityResponse.getServiceAvailable().get(0).getTelephoneNumber());
                result.setServiceProviderName(portabilityResponse.getServiceAvailable().get(0).getServiceProviderName());
                result.setPortable(portabilityResponse.getServiceAvailable().get(0).getIsPortable());
            }
        } else {
            System.out.println("InteliquentService: Failed to check number for portability!");
        }

        return result;
    }
}
