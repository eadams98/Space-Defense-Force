package com.group.sdf.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.sdf.dto.EncounterDTO;
import com.group.sdf.service.EncounterService;

@CrossOrigin
@RestController
@RequestMapping(value="encounter")
public class EncounterAPI {
	
	@Autowired
	EncounterService encounterService;
	
	@PostMapping(value="/page/{pageNo}/size/{pageSize}")
	public ResponseEntity<List<EncounterDTO>> loadEncounterPage(@PathVariable int pageNo, @PathVariable int pageSize) throws Exception {
		List<EncounterDTO> encounters = encounterService.getEncounterPage(pageNo, pageSize);
		return new ResponseEntity<>(encounters, HttpStatus.OK);
		
	}
	
	@GetMapping(value="/size/{pageSize}")
	public ResponseEntity<Integer> loadNumberOfEncounterPages(@PathVariable int pageSize) throws Exception {
		Integer totalEncountersPages = encounterService.getNumberOfEncountersForPagesOfSize(pageSize);
		return new ResponseEntity<>(totalEncountersPages, HttpStatus.OK);
		
	}
	
	@GetMapping(value = "/{encounterId}")
	public ResponseEntity<EncounterDTO> loadEncounter(@PathVariable int encounterId) throws Exception {
		EncounterDTO encounter = encounterService.getEncounterDTO(encounterId);
		return new ResponseEntity<>(encounter, HttpStatus.OK);
		
	} 

}
