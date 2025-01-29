package com.group.sdf.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.sdf.entity.Encounter;
import com.group.sdf.entity.Unit;
import com.group.sdf.entity.UnitCommander;
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
	public Map<Integer, Map<String, String>> battle(int unitId, int encounterId, String token) throws Exception {
		
		validateEntities(unitId, encounterId, token);
		
		Encounter encounter = encounterService.getEncounterEntity(encounterId);
		Unit unit = playerService.getUnit(unitId);
		
		Map<Integer, Map<String, String>> gameResults = conductBattle(encounter, unit);
		
		return gameResults;
	}
	
	private Map<Integer, Map<String, String>> conductBattle(Encounter encounter, Unit unit) throws Exception {
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
		
		Map<Integer, Map<String, String>> battleResults = new HashMap<>();
		Random random = new Random();
		Integer turn = 1;
		
		while (currentUnitHealth > 0 && currentEnemyHealth > 0) {
			// Unit's turn to attack
            Integer unitDiceRoll = random.nextInt(6) + 1; // Roll a 6-sided dice
            Integer unitAttack = unitDiceRoll + unitDamage;
            if (enemyShield != 0) {
            	enemyShield = enemyShield - unitAttack;
            	if (enemyShield < 0)
            		unitAttack = Math.abs(enemyShield);
            	enemyShield = Math.max(0, enemyShield);
            } 
            currentEnemyHealth -= unitAttack;
            
            // Record unit's attack results
            Map<String, String> turnResult = new HashMap<>();
            turnResult.put("turn", turn.toString());
            turnResult.put("unitDiceRoll", unitDiceRoll.toString());
            turnResult.put("unitAttack", unitAttack.toString());
            turnResult.put("enemyRemainingHealth", String.valueOf(Math.max(0, currentEnemyHealth)));
            turnResult.put("enemyRemainingShield", String.valueOf(enemyShield));
            
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
                turnResult.put("enemyDiceRoll", enemyDiceRoll.toString());
                turnResult.put("enemyAttack", enemyAttack.toString());
                turnResult.put("unitRemainingHealth", String.valueOf(Math.max(0, currentUnitHealth)));
                turnResult.put("unitRemainingShield", String.valueOf(unitShield));
            }

            // Record the turn result
            turnResult.put("winner", currentUnitHealth <= 0 ? "Enemy" : (currentEnemyHealth <= 0 ? "Unit" : "None"));
            battleResults.put(turn, turnResult);

            turn++;

		}
		
		return battleResults;
		
	}
	
	private void validateEntities(int unitId, int encounterId, String token) throws Exception {
		logger.info("token: " + token);
		Integer commanderId = jwtUtil.getCommanderIdFromToken(token);
		logger.info("commander id " + commanderId);
		UnitCommander commander = playerService.getCommander(commanderId);
		
		logger.info("bag unit size: " + commander.getBag().getBagUnits().size());
		logger.info("bag unit 1: " + commander.getBag().getBagUnits().get(0).getUnit());
		
		if(!encounterService.isValidEncounter(encounterId))
			throw new Exception("Not a valid encounterId");
		
		// need to check if unit is valid and if commander owns this specific user. Should use JWT
		if (!playerService.isValidUnit(unitId))
			throw new Exception("Not a valid unit");
		
		if (!bagRepository.existsByUnitIdAndCommanderId(unitId, commanderId))
			throw new Exception("Commander doesn't own this unit");
	}

}
