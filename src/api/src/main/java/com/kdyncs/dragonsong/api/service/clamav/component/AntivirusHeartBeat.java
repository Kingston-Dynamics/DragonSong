package com.kdyncs.dragonsong.api.service.clamav.component;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class AntivirusHeartBeat {
    
    //private boolean isBeating = false;
    //@Autowired
    //private AntivirusService service;
    
    @Scheduled(fixedRate = 5000)
    public void ping() {
        
        //isBeating = service.isAvailable();
    }
    
    public boolean isBeating() {
        return false;
        //return isBeating;
    }
    
}
