package com.group.sdf.service;


import java.security.NoSuchAlgorithmException;
import com.group.sdf.dto.UnitCommanderDTO;
import com.group.sdf.dto.UnitDTO;
import com.group.sdf.entity.Unit;
import com.group.sdf.entity.UnitCommander;

public interface PlayerService {

	//>> has to be Integer not int    UnitDTO unit or unitDto ? 
//	UnitCommanderDTO makeUnit(Integer commanderId, UnitDTO unit) throws Exception;
	UnitCommanderDTO makeUnit(Integer commanderId, UnitDTO unitDto) throws Exception;
  
	  // Brandons methods
	  UnitCommanderDTO authenticatePlayer(String commanderName, String commanderPassword ) throws Exception, NoSuchAlgorithmException;
		
	  UnitCommanderDTO registerNewPlayer(UnitCommanderDTO unitCommanderDTO ) throws Exception; // From String to DTO for AOP purposes
		
	  UnitCommanderDTO updatePlayerPassword(UnitCommanderDTO unitCommanderDTO)throws Exception, NoSuchAlgorithmException;
	
	  UnitCommanderDTO updatePlayer( UnitCommanderDTO unitCommanderDTO) throws Exception;
	  
	  boolean isValidCommander(Integer commanderId);
	  
	  boolean isValidUnit(Integer unitId);
	  
	  Unit getUnit(Integer unitId);
	  
	  UnitCommander getCommander(Integer commanderId);

}
