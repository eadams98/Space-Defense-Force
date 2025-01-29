package com.group.sdf.api;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.sdf.service.GameService;

@CrossOrigin
@RestController
@RequestMapping(value="/game")
public class GameLogicController {
	
	@Autowired
	GameService gameService;
	
	// change path variable to using jwt to see the commander/user id. Need to validate if commander actually owns UNIT 
	@GetMapping(value="/battle/encounter/{encounterId}/unit/{unitId}")
	public ResponseEntity<Map<Integer, Map<String, String>>> battleResults(@PathVariable Integer encounterId, @PathVariable Integer unitId, @RequestHeader (name="Authorization") String token) throws Exception {
		token = token.split(" ")[1];
		Map<Integer, Map<String, String>> battleResults = gameService.battle(unitId, encounterId, token);
		return new ResponseEntity<>(battleResults, HttpStatus.OK);
	}

}
