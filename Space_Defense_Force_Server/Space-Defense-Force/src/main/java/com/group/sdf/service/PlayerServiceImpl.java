package com.group.sdf.service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.group.sdf.dto.UnitCommanderDTO;
import com.group.sdf.dto.UnitDTO;
import com.group.sdf.dto.UpgradeDTO;
import com.group.sdf.dto.UpgradeTypeDTO;
import com.group.sdf.entity.Unit;
import com.group.sdf.entity.UnitCommander;
import com.group.sdf.entity.Upgrade;
import com.group.sdf.entity.UpgradeType;
import com.group.sdf.repository.CommanderRepository;
import com.group.sdf.repository.UnitRepository;
import com.group.sdf.repository.UpgradeRepository;
import com.group.sdf.repository.UpgradeTypeRepository;
import com.group.sdf.utility.HashingUtility;

//import com.group.sdf.utility.HashingUtility;

@Service(value = "playerService")
@Transactional
public class PlayerServiceImpl implements PlayerService{
	@Autowired
	private CommanderRepository commanderRepo;
	@Autowired
	private UnitRepository unitRepo;
	@Autowired
	private UpgradeTypeRepository upgradeTypeRepository;
	@Autowired
	private UpgradeRepository upgradeRepository;

	static Log logger = LogFactory.getLog(PlayerServiceImpl.class);
	
	@Override
	public UnitCommanderDTO makeUnit(Integer commanderId, UnitDTO unitDto) throws Exception {
		
		Optional<UnitCommander> optionCommand = commanderRepo.findById(commanderId);
		UnitCommander commander = optionCommand.orElseThrow(() -> new Exception("no commander found"));
		Unit unit = UnitDTO.generateUnit(unitDto);
//		unit.setCommanderId(commanderId); 
		// set unit defaults
		unit.setUnitHealth(100);
		unit.setUnitDamage(5);
		unit.setUnitShield(5);
		unit = unitRepo.save(unit);
		List<Unit> units = unitRepo.findByCommanderId(commanderId);
		UnitCommanderDTO commandDTO = UnitCommanderDTO.entityToDTO(commander);//>> BS new quick conversion creation
		return commandDTO;
	}
  	
