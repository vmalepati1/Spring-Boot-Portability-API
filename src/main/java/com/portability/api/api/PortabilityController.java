package com.portability.api.api;

import com.portability.api.model.PortabilityResponse;
import com.portability.api.service.BandwidthService;
import com.portability.api.service.InteliquentService;
import com.portability.api.service.PeerlessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("api/v1/portability")
@RestController
public class PortabilityController {

    private final BandwidthService bandwidthService;
    private final InteliquentService inteliquentService;
    private final PeerlessService peerlessService;

    @Autowired
    public PortabilityController(BandwidthService bandwidthService,
                                 InteliquentService inteliquentService,
                                 PeerlessService peerlessService) {
        this.bandwidthService = bandwidthService;
        this.inteliquentService = inteliquentService;
        this.peerlessService = peerlessService;
    }

    @GetMapping(path = "bandwidth/{tn}")
    public PortabilityResponse getBandwidthPortability(@PathVariable("tn") String tn) throws Exception {
        return bandwidthService.checkNumber(tn);
    }

    @GetMapping(path = "inteliquent/{tn}")
    public PortabilityResponse getInteliquentPortability(@PathVariable("tn") String tn) throws Exception {
        return inteliquentService.checkNumber(tn);
    }

    @GetMapping(path = "peerless/{tn}")
    public PortabilityResponse getPeerlessPortability(@PathVariable("tn") String tn) throws Exception {
        return peerlessService.checkNumber(tn);
    }

}
