package com.group.sdf.dto;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Pattern;

import com.group.sdf.entity.Upgrade;
import com.group.sdf.entity.UpgradeType;
import com.sun.istack.NotNull;

public class UpgradeDTO {
	
	//Instance Variables
	@NotNull
	private String upgradeId;
	//@NotNull
	//private Integer modelId;
	private Integer commanderId;
	private UpgradeTypeDTO upgradeTypeDTO;
	
	
	// Constructors
	public UpgradeDTO( String upgradeId, Integer commanderId, UpgradeTypeDTO upgradeTypeDTO) {
		this.upgradeId = upgradeId;
		//this.modelId = modelId;
		this.commanderId = commanderId;
		this.upgradeTypeDTO = upgradeTypeDTO;
		
	}
	
	// Methods
	public Upgrade prepareUpgrade() {
		return new Upgrade(upgradeId, commanderId, upgradeTypeDTO.prepareUpgradeType());
	}
	
	// Getters
	public String getUpgradeId()			{ return upgradeId; }
	//public Integer getModelId()				{ return modelId; }
	public Integer getCommanderId()			{ return commanderId; }
	public UpgradeTypeDTO getUpgradeTypeDTO()		{ return upgradeTypeDTO; }

	
	// Setters
	public void setUpgradeId(String upgradeId)						{ this.upgradeId = upgradeId; }
	//public void getModelId(Integer modelId)						{ this.modelId = modelId; }
	public void setCommanderId(Integer commanderId)					{ this.commanderId = commanderId; }
	public void setUpgradeTypeDTO(UpgradeTypeDTO upgradeTypeDTO)	{ this.upgradeTypeDTO = upgradeTypeDTO; }

	@Override
	public String toString() {
		return "UpgradeDTO [upgradeId=" + upgradeId + ", commanderId=" + commanderId + ", upgradeTypeDTO="
				+ upgradeTypeDTO + "]";
	}

	
	
	


	
	
	
}