	@Override   /// LOGIN PLAYER :
	public UnitCommanderDTO authenticatePlayer(String commanderName, String commanderPassword )
	throws Exception, NoSuchAlgorithmException
		{
		Optional <UnitCommander>commanderDetails 	= commanderRepo.findByCommanderName(commanderName);
		UnitCommander 			dbResponse 			= commanderDetails.orElseThrow( ()-> new Exception("User Name Not Found") );
		String 					hashedPassword 		= HashingUtility.getHashValue(commanderPassword);	
		UnitCommanderDTO 		ucDTO 				= UnitCommanderDTO.entityToDTO(dbResponse); //>> BS new quick conversion creation		
		logger.info(commanderName + " " + dbResponse.getCommanderName() + " || " + commanderPassword + " " + dbResponse.getCommanderPassword());
	    logger.info("===> HASH PASS RECEIVED  " + hashedPassword );
	    logger.info("===> HASH PASS IN    DB  " + dbResponse.getCommanderPassword() );
	    logger.info("ucDTO to string: " + ucDTO.getUpgradeDTOList()); // getUpgradeDTOList (still have to pull object
	    
		 ///>>> <> -- NEW AND IMPROVED -- <>   GET COMMANDER UPGRADES >>>>
	    logger.info(ucDTO.getUpgradeTypeDTOList());
	    for (UpgradeDTO upgradeDTO : ucDTO.getUpgradeDTOList()) {
	    	logger.info("From upgrade Repo: " +  upgradeDTO.getUpgradeTypeDTO());//upgradeRepository.findById(upgradeDTO.getCommanderId()) );
	    }
	    /*
	    List<UpgradeType> upgradeDetails = upgradeRepository.getAllCommanderUpgrades(dbResponse.getCommanderId());
	    if ( !upgradeDetails.isEmpty())
		     {	 
		      //>>BS take list and existing dto, pass them to commander dto class convert to UpgradeDTO, and set on commander
		     ucDTO = UnitCommanderDTO.entityUpgradeTypeListToDTO(ucDTO, upgradeDetails)  ; 
			  logger.info("<<< New Query UpgradeType data LENGTH is :>>> " + upgradeDetails.size());
			  
		     for ( UpgradeType up : upgradeDetails )
		       	 {
		       	  logger.info("modelid   :"  + up.getModelId() ); 
		       	  logger.info("modelName :"  + up.getModelName()); 
		       	  logger.info("type 	 :"  + up.getType() ); 
		       	  logger.info("presCost  :"  + up.getPrestigeCost() ); 
		       	  logger.info("score     :"  + up.getScore() );
		         }
		     }
		*/
		
	         // ENSURE BOTH CREDENTIALS ARE PAIR AND MATCH WHAT WAS PROVIDED 
	    if ( commanderName.equals( dbResponse.getCommanderName() )  && hashedPassword.equals( dbResponse.getCommanderPassword() ) )
			{
	
				 logger.info(dbResponse.getCommanderName());
				 logger.info(ucDTO.getCommanderName());
				 logger.info("the list of upgrade types is"+ ucDTO.getUpgradeTypeDTOList()); // Should always be blank unless you set it explictly (see UnitCommander entity for explanation)
				 return ucDTO;
				}
			else 
				logger.info("Password Incorrect"); 
	            throw new Exception("Password Incorrect, Input correct Current Password");
	           /// SHOULD BE RETURNING NULL COMMANDER DTO IF THE PASSWORD IS WRONG 
			  //return  new UnitCommanderDTO();
			}

	
	@Override  /// REGISTER NEW PLAYER :
	//public String registerNewPlayer(UnitCommanderDTO unitCommanderDTO )
	public UnitCommanderDTO registerNewPlayer(UnitCommanderDTO unitCommanderDTO ) throws Exception
		{
		String loginMessage="";
		Optional<UnitCommander> commanderDetails = commanderRepo.findByCommanderName(  unitCommanderDTO.getCommanderName()  );
		if ( commanderDetails.isPresent() )
			{
				//loginMessage="Choose a Different User Name, ( " + unitCommanderDTO.getCommanderName()+ " ) Is Already In Use";
		      	throw new Exception("User Name Already in Use "); 
			}		
		else 
			{
				//String hashThePass  = unitCommanderDTO.getCommanderPassword(); //this replaced by hashutility func
				String hashThePass  =HashingUtility.getHashValue(unitCommanderDTO.getCommanderPassword());
				//unitCommanderDTO.setCommanderPassword(hashThePass); // SECURITY RISK. Don't pass back the password. If someone could access the hashed pass and knew hashing algo, they could potentially (very unlikely) crack other passwords. Besides we don't require this on the front.
				unitCommanderDTO.setCommanderPassword(null); // Users should know their own password. We don't need it for front end
				UnitCommander ucEntity = new UnitCommander();
				ucEntity.setCommanderName(unitCommanderDTO.getCommanderName());
				ucEntity.setCommanderPassword(hashThePass); 
				commanderRepo.save(ucEntity);
				unitCommanderDTO.setCommanderId( ucEntity.getCommanderId()  );
				loginMessage =" Welcome Aboard Commander " + unitCommanderDTO.getCommanderName() + " commander id " +  ucEntity.getCommanderId() ;
				return unitCommanderDTO;
		    }
		 //return loginMessage; // maybe add to DTO response or reconfig back to string (need DTO tho for AOP Logging)
		}
	
	
	@Override // UPDATE PLAYER PASSWORD :
	public UnitCommanderDTO updatePlayerPassword(UnitCommanderDTO commander) 
	throws Exception, NoSuchAlgorithmException
		{
		Optional<UnitCommander>	commanderDetails 	= commanderRepo.getPasswordOfPlayer(commander.getCommanderName());
		UnitCommander 			dbResponse 			= commanderDetails.orElseThrow( ()-> new Exception("User Name Not Found") );
		String 					hashedOldPassword 	= HashingUtility.getHashValue(commander.getCommanderPassword());//commander.getCommanderPassword(); //
		String 					hashedNewPassword 	=  HashingUtility.getHashValue(commander.getCommanderNewPassword());	//commander.getCommanderNewPassword(); //
		String 					updateMessage		= "";
		
		if ( hashedOldPassword.equals(dbResponse.getCommanderPassword()))
			{
             dbResponse.setCommanderPassword(hashedNewPassword); // need to be saved to repo?
             
             updateMessage="Success Your Password has been Changed, please save this password in your records";
             UnitCommanderDTO unitCommanderDTO = UnitCommanderDTO.entityToDTO(dbResponse);
             //unitCommanderDTO.setCommanderPassword(hashedNewPassword); // SECURITY RISK. Don't pass back the password. If someone could access the hashed pass and knew hashing algo, they could potentially (very unlikely) crack other passwords. Besides we don't require this on the front.
             return unitCommanderDTO;
			}
		else 
			{
			updateMessage="Current Password Does Not Match Saved Password, Input correct Current Password, to Update to New Password";
			throw new Exception("Current Password Does Not Match Saved Password, Input correct Current Password, to Update to New Password");
			}
		//return updateMessage;
		}	
	
	
	@Override // DELETE THE PLAYER :
	public UnitCommanderDTO updatePlayer( UnitCommanderDTO commander) 
    throws Exception
	    {
		Optional<UnitCommander>	commanderDetails 	= commanderRepo.getPasswordOfPlayer(commander.getCommanderName());
		UnitCommander 			dbResponse 			= commanderDetails.orElseThrow( ()-> new Exception("User Name Not Found") );
		String 					hashedPassword   	= HashingUtility.getHashValue(commander.getCommanderPassword());//commander.getCommanderPassword(); //
		String 					updateMessage		= "";
		if ( commander.getCommanderName().equals( dbResponse.getCommanderName() )  && hashedPassword.equals( dbResponse.getCommanderPassword() ) )
//		if ( hashedOldPassword.equals(dbResponse.getCommanderPassword()))
			{
			UnitCommanderDTO unitCommanderDTO = UnitCommanderDTO.entityToDTO(dbResponse);
	         commanderRepo.delete(dbResponse);
	         updateMessage=" Success ! You have Deleted your Commander, We are Honored to have Served with you Commander " +  commander.getCommanderName()+" SIR ! ";
	         return unitCommanderDTO;
			}
		else 
			{
			//updateMessage="Current Password Does Not Match Saved Password, Input correct Current Password, before Commander Can be Deleted";
			throw new Exception("Exception Current Password Does Not Match Saved Password, Input correct Current Password, before Commander Can be Deleted");
			}
		//return updateMessage;
		}

	
	
	
	
}////END CLASS



