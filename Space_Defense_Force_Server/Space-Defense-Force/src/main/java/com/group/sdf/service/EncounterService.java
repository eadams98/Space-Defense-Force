package com.group.sdf.service;

import java.util.ArrayList;
import java.util.List;

import com.group.sdf.dto.EncounterDTO;
import com.group.sdf.dto.UnitCommanderDTO;
import com.group.sdf.entity.Encounter;

public interface EncounterService {
	
	// Have to come back and add throws to this interfaces methods
	ArrayList<EncounterDTO> generateEncounters(Integer commanderId);
	
	// remove
	UnitCommanderDTO updateCommander(Integer commanderId, Integer encounterId);
	
	List<EncounterDTO> getEncounterPage(Integer pageNo, Integer pageSize) throws Exception; 
	
	Integer getNumberOfEncountersForPagesOfSize(Integer pageSize);
	
	EncounterDTO getEncounterDTO(Integer encounterId) throws Exception;
	
	Encounter getEncounterEntity(Integer encounterId) throws Exception;
	
	boolean isValidEncounter(Integer encounterId) throws Exception;
	
}
