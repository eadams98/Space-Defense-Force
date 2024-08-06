package com.group.sdf.utility;

import java.security.NoSuchAlgorithmException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.group.sdf.dto.UnitCommanderDTO;

@Component
@Aspect
public class LoggingAspect {
	
	static Log logger = LogFactory.getLog(LoggingAspect.class);
	
	//"execution(* com.group.sdf.service.EncounterServiceImpl.updateCommander(..))"
	@AfterReturning(value = "execution(* com.group.sdf.service.EncounterServiceImpl.updateCommander(..))", returning = "returnedCommander")
	public void afterGeneratedEncounter(UnitCommanderDTO returnedCommander) {
		/*
		 * Need to add throws error here probably
		 * Need to write to file
		 * 
		 * Functionality
		 * ============
		 * every time the updateCommander method is called in Service package in the EncounterServiceImpl class, this will be called.
		 * It looks at the return value and formats the corresponeding MySQL query to perform to alter the DB.
		 * 
		 * NOTE: Once placed in file that file only needs to be run once to catch up to the DB everyone else is using. This is strictly
		 * 			for keeping the DBs in sync. 
		 * 
		 * FUTURE: need to somehow check if there is a statment that already resembles the new alter statment. if so delete the old and
		 * 			replace with new (no need to have a ton of alter's that change the same thing a little at a time. Only need current)
		 */
		
		logger.info("After advice called.");
		logger.info("USE defense_force;");
		logger.info("UPDATE unit_commanders SET commander_xp = " + returnedCommander.getCommanderXP() + " WHERE commander_id = " + returnedCommander.getCommanderId() + ";");
		logger.info("After advice end");
	}
	
	
	@AfterReturning(value = "execution(* com.group.sdf.service.PlayerServiceImpl.registerNewPlayer(..))", returning = "returnedCommander")
	public void afterRegistration(UnitCommanderDTO returnedCommander) {
		/*
		 * Need to add throws error here probably
		 * Need to write to file
		 * 
		 * Functionality
		 * ============
		 * every time the registerNewPlayer() method is called in Service package in the PlayerServiceImpl class, this will be called.
		 * It looks at the return value and formats the corresponeding MySQL query to perform to alter the DB.
		 * 
		 * NOTE: Once placed in file that file only needs to be run once to catch up to the DB everyone else is using. This is strictly
		 * 			for keeping the DBs in sync. 
		 * 
		 * FUTURE: need to somehow check if there is a statment that already resembles the new alter statment. if so delete the old and
		 * 			replace with new (no need to have a ton of alter's that change the same thing a little at a time. Only need current)
		 */
		
		logger.info("After advice called for register New Player");
		logger.info("USE defense_force;");
		/*
		try {
			logger.info("INSERT INTO unit_commanders VALUES(" + returnedCommander.getCommanderId() + ", " + returnedCommander.getCommanderName() + ", " + HashingUtility.getHashValue(returnedCommander.getCommanderPassword()) + ", 100, 0"  + ";");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		logger.info("After advice end in New Player");
		
	}
	
	@AfterReturning(value = "execution(* com.group.sdf.service.PlayerServiceImpl.updatePlayerPassword(..))", returning = "returnedCommander")
	public void afterPasswordUpdate(UnitCommanderDTO returnedCommander) {
		/*
		 * Need to add throws error here probably
		 * Need to write to file
		 * 
		 * Functionality
		 * ============
		 * every time the registerNewPlayer() method is called in Service package in the PlayerServiceImpl class, this will be called.
		 * It looks at the return value and formats the corresponeding MySQL query to perform to alter the DB.
		 * 
		 * NOTE: Once placed in file that file only needs to be run once to catch up to the DB everyone else is using. This is strictly
		 * 			for keeping the DBs in sync. 
		 * 
		 * FUTURE: need to somehow check if there is a statment that already resembles the new alter statment. if so delete the old and
		 * 			replace with new (no need to have a ton of alter's that change the same thing a little at a time. Only need current)
		 */
		
		logger.info("After advice called for update password");
		logger.info("USE defense_force;");
		logger.info("SELECT COMMANDER_PASSWORD FROM unit_commanders WHERE COMMANDER_ID = " + returnedCommander.getCommanderId());
		logger.info("UPDATE unit_commanders SET COMMANDER_PASSWORD = " + returnedCommander.getCommanderPassword() + " WHERE COMMANDER_ID = " + returnedCommander.getCommanderId() + ";");
		logger.info("After advice ended for update password");
		
	}
	
	@AfterReturning(value = "execution(* com.group.sdf.service.PlayerServiceImpl.updatePlayer(..))", returning = "returnedCommander")
	public void afterDeletePlayer(UnitCommanderDTO returnedCommander) {
		/*
		 * Need to add throws error here probably
		 * Need to write to file
		 * 
		 * Functionality
		 * ============
		 * every time the registerNewPlayer() method is called in Service package in the PlayerServiceImpl class, this will be called.
		 * It looks at the return value and formats the corresponeding MySQL query to perform to alter the DB.
		 * 
		 * NOTE: Once placed in file that file only needs to be run once to catch up to the DB everyone else is using. This is strictly
		 * 			for keeping the DBs in sync. 
		 * 
		 * FUTURE: need to somehow check if there is a statment that already resembles the new alter statment. if so delete the old and
		 * 			replace with new (no need to have a ton of alter's that change the same thing a little at a time. Only need current)
		 */
		
		logger.info("After advice called for delete commander");
		logger.info("USE defense_force;");
		logger.info("DELETE FROM unit_commanders WHERE COMMANDER_ID = " + returnedCommander.getCommanderId() + ";");
		logger.info("After advice ended for delete commander");
		
	}


}