///////////////////////////////////////////////////////////////////////////////////////////////////
////////////////////    SERVICE CODE BONEYARD   ///////////////////////////////////////////
/// DEPRECATED CODE SAMPLES 
///////////////////////////////////////////////////////////////////////////////////////////////////

/*


///////////////////////////////////////////
////// BELOW FROM PLAYER LOGIN  SERVICE 
///////////////////////////////////////////
/// COMBINING UPGRADES WITH EXISTING DTO AND QUERIES THUS FAR
/// Send new query to Upgrade Type Repo  by accessing one of the model ids on the current DTO object return by first repo query 
/// we will have to get and create array of all ids then process query to get all ids in our array 
/// or we do a query to the Upgrade table and get return items that way by model id  
// call upgrade query unpack results to logger
///BS DEBUG TEST NEW QUERY BELOW 


//Optional<Upgrade> upgradeDetails = upgradeRepository.getAllCommanderUpgrades(dbResponse.getCommanderId());
//Upgrade   upgradesResponse =  upgradeDetails.orElseThrow( ()-> new Exception("Nothing Found on Upgrades Query") );
//logger.info("<<<-----QUICK TEST---->>> size, modelId "  + upgradesResponse.getUpgradeTypeList().size() + upgradesResponse.getUpgradeTypeList().get(0).getModelId()) ;
//
//

//List <Upgrade> upgradeDetails = upgradeRepository.getAllCommanderUpgrades(dbResponse.getCommanderId());
//logger.info("<<< New Query Upgrade_Type  LIST SIZE = >>> "+  upgradeDetails.get(0).getUpgradeTypeList().size() +" " +upgradeDetails.get(0).getUpgradeTypeList());
//for ( Upgrade u : upgradeDetails)
//{
//	 logger.info("<<< New Query Upgrade data using commander_id = ( "+ dbResponse.getCommanderId() +" ) :>>> "+ u.getUpgradeId() + ", "+ u.getCommanderId() + ", "
//     + u.getModelId() + " "+ u.getUpgradeTypeList());
//	 for ( UpgradeType up : u.getUpgradeTypeList())
//	 {
//	 logger.info("<<< New Query UpgradeType data received:>>> ");
//	  logger.info("<<< New Query UpgradeType data:>>> " 
//			 + "modelid   :"  + up.getModelId() + ", " ); 
////			 + "modelName :"  + up.getModelName() + ", "
////			 + "type 	  :"  + up.getType() + ", "
////			 + "presCost  :"  + up.getPrestigeCost() + ", "
////			 + "score     :"  + up.getScore() + ", "
////			  );
//	 }
//}

     	      ////>>>BELOW THE MORE MANUAL WAY OF OBTAINING UPGRADE_TYPE LIST BY COMMANDER 
     	       * >> WOULD GET LIST OF MODEL IDS FROM EXISTING DTO , PUT IN ARRAY, PASS TO QUERY TO UPGRADE_TYPE REPO 
     	       *>> SET THAT RESULT ON COMMANDER DTO 
//    	     List <UpgradeType> upgradeTypesList = upgradeTypeRepository.getById(ucDTO.getUpgradeDTOList().get(0).getModelId());
    	     Integer id =ucDTO.getUpgradeDTOList().get(0).getModelId();
    	     int listLength = ucDTO.getUpgradeDTOList().size();
    	     int []  modelids = new int[ listLength  ];
    	     //get all ids from dto query put in array
    	     for( int i=0; i<listLength; i++) { modelids[i] = ucDTO.getUpgradeDTOList().get(i).getModelId();}
    	     List <UpgradeType> upgradeTypesListB = upgradeTypeRepository.getAllBysId(modelids);
    	         	     
			 logger.info("<<<>>> this is the id passing the query <<<>>> "+ id);
//			  Optional<UpgradeType> optionalUpgradeType = upgradeTypeRepository.findById(1001);
//    	     List <UpgradeType> upgradeTypesList = upgradeTypeRepository.getById(id);
			 List<UpgradeTypeDTO>commanderUpgradesList = new ArrayList<>(); 
			 /// UPDATE THIS TO PASS ARRAY - UPDATE QUERY TO GRAB ALL MODEL IDS IN THAT ARRAY FROM TABLE
			    for (UpgradeType ee : upgradeTypesListB  )
	    	     {
//			    	//>> PRINT OUTS BELOW FOR DEBUG PURPOSES  
//	    	         logger.info("<<< upgrade types model name >>> "+  ee.getModelName());
//	    	         logger.info("<<< the one item in upgrade types >>> "+  ee.getType());
//	    	         logger.info("<<< the one item in upgrade types >>> "+  ee.getPrestigeCost());
//	    	         logger.info("<<< the one item in upgrade types >>> "+  ee.getScore());
//	    	         logger.info("<<< the one item in upgrade types >>> "+  ee.getModelId());
	    	         UpgradeTypeDTO  uptDTO = new UpgradeTypeDTO();
	    	         uptDTO.setModelId(ee.getModelId());
	    	         uptDTO.setModelName(ee.getModelName());
	    	         uptDTO.setPrestigeCost(ee.getPrestigeCost());
	    	         uptDTO.setScore(ee.getScore());
	    	         uptDTO.setType(ee.getType());
	    	        commanderUpgradesList.add(uptDTO);
	    	     }
			    ucDTO.setUpgradeTypeDTOList(commanderUpgradesList);
			 
			 
    	     List <UpgradeType> upgradeTypesList = upgradeTypeRepository.getById(id);
//    	     for (UpgradeType ee : upgradeTypesList  )
//    	     {
//    	         logger.info("<<< upgrade types model name >>> "+  ee.getModelName());
//    	         logger.info("<<< the one item in upgrade types >>> "+  ee.getType());
//    	         logger.info("<<< the one item in upgrade types >>> "+  ee.getPrestigeCost());
//    	         logger.info("<<< the one item in upgrade types >>> "+  ee.getScore());
//    	         logger.info("<<< the one item in upgrade types >>> "+  ee.getModelId());
//    	         UpgradeTypeDTO  uptDTO = new UpgradeTypeDTO();
//    	         uptDTO.setModelId(ee.getModelId());
//    	         uptDTO.setModelName(ee.getModelName());
//    	         uptDTO.setPrestigeCost(ee.getPrestigeCost());
//    	         uptDTO.setScore(ee.getScore());
//    	         uptDTO.setType(ee.getType());
//    	        commanderUpgradesList.add(uptDTO);
//    	     }
    	     /// declare new list
    	  
    	     /// map responses to dto then to dto list and set on current commander dto
//    	     	{
//    	    	  UpgradeTypeDTO upTypesDTO = new UpgradeTypeDTO(upType.getModelId(), upType.getModelName(), upType.getScore(), upType.getPrestigeCost(), upType.getType(), null  );
//    	    	  commanderUpgradesList.add(upTypesDTO);
//    	    	}
//    	     ucDTO.setUpgradeTypeDTOList(commanderUpgradesList);
//    	     
    	     
    	     
    	     logger.info("<<< the one item in upgrade types >>> "+  ucDTO.getUpgradeDTOList());
			 for  (UpgradeDTO  udl : ucDTO.getUpgradeDTOList()) {
//				  List <UpgradeType> upgradeTypesList = upgradeTypeRepository.getById(udl.getModelId());
				  logger.info("<<< the one item in upgrade types >>> "+  udl.getModelId());
			 }

///////////////////////////////////////////
////// ABOVE FROM PLAYER LOGIN  SERVICE 
///////////////////////////////////////////
			
			

*/




