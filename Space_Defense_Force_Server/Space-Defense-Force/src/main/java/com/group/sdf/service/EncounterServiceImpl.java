package com.group.sdf.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

	@Override
	public List<EncounterDTO> getEncounterPage(Integer pageNo, Integer pageSize) throws Exception {
		// TODO Auto-generated method stub

		int offsetZeroIndex = 1;
		pageNo -= offsetZeroIndex;
		Pageable pageable = PageRequest.of(pageNo, pageSize);
		Page<Encounter> encounterPage = encounterRepository.findAll(pageable);
		
		if (encounterPage.getNumberOfElements() == 0)
			throw new Exception("failure, no results in page");
		
		List<EncounterDTO> encounters = new ArrayList<>();
		encounterPage.forEach(e -> {
			EncounterDTO eDTO = new EncounterDTO();
			eDTO.setEnemyDamage(e.getEnemyDamage());
			eDTO.setEnemyHealth(e.getEnemyHealth());
			eDTO.setEnemyPrestigeGiven(e.getEnemyPrestigeGiven());
			eDTO.setEnemyXPGiven(e.getEnemyXPGiven());
			eDTO.setEnemyName(e.getEnemyName());
			eDTO.setEnemyId(e.getEnemyId());
			encounters.add(eDTO);
		});
		
		return encounters;
	}

	@Override
	public Integer getNumberOfEncountersForPagesOfSize(Integer pageSize) {
		Pageable pageable = PageRequest.ofSize(pageSize);
		Page<Encounter> encounterPage = encounterRepository.findAll(pageable);
		return encounterPage.getTotalPages(); 
	}

	@Override
	public EncounterDTO getEncounterDTO(Integer encounterId) throws Exception {
		// TODO Auto-generated method stub
		Optional<Encounter> encounterOptional = encounterRepository.findById(encounterId);
		
		Encounter encounter = encounterOptional.orElseThrow(() -> new Exception("Not a valid encounter id"));
		
		EncounterDTO eDTO = new EncounterDTO();
		eDTO.setEnemyHealth(encounter.getEnemyHealth());
		eDTO.setEnemyDamage(encounter.getEnemyDamage());
		eDTO.setEnemyName(encounter.getEnemyName());
		eDTO.setEnemyPrestigeGiven(encounter.getEnemyPrestigeGiven());
		eDTO.setEnemyShield(encounter.getEnemyShield());
		eDTO.setEnemyXPGiven(encounter.getEnemyXPGiven());
		
		return eDTO;
	}

	@Override
	public boolean isValidEncounter(Integer encounterId) throws Exception {
		Optional<Encounter> encounterOptional = encounterRepository.findById(encounterId);
		if(encounterOptional.isPresent())
			return true;
		return false;
	}

	@Override
	public Encounter getEncounterEntity(Integer encounterId) throws Exception {
		return encounterRepository.getById(encounterId);
	}

}
