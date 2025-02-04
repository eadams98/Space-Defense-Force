package com.group.sdf.service;

import java.time.Duration;
import java.time.LocalDateTime;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.group.sdf.entity.UnitCommander;
import com.group.sdf.repository.CommanderRepository;

@Service
@Transactional
public class StaminaServiceImpl implements StaminaService {
    
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    private CommanderRepository commanderRepo;
    
    private final int STAMINA_REGEN_INTERVAL_MINUTES = 1;
    private final int STAMINA_REGEN_AMOUNT = 10;
    private final int BATCH_SIZE = 1;
    
    private final int MAX_STAMINA = 100;

    @Override
    public void staminaRegeneration() {
        
        int page = 0;

        while (true) {
            Pageable pageable = PageRequest.of(page, BATCH_SIZE);
            Page<UnitCommander> commanderPage = commanderRepo.findUnitsWithNonMaxStamina(pageable);

            if (!commanderPage.hasContent()) {
                break;  // Exit loop if no more units to process
            }

            for (UnitCommander commander : commanderPage.getContent()) {
                regenerateStaminaForUnit(commander);
            }

            commanderRepo.saveAll(commanderPage.getContent());
            page++;
        }
        
        
    }
    
    private void regenerateStaminaForUnit(UnitCommander commander) {
        logger.info("commander time: " + commander.getLastStaminaUpdate());
        
        LocalDateTime now = LocalDateTime.now();
        Duration timeElapsed = Duration.between(commander.getLastStaminaUpdate(), now);

        long minutesElapsed = timeElapsed.toMinutes();

        if (minutesElapsed >= STAMINA_REGEN_INTERVAL_MINUTES) {
            int regenCycles = (int) (minutesElapsed / STAMINA_REGEN_INTERVAL_MINUTES);
            int staminaToRegenerate = regenCycles * STAMINA_REGEN_AMOUNT;

            commander.setStamina(Math.min(commander.getStamina() + staminaToRegenerate, MAX_STAMINA));
            commander.setLastStaminaUpdate(commander.getLastStaminaUpdate().plusMinutes(regenCycles * STAMINA_REGEN_INTERVAL_MINUTES));
        }
    }

}
