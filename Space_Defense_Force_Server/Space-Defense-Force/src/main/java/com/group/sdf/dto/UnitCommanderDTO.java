package com.group.sdf.dto;

import java.util.ArrayList;
import java.util.List;
//import com.group.sdf.entity.Unit;
//import com.group.sdf.entity.Upgrade;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.group.sdf.entity.Unit;
import com.group.sdf.entity.UnitCommander;
import com.group.sdf.entity.Upgrade;
import com.group.sdf.entity.UpgradeType;

public class UnitCommanderDTO {
	
  // Instance Variables
	private Integer			commanderId;
	// Needs specific pattern match rule
	//@Pattern(regexp="([A-Z][a-z]{2,}){1}([ ][A-Z][a-z]*)*") // Eric, Erig G, Eric G Adams
	@NotNull
	private String			commanderName;
	private String			commanderPassword;
	private String 			commanderNewPassword; 
	private Integer			commanderPrestige;
	private Integer			commanderXP;
	private List<UnitDTO>	unitDTOList;
	private List<UpgradeDTO> upgradeDTOList; 
	private List<UpgradeTypeDTO> upgradeTypeDTOList; // DONT NEED THIS. TO BE DELETED (CHECK UnitCommanderDTO for explanation)
    //private String          token; //>>BS COMMENT:  FOR FUTURE VARIABLE FIELD -->> SECURE CONNECTION 
     
	// Constructors
    public UnitCommanderDTO(){} 

	// Accessors
	public Integer 	getCommanderId() 		{	return commanderId;	}
	public String 	getCommanderName() 		{	return commanderName;}
	public String 	getCommanderPassword() 	{	return commanderPassword;}
	public String 	getCommanderNewPassword(){	return commanderNewPassword;}
	public Integer 	getCommanderPrestige() 	{	return commanderPrestige;	}
	public Integer  getCommanderXP()		{ 	return commanderXP;		}
	public List<UnitDTO> getUnitDTOList() 	{return unitDTOList;	}
	public List<UpgradeDTO> getUpgradeDTOList() {return upgradeDTOList;	}
	public List<UpgradeTypeDTO> getUpgradeTypeDTOList() {return upgradeTypeDTOList;	}

	public void setCommanderId( Integer commanderId){this.commanderId= commanderId;}
	public void setCommanderName( String  commanderName){this.commanderName= commanderName;}
	public void setCommanderPassword( String  commanderPassword){this.commanderPassword= commanderPassword;}
	public void setCommanderNewPassword(String commanderNewPassword){this.commanderNewPassword= commanderNewPassword;}
	public void setCommanderPrestige( Integer commanderPrestige){this.commanderPrestige= commanderPrestige;}
	public void setCommanderXP( Integer commanderXP){this.commanderXP = commanderXP;}
	public void setUnitDTOList(List<UnitDTO> unitDTOList){this.unitDTOList = unitDTOList;}
	public void setUpgradeDTOList(List<UpgradeDTO> upgradeDTOList){this.upgradeDTOList = upgradeDTOList;}
	public void setUpgradeTypeDTOList(List<UpgradeTypeDTO> upgradeTypeDTOList) {this.upgradeTypeDTOList = upgradeTypeDTOList;}
	
	//ASSOCIATED CLASS CREATIONS/CONVERSIONS/CONSTRUCTORS 
	// All New DTO
	public static UnitCommanderDTO  prepareCommanderDTO( UnitCommanderDTO  DTO)
		{
		UnitCommanderDTO newCommanderDTO = new UnitCommanderDTO();
		newCommanderDTO.setCommanderId(DTO.getCommanderId());
		newCommanderDTO.setCommanderName(DTO.getCommanderName());
		newCommanderDTO.setCommanderPassword(DTO.getCommanderPassword());
		newCommanderDTO.setCommanderNewPassword(DTO.getCommanderNewPassword());
		newCommanderDTO.setCommanderPrestige(DTO.getCommanderPrestige());
		newCommanderDTO.setCommanderXP(DTO.getCommanderPrestige());
		newCommanderDTO.setUnitDTOList(DTO.getUnitDTOList());
		newCommanderDTO.setUpgradeDTOList(DTO.getUpgradeDTOList());
		return newCommanderDTO;
		}
	
	//DTO to Entity
	public static UnitCommander DTOtoEntiy(UnitCommanderDTO DTO  )//passwords, units, upgrades not passed this way
		{
		UnitCommander commanderEntity = new UnitCommander();
	    commanderEntity.setCommanderId(DTO.getCommanderId());
	    commanderEntity.setCommanderName(DTO.getCommanderName());
	    commanderEntity.setCommanderPrestige(DTO.getCommanderPrestige());
	    commanderEntity.setCommanderXP(DTO.getCommanderXP());
		return commanderEntity ;
		}
	
