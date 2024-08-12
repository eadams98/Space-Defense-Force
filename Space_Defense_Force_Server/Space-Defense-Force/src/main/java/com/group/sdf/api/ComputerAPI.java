package com.group.sdf.api;

import java.util.ArrayList;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.sdf.dto.EncounterDTO;
import com.group.sdf.dto.UnitCommanderDTO;
import com.group.sdf.service.EncounterService;

@RestController
@RequestMapping(value="/AI")
public class ComputerAPI {
	
	@Autowired
	private EncounterService encounterService;
	
	static Log logger = LogFactory.getLog(ComputerAPI.class);
	
	
	@GetMapping(value="/generate-encounters/{commanderId}")
	public ResponseEntity<ArrayList<EncounterDTO>> generateEncounters(@PathVariable Integer commanderId) {
		/*
		 * Probably needs to throw exception, but we have to talk about what specificaly
		 * 
		 * sends request to ecounter service to get ArrayList of possible Encounter DTOs.
		 * Returns list as a response entity.
		 */
		logger.info("before");
		ArrayList<EncounterDTO> possibleEncounters = encounterService.generateEncounters(commanderId);
		return new ResponseEntity<>(possibleEncounters, HttpStatus.OK);
	}
	
	@GetMapping(value="/test")
	public ResponseEntity<String> test() {
		logger.info("before");
		return new ResponseEntity<>("TEST SUCCESSFUL", HttpStatus.OK);
	}
	
	@PostMapping(value="/encounter-results/{commanderId}/{encounterId}")
	public ResponseEntity<UnitCommanderDTO> battleResultUpdate(@PathVariable Integer encounterId, @PathVariable Integer commanderId) {
		/*
		 * Probably needs to throw exception, but we have to talk about what specificaly
		 * 
		 * 
		 */
		logger.info("before");
		UnitCommanderDTO possibleEncounters = encounterService.updateCommander(commanderId, encounterId);
		return new ResponseEntity<>(possibleEncounters, HttpStatus.OK);
	}

}
