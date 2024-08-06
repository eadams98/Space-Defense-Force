package com.group.sdf.entity;

import java.util.List;
import java.util.Objects; // Eric: Don't think we need this

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "unit_commanders")
public class UnitCommander {

	//BS COMMANDER NEEDS VALIDATION - FOR NEXT RELEASE 
  // Instance Variables 
	@Id // Had the wrong import, was changed to persistence
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column	(name = "COMMANDER_ID"      )	private Integer commanderId;
	@Column	(name = "COMMANDER_NAME"   )	private String 	commanderName; 
	@Column	(name = "COMMANDER_PASSWORD")	private String 	commanderPassword;
	@Column	(name = "COMMANDER_PRESTIGE")	private Integer commanderPrestige;
	@Column (name = "COMMANDER_XP")			private Integer commanderXP;

	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "COMMANDER_ID")		
	private List<Unit> unit; 
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name = "COMMANDER_ID")		
	private List<Upgrade> upgradeList;
	
	@OneToMany(cascade=CascadeType.ALL)	// these 3 lines should be deleted.
	@JoinColumn(name = "MODEL_ID")		// This is impossible due to the fact there is no column on UnitCommander to connect to MODEL_ID of upgradesType table. UpgradeType connects to Upgrade. Upgrade Connects to UnitCommander
	private List<UpgradeType> upgradeTypes;


  // Constructors
    public UnitCommander(){} 
    
  //>> quick mapping of DTO to Entity
	public UnitCommander( Integer commanderId, String commanderName, String commanderPassword, Integer commanderPrestige, Integer commanderXP ) 
	{
		this.commanderId 		= commanderId;	
		this.commanderName 		= commanderName;
		this.commanderPassword 	= commanderPassword;
		this.commanderPrestige 	= commanderPrestige;
		this.commanderXP 		= commanderXP;
	}
	
  
  // Accessors
	public Integer 	getCommanderId() 		{ return commanderId;		} 
	public String 	getCommanderName() 		{ return commanderName;		}  
	public String 	getCommanderPassword() 	{ return commanderPassword;	} 
	public Integer 	getCommanderPrestige() 	{ return commanderPrestige;	}  
	public Integer	getCommanderXP()		{ return commanderXP;		}
	public List<Unit>    getUnit() 			{ return unit;				}
	public List<Upgrade> getUpgradeList() {		return upgradeList;	}
	public List<UpgradeType> getUpgradeTypes(){ return upgradeTypes;	} 

	public void setCommanderId(Integer commanderId      		)	{ this.commanderId		= commanderId;		} 
	public void setCommanderName(String  commanderName    		)	{ this.commanderName	= commanderName;	}  
	public void setCommanderPassword(String  commanderPassword	)	{ this.commanderPassword= commanderPassword;}  
	public void setCommanderPrestige(Integer commanderPrestige	)	{ this.commanderPrestige= commanderPrestige;} 
	public void setCommanderXP(	Integer commanderXP		)			{ this.commanderXP 		= commanderXP;		}
	public void setUnit( List<Unit> unit)							{ this.unit 			= unit; 			}
	public void setUpgradeList(List<Upgrade> upgradeList) {		this.upgradeList = upgradeList;	}
	public void setUpgradeTypes(List<UpgradeType> upgradeTypes  )	{ this.upgradeTypes 	= upgradeTypes;		} 
	
	
	@Override
	public int hashCode() {
		return Objects.hash(commanderId, commanderName, commanderPassword, commanderPrestige, commanderXP, unit,
				upgradeList, upgradeTypes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnitCommander other = (UnitCommander) obj;
		return Objects.equals(commanderId, other.commanderId) && Objects.equals(commanderName, other.commanderName)
				&& Objects.equals(commanderPassword, other.commanderPassword)
				&& Objects.equals(commanderPrestige, other.commanderPrestige)
				&& Objects.equals(commanderXP, other.commanderXP) && Objects.equals(unit, other.unit)
				&& Objects.equals(upgradeList, other.upgradeList) && Objects.equals(upgradeTypes, other.upgradeTypes);
	}
	

	
/////////////////
 }///  end class 
///////////////////


/*//>> BS REMOVE TO TEST 
// Hash & Equals
	@Override
	public int hashCode() {
		return Objects.hash(commanderId, commanderName, commanderPassword, commanderPrestige, commanderXP, unit
				);//,upgradeTypes //>> BS REMOVE TO TEST
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnitCommander other = (UnitCommander) obj;
		return Objects.equals(commanderId, other.commanderId) && Objects.equals(commanderName, other.commanderName)
				&& Objects.equals(commanderPassword, other.commanderPassword)
				&& Objects.equals(commanderPrestige, other.commanderPrestige)
				&& Objects.equals(commanderXP, other.commanderXP) && Objects.equals(unit, other.unit);
//				&& Objects.equals(upgradeTypes, other.upgradeTypes);//>> BS REMOVE TO TEST
	}
	*/