	//Entity to DTO 
	public static UnitCommanderDTO entityToDTO( UnitCommander entity )
	{
		
		UnitCommanderDTO  commanderDto = new UnitCommanderDTO();	
		commanderDto.setCommanderId(entity.getCommanderId()); 
		commanderDto.setCommanderName(entity.getCommanderName()); 
		commanderDto.setCommanderPrestige(entity.getCommanderPrestige()); 
		commanderDto.setCommanderXP(entity.getCommanderXP());
		
		List<UnitDTO> unitDTOList = new ArrayList<>();
		for (Unit unit : entity.getUnit())
			unitDTOList.add( Unit.generateUnitDTO(unit) ); // static class method conversion
		commanderDto.setUnitDTOList(unitDTOList);
		
		List<UpgradeDTO> upgradeDTOList = new ArrayList<>();
		for (Upgrade upgrade : entity.getUpgradeList())
			upgradeDTOList.add( upgrade.prepareUpgradeDTO() ); // method
		commanderDto.setUpgradeDTOList(upgradeDTOList);
		
		return commanderDto;
	}
	/*
	public static UnitCommanderDTO   entityToDTO( UnitCommander entity )
		{
		UnitCommanderDTO  commanderDto = new UnitCommanderDTO();	
		commanderDto.setCommanderId(entity.getCommanderId()); 
		commanderDto.setCommanderName(entity.getCommanderName()); 
		commanderDto.setCommanderPrestige(entity.getCommanderPrestige()); 
		commanderDto.setCommanderXP(entity.getCommanderXP()); 
		
		// BUILD LIST OF "UNIT" DTOs
	    // IF WERE TO  CONVERT ENTITY UNIT LIST  to UnitDTO UNIT LIST // DISCUSS WE DO HERE OR IN SERVICE IMPL  
		 List<UnitDTO> commanderUnitDTOs = new ArrayList<>();
		 for (Unit u : entity.getUnit()  )
			{
			 UnitDTO unitDto = UnitDTO.generateUnitDTO(u) ;
			 commanderUnitDTOs.add(unitDto);
			}
	    commanderDto.setUnitDTOList(commanderUnitDTOs);
	    
	    /// BUILD LIST OF "UPGRADE" DTOS 
	    List<UpgradeDTO> upgradeDTOList= new ArrayList<>();
	    for ( Upgrade upgrades : entity.getUpgradeList() )
		    {
		      UpgradeDTO  upgradeDTO = new UpgradeDTO( upgrades.getUpgradeId(),upgrades.getModelId(), upgrades.getCommanderId());
		      
		      upgradeDTOList.add(upgradeDTO);
		    }
	    commanderDto.setUpgradeDTOList(upgradeDTOList);
	    
	    /// BUILD LIST OF "UPGRADE TYPE" DTOs	
	    List<UpgradeTypeDTO>commanderUpgradesList = new ArrayList<>();
	    for ( UpgradeType upType : entity.getUpgradeTypes()) 
	    	{
	    	  UpgradeTypeDTO upTypesDTO = new UpgradeTypeDTO(upType.getModelId(), upType.getModelName(), upType.getScore(), upType.getPrestigeCost(), upType.getType(), null);
//	    	  UpgradeTypeDTO( Integer modelId, String modelName, Integer score, Integer prestigeCost, Type type, UpgradeDTO upgradeDTO)
	    	  commanderUpgradesList.add(upTypesDTO);
	    	}
	     commanderDto.setUpgradeTypeDTOList(commanderUpgradesList);

		return commanderDto;	
		}*/

	
	
	/// TAKE IN An existing CommanderDTO obj, and  LIST OF  "UPGRADE_TYPE" FROM ENTITY AND CONVERT TO UPGRADE_TYPE DTO ON EXISTING COMMANDER
	public static UnitCommanderDTO  entityUpgradeTypeListToDTO( UnitCommanderDTO  ucDTO, List<UpgradeType> upgradeTypeEntityList )
	{
	List<UpgradeTypeDTO>commanderUpgradesList = new ArrayList<>();
	for ( UpgradeType upType : upgradeTypeEntityList) 
		{
		 // current parameterized constructor on UpgradeTypeDTO
         // UpgradeTypeDTO( Integer modelId, String modelName, Integer score, Integer prestigeCost, Type type, UpgradeDTO upgradeDTO)
		  UpgradeTypeDTO upTypesDTO = new UpgradeTypeDTO(upType.getModelId(), upType.getModelName(), upType.getScore(), upType.getPrestigeCost(), upType.getType(), null);
		  commanderUpgradesList.add(upTypesDTO);
		}
	  ucDTO.setUpgradeTypeDTOList(commanderUpgradesList);
	  return ucDTO;
	 }
	
	
	


}////END CLASS 


