package com.group.sdf.service;

import java.util.ArrayList;

import com.group.sdf.dto.EncounterDTO;
import com.group.sdf.dto.UnitCommanderDTO;

public interface EncounterService {
	
	// Have to come back and add throws to this interfaces methods
	ArrayList<EncounterDTO> generateEncounters(Integer commanderId);
	
	UnitCommanderDTO updateCommander(Integer commanderId, Integer encounterId);
	
}
