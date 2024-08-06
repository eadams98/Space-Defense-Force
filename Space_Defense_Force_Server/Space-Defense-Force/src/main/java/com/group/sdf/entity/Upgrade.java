package com.group.sdf.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
//import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
//import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.CascadeType;

import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import com.group.sdf.dto.Type;
import com.group.sdf.dto.UpgradeDTO;

import net.bytebuddy.asm.Advice.This;

@Entity
@Table(name = "upgrades")
//@SecondaryTable(name = "upgrade_types")
public class Upgrade {
	//Instance Variables
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="UPGRADE_ID")
	private String upgradeId;
	//@Column(name="MODEL_ID")
	//private Integer modelId;
	@Column(name="COMMANDER_ID")
	private Integer commanderId;
		
    ////BS added Test join to this table to first then to UpgradeTypes
	//  @JoinColumn(name = "address_id", referencedColumnName = "id")
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="MODEL_ID", referencedColumnName = "MODEL_ID")
	private UpgradeType upgradeType;
	
	// Constructors
	public Upgrade(){}//BS GOOD TO HAVE AS DEFAULT CONSTRUCTOR IN EVENT CONSTRUCTORS BELOW DO NOT MATCH INSTANCE VARIABLE SETUP
	
	public Upgrade( String upgradeId, Integer commanderId, UpgradeType upgradeType) {
		this.upgradeId = upgradeId;
		this.commanderId = commanderId;
		this.upgradeType = upgradeType;
	}
	// Upgrade(upgradeId, commanderId, upgradeTypeDTO.prepareUpgradeType());
	
	
	// Methods
	public UpgradeDTO prepareUpgradeDTO() {
		return new UpgradeDTO(upgradeId, commanderId, upgradeType.prepareUpgradeTypeDTO());
	}
	
	
	//BS  Added below 
	//public List<UpgradeType> getUpgradeTypeList() {		return upgradeTypeList;	}
	//public void setUpgradeTypeList(List<UpgradeType> upgradeTypeList) {		this.upgradeTypeList = upgradeTypeList;	}

	
	// Getters
	public String getUpgradeId()			{ return upgradeId; }
	//public Integer getModelId()				{ return modelId; }
	public Integer getCommanderId()			{ return commanderId; }
	public UpgradeType getUpgradeType()		{ return upgradeType; } // Eric

	
	// Setters
	public void setUpgradeId(String upgradeId)			{ this.upgradeId = upgradeId; }
	//public void setModelId(Integer modelId)				{ this.modelId = modelId; }
	public void setCommanderId(Integer commanderId)		{ this.commanderId = commanderId; }
	public void setUpgradeType(UpgradeType upgradeType) { this.upgradeId = upgradeId; }// Eric

	@Override
	public int hashCode() {
		return Objects.hash(commanderId, upgradeId, upgradeType);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Upgrade other = (Upgrade) obj;
		return Objects.equals(commanderId, other.commanderId) && Objects.equals(upgradeId, other.upgradeId)
				&& Objects.equals(upgradeType, other.upgradeType);
	}

	

	
	///>> BS ADDED NEW HASH CODE FOR JOIN TO UPGRADE TYPE TABLE 




}


/*
 * 
 * 
 	@Override
	public int hashCode() {
		return Objects.hash(commanderId, modelId, upgradeId, upgradeTypeList);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Upgrade other = (Upgrade) obj;
		return Objects.equals(commanderId, other.commanderId) && Objects.equals(modelId, other.modelId)
				&& Objects.equals(upgradeId, other.upgradeId) && Objects.equals(upgradeTypeList, other.upgradeTypeList);
	} 
 
	@Override
	public int hashCode() {
		return Objects.hash(commanderId, modelId, upgradeId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Upgrade other = (Upgrade) obj;
		return Objects.equals(commanderId, other.commanderId) && Objects.equals(modelId, other.modelId)
				&& Objects.equals(upgradeId, other.upgradeId);
	}
 
 */



//in SQL we would join from commander, to this table upgrades on commander_id, 
//then join in upgrade_types on the common model_ids found in prior join
/*
  | upgrade_id | model_id | commander_id
    123          m23          c112
    124          m66          c112
    125          m23           
 */
