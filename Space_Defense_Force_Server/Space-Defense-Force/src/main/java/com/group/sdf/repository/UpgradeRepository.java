package com.group.sdf.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.group.sdf.entity.Upgrade;
import com.group.sdf.entity.UpgradeType;

public interface UpgradeRepository extends CrudRepository<Upgrade, Integer> {



//@Query("select ut from Upgrade u join UpgradeType ut on u.modelId = ut.modelId where u.commanderId = :commanderId")
//List<UpgradeType> getAllCommanderUpgrades(Integer commanderId);	
	
	

	
	
}



////Tested queries below 

//@Query(value=" SELECT * FROM UPGRADES U  JOIN UPGRADE_TYPES UT  ON U.MODEL_ID = UT.MODEL_ID  WHERE UT.MODEL_ID                    	\r\n"
//+ " IN (SELECT UU.MODEL_ID FROM  UPGRADES UU  WHERE UU.COMMANDER_ID = :commanderId 	)", nativeQuery=true)
//List<Upgrade> getAllCommanderUpgrades(Integer commanderId);	


//>> THIS QUERY WORKS BUT DOES NOT RETURN LIST OF UPGRADES 
//@Query("select u from Upgrade u where commanderId = :commanderId")
//List<Upgrade> getAllCommanderUpgrades(Integer commanderId);

//>> THIS QUERY WORKS BUT DOES NOT RETURN LIST OF UPGRADES 
//@Query("select ut from Upgrade u join ")//where commanderId = :commanderId
//List<Upgrade> getAllCommanderUpgrades(Integer commanderId);

//>> THIS QUERY WORKS BUT DOES NOT RETURN LIST OF UPGRADES 
//@Query(value=" SELECT *, ut.MODEL_ID FROM UPGRADES u LEFT JOIN UPGRADE_TYPES ut  "
//	     + "ON u.MODEL_ID = ut.MODEL_ID WHERE u.COMMANDER_ID = :commanderId ", nativeQuery=true)
//List<Upgrade> getAllCommanderUpgrades(Integer commanderId);	

//@Query(value=" SELECT   u.MODEL_ID upgradeMid, u.UPGRADE_ID, u.COMMANDER_ID,  "
//	      + "ut.MODEL_ID, ut.MODEL_NAME, ut.SCORE, ut.PRESTIGE_COST, ut.UPGRADE_TYPE     "
//	      + " FROM UPGRADES u LEFT JOIN UPGRADE_TYPES ut "
//	      + " ON  u.MODEL_ID = ut.MODEL_ID"  , nativeQuery=true)
//List<UpgradeType> getAllCommanderUpgrades(Integer commanderId);		
//Optional<Upgrade> getAllCommanderUpgrades(Integer commanderId);	

//@Query(value=" SELECT * FROM upgrades u JOIN upgrade_types ut  ON  u.model_id = ut.model_id WHERE ut.model_id \r\n"
//+ " IN (SELECT model_id  FROM  upgrades   WHERE commander_id = :commanderId )	", nativeQuery=true)

//>> THIS QUERY WORKS BUT DOES NOT RETURN LIST OF UPGRADES 
//@Query(value="SELECT * FROM UPGRADES where COMMANDER_ID = :commanderId ", nativeQuery=true)	
//List<Upgrade> getAllCommanderUpgrades(Integer commanderId);

//
//@Query(value=" SELECT * FROM UPGRADES U LEFT JOIN UPGRADE_TYPES UT           	\r\n"
//		+ " ON U.MODEL_ID = UT.MODEL_ID  WHERE UT.MODEL_ID                    	\r\n"
//		+ " IN (                                 	\r\n"
//		+ "	SELECT UU.MODEL_ID FROM  UPGRADES UU	    \r\n"
//		+ "	WHERE UU.COMMANDER_ID = :commanderId 		\r\n"
//		+ "	)  ", nativeQuery=true)