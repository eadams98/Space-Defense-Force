package com.group.sdf.api;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.sdf.dto.UnitCommanderDTO;
import com.group.sdf.service.PlayerService;
import com.group.sdf.service.PlayerServiceImpl;

import com.group.sdf.dto.UnitDTO;

@CrossOrigin
@RestController
@RequestMapping(value="player")
@Validated // forgot what this does. Something to do with DTO confirmation I'm sure. // yes is for accessing validation methods on request body , and possibly path variables
public class PlayerAPI {
	
	@Autowired
	private PlayerService playerService; 
	
	@Autowired
	private Environment environment; // Eric: added private modifier
	
  // Brandon's test 
	@GetMapping(value="/hello")
	public ResponseEntity<String>getSome()
	throws Exception
		{
		String message="Hello from Player API HOMIE";
		return new ResponseEntity<>(message, HttpStatus.OK);
		}
	
	// PLAYER LOGIN :
	@PostMapping(value="/login")
	public ResponseEntity<UnitCommanderDTO> authenticatePlayer( @Valid @RequestBody UnitCommanderDTO unitCommanderDTO)
	throws Exception //->NEEDS REAL EXCEPTION TYPE , AND LOGGING BELOW 
		{
		String name = unitCommanderDTO.getCommanderName();
		String pass = unitCommanderDTO.getCommanderPassword();
		UnitCommanderDTO unitCommanderFromDb = playerService.authenticatePlayer(name, pass );
		return new ResponseEntity<>( unitCommanderFromDb, HttpStatus.OK);
		}
	
	// NEW PLAYER REGISTRATION :
	@PostMapping(value="/alter") 
	//public ResponseEntity<String> registerPlayer(@Valid @RequestBody UnitCommanderDTO unitCommanderDTO)
	public ResponseEntity<UnitCommanderDTO> registerPlayer(@Valid @RequestBody UnitCommanderDTO unitCommanderDTO)
	throws Exception
		{
		System.out.println("the commander name "+ unitCommanderDTO.getCommanderName());
		UnitCommanderDTO registerMessage=playerService.registerNewPlayer(unitCommanderDTO);
		return new ResponseEntity<>( registerMessage, HttpStatus.OK);
		}	
	
  // Make Unit From given unitDTO and register to UnitCommanderDTO : (?) (point of discussion. Should player be able to create own Unit)
	@PutMapping(value="/new-unit/{commanderId}")
	public UnitCommanderDTO makeUnit(@PathVariable int commanderId, @RequestBody UnitDTO unit) throws Exception {
		// create new unit with assigned commanderId
		// update commanderDTO to include unit in units[]
		UnitCommanderDTO cdto = playerService.makeUnit(commanderId, unit);
		return cdto;
	}

	/// UPDATE - PLAYER PASSWORD :
	@PutMapping(value="/alter")
	public ResponseEntity<UnitCommanderDTO>
	updatePlayerPass( @RequestBody UnitCommanderDTO unitCommanderDTO )
	throws Exception
		{
		UnitCommanderDTO updateMessage = playerService.updatePlayerPassword( unitCommanderDTO); // Eric: changed pservice to playerService
		return new ResponseEntity<>(updateMessage , HttpStatus.OK);
		}	
	
    /// DELETE - PLAYER :
	@DeleteMapping(value="/alter") 
	public ResponseEntity<UnitCommanderDTO>
	deleteThePlayer( @RequestBody UnitCommanderDTO unitCommanderDTO )
	throws Exception
		{
		UnitCommanderDTO updateMessage = playerService.updatePlayer( unitCommanderDTO); // Eric: changed pservice to playerService
		return new ResponseEntity<>(updateMessage , HttpStatus.OK);
		}	
	
	
	
}// END CLASS 
