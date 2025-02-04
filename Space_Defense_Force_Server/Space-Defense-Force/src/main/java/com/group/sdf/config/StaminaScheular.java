package com.group.sdf.config;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.group.sdf.service.StaminaService;

@Component
public class StaminaScheular {
    
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());

    
    @Autowired
    StaminaService staminaService;
    
    @Scheduled(fixedRate = 60000)  // Run every 60 seconds. bad implementation. Probably need Kafka queue to honor things in timely manner
    public void runStaminaRegeneration() {
        try {
            staminaService.staminaRegeneration();
        } catch (Exception ex) {
            logger.info("exception caught in task: %s", ex);
        }
    }

}
