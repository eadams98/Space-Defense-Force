package com.group.sdf.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.sdf.dto.BattleResultDTO;
import com.group.sdf.entity.Encounter;
import com.group.sdf.entity.Unit;
import com.group.sdf.entity.UnitCommander;
import com.group.sdf.enums.Winner;
import com.group.sdf.repository.BagRepository;
import com.group.sdf.utility.JwtUtils;

@Service
public class GameServiceImpl implements GameService {
	
	private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	BagRepository bagRepository;
	
	@Autowired
	EncounterService encounterService;
	
	@Autowired
	PlayerService playerService;
	
	@Autowired
	private JwtUtils jwtUtil;

	@Override
	public List<BattleResultDTO> battle(int unitId, int encounterId, String token) throws Exception {
		
		validateEntities(unitId, encounterId, token);
		
		Encounter encounter = encounterService.getEncounterEntity(encounterId);
		Unit unit = playerService.getUnit(unitId);
		
		List<BattleResultDTO> gameResults = conductBattle(encounter, unit);
		
		return gameResults;
	}
	
	private List<BattleResultDTO> conductBattle(Encounter encounter, Unit unit) throws Exception {
	    
		if (encounter == null || unit == null)
			throw new Exception("encounter or unit null. Not able to conduct battle");
		
		// unit stats
		Integer currentUnitHealth = unit.getUnitHealth();
		Integer unitDamage = unit.getUnitDamage();
		Integer unitShield = unit.getUnitShield();
		
		// encounter stats
		Integer currentEnemyHealth = encounter.getEnemyHealth();
		Integer enemyDamage = encounter.getEnemyDamage();
		Integer enemyShield = encounter.getEnemyShield();
		
		List<BattleResultDTO> battleResultList = new ArrayList<>();
		
		Random random = new Random();
		Integer turn = 1;
		Integer DICE_OFFSET = 1;
		
		while (currentUnitHealth > 0 && currentEnemyHealth > 0) {
		    BattleResultDTO battleResult = new BattleResultDTO();
		    
			// Unit's turn to attack
            Integer unitDiceRoll = random.nextInt(6) + DICE_OFFSET; // Roll a 6-sided dice
            Integer unitAttack = unitDiceRoll + unitDamage;
            if (enemyShield != 0) {
            	enemyShield = enemyShield - unitAttack;
            	if (enemyShield < 0)
            		unitAttack = Math.abs(enemyShield);
            	enemyShield = Math.max(0, enemyShield);
            } 
            currentEnemyHealth -= unitAttack;
            
            // Record unit's attack results
            battleResult.setTurn(turn);
            battleResult.setUnitDiceRoll(unitDiceRoll);
            battleResult.setUnitAttack(unitAttack);
            battleResult.setEnemyHealth(Math.max(0, currentEnemyHealth));
            battleResult.setEnemyShield(enemyShield);
            
            // Enemy's turn to attack if it's still alive
            if (currentEnemyHealth > 0) {
                Integer enemyDiceRoll = random.nextInt(6) + 1;
                Integer enemyAttack = enemyDiceRoll + enemyDamage;
                if (unitShield != 0) {
                	unitShield = unitShield - enemyAttack;
                	if (unitShield < 0)
                		enemyAttack = Math.abs(unitShield);
                	unitShield = Math.max(0, unitShield);
                }
                enemyAttack = Math.max(0, enemyAttack); // Ensure attack doesn't go negative
                currentUnitHealth -= enemyAttack;
                

                // Record enemy's attack results
                battleResult.setEnemyDiceRoll(enemyDiceRoll);
                battleResult.setEnemyAttack(enemyAttack);
                battleResult.setUnitHealth(Math.max(0, currentUnitHealth));
                battleResult.setUnitShield(unitShield);
            }

            // Record the turn result
            battleResult.setWinner(currentUnitHealth <= 0 ? Winner.ENCOUNTER : (currentEnemyHealth <= 0 ? Winner.COMMANDER : Winner.NONE));
            battleResultList.add(battleResult);
            
            turn++;

		}
		
		return battleResultList;
		
	}
	
	private void validateEntities(int unitId, int encounterId, String token) throws Exception {
	    
		Integer commanderId = jwtUtil.getCommanderIdFromToken(token);
		UnitCommander commander = playerService.getCommander(commanderId);
		
		if(!encounterService.isValidEncounter(encounterId))
			throw new Exception("Not a valid encounterId");
		
		// need to check if unit is valid and if commander owns this specific user. Should use JWT
		if (!playerService.isValidUnit(unitId))
			throw new Exception("Not a valid unit");
		
		if (!bagRepository.existsByUnitIdAndCommanderId(unitId, commanderId))
			throw new Exception("Commander doesn't own this unit");
		
		Encounter encounter = encounterService.getEncounterEntity(encounterId);
		Integer encounterCost = encounter.getStaminaCost();
		
		if (commander.getStamina() < encounterCost) // hard coded 
		    throw new Exception("Commander doesn't have enough stamina");
		
		commander.setStamina(commander.getStamina() - encounterCost);
		try {
		    playerService.saveCommander(commander);
		    logger.info("saved commander"); 
		} catch (Exception ex) {
            ex.printStackTrace();  
        }
	}

}
