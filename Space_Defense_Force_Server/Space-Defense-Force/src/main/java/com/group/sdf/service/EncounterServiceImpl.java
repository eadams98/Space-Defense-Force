package com.group.sdf.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group.sdf.api.ComputerAPI;
import com.group.sdf.dto.EncounterDTO;
import com.group.sdf.dto.UnitCommanderDTO;
import com.group.sdf.entity.Encounter;
import com.group.sdf.entity.Unit;
import com.group.sdf.entity.UnitCommander;
import com.group.sdf.repository.CommanderRepository;
import com.group.sdf.repository.EncounterRepository;

@Service(value = "customerService")
public class EncounterServiceImpl implements EncounterService {

	static Log logger = LogFactory.getLog(EncounterServiceImpl.class);
	
	@Autowired
	private CommanderRepository commanderRepository;
	
	@Autowired
	private EncounterRepository encounterRepository;
	
	@Override
	public ArrayList<EncounterDTO> generateEncounters(Integer commanderId) { 
		/*
		 * Loops through current commander's units and identifies the strongest
		 * Uses the strongest attribute as a query parameter to get list of suitable encounters
		 * converts list of encounter entites into list of encounter DTOs and returns
		 * 
		 * (need to fix unit lvls. maybe attach lvl to commander instead of individual troops. That way many to many relationship could work later)
		 * (For front end need to make sure commanders never sell all units. Has to have at least 1 at all times.
		 */
		
		Optional<UnitCommander> optionalCommander = commanderRepository.findById(commanderId);
		UnitCommander commander = optionalCommander.orElseThrow(() -> null); // need to add specific exception
		
		List<Unit> units = commander.getUnit();
		Integer highestDmg = 0;
		
		if (!units.isEmpty()) { // currently checks to see if they have any units (should have at least 1 at all times, but until implemented this is here)
			for (Unit unit : units) {
				Integer currentUnitDmg = unit.getUnitDamage();
				if (currentUnitDmg > highestDmg)
					highestDmg = currentUnitDmg;
			}
		}
		
		ArrayList<Encounter> possibleEncounters = encounterRepository.findByEnemyHealthBetween(1, highestDmg);
		ArrayList<EncounterDTO> possibleEncounterDTOs = new ArrayList<>();
		
		// 	public EncounterDTO(Integer enemyId, String enemyName, Integer enemyHealth, Integer enemyXPGiven, Integer enemyShield, Integer enemyDamage)
		
		possibleEncounters.forEach( e ->  
		possibleEncounterDTOs.add( 
				new EncounterDTO( e.getEnemyId(),
						e.getEnemyName(),
						e.getEnemyHealth(),
						e.getEnemyXPGiven(), e.getEnemyShield(),
						e.getEnemyDamage(),
						e.getEnemyPrestigeGiven()
				)));
		
		return possibleEncounterDTOs;
	}

	@Override
	public UnitCommanderDTO updateCommander(Integer commanderId, Integer encounterId) {
		
		/*
		 * Checks to see if commander and encounter are valid.
		 * Once validated, the xp and prestige from encounter are added to commander Entity.
		 * After the commander Entity is updated, we create an updated commander DTO to return
		 */
		
		Optional<UnitCommander> optionalCommander = commanderRepository.findById(commanderId);
		UnitCommander commander = optionalCommander.orElseThrow(() -> null); // need to add specific exception
		
		Optional<Encounter> optionalEncounter = encounterRepository.findById(commanderId);
		Encounter encounter = optionalEncounter.orElseThrow(() -> null); // need to add specific exception
		
		// commander needs xp tied to his account
		// encounter needs prestige tied to its account
		logger.info("Old Prestige: " + commander.getCommanderPrestige() + " new Prestige: " + (commander.getCommanderPrestige() + encounter.getEnemyPrestigeGiven()) );
		logger.info("Old Expierence: " + commander.getCommanderXP() + " new Expeirence: " + (commander.getCommanderXP() + encounter.getEnemyXPGiven()) );
		
		//logger.info("USE defense_force;");
		//logger.info("UPDATE unit_commanders SET commander_xp = " + (commander.getCommanderXP() + encounter.getEnemyXPGiven()) + " WHERE commander_id = " + commander.getCommanderId() + ";");
		
		// need to update Entity here
		
		// based off updated Entity create updatedDTO below
		UnitCommanderDTO updatedCommanderDTO = new UnitCommanderDTO(); // NEEDS TO BE FIXED
		updatedCommanderDTO.setCommanderId(commanderId);
		updatedCommanderDTO.setCommanderPrestige( commander.getCommanderPrestige() + encounter.getEnemyPrestigeGiven() );
		updatedCommanderDTO.setCommanderXP( commander.getCommanderXP() + encounter.getEnemyXPGiven() );
		
		return updatedCommanderDTO;
	}

}
