package com.group.sdf.repository;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import com.group.sdf.entity.UnitCommander;
import org.springframework.data.jpa.repository.Query;

//public interface CommanderRepository extends CrudRepository<UnitCommander,String>{
public interface CommanderRepository extends CrudRepository<UnitCommander, Integer> {

	@Query("SELECT u FROM UnitCommander u WHERE u.commanderName = :name")//or COMMANDER_NAME
	Optional <UnitCommander> getPasswordOfPlayer(String name);
//	
	
//	@Query(value="Select * FROM     \r\n"
//			+ "UNIT_COMMANDERS UC   \r\n"
//			+ "LEFT JOIN            \r\n"
//			+ "UPGRADES U            \r\n"
//			+ "ON UC.COMMANDER_ID = U.COMMANDER_ID  \r\n"
//			+ "LEFT JOIN UPGRADE_TYPES UT   \r\n"
//			+ "ON U.MODEL_ID = UT.MODEL_ID   \r\n"
//			+ "WHERE UC.COMMANDER_NAME = :name ",nativeQuery=true)
//	Optional <UnitCommander> getPasswordOfPlayer(String name);
//	and UT.MODEL_ID = 1001
//	public String findByCommanderName(String commanderName);
	Optional<UnitCommander> findByCommanderName(String commanderName);	
  
}
