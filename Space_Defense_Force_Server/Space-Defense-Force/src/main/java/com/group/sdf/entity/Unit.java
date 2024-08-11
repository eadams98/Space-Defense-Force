package com.group.sdf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.group.sdf.dto.UnitDTO;



@Entity
@Table(name="units")
public class Unit {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column	(name = "UNIT_ID"      )
	private Integer unitId;

	@Column	(name = "UNIT_NAME"      )
	private String unitName;
	@Column	(name = "UNIT_HEALTH"      )
	private Integer unitHealth;
	@Column	(name = "UNIT_SHIELD"      )
	private Integer unitShield;
	@Column	(name = "UNIT_DAMAGE"      )
	private Integer unitDamage;

	@Column	(name = "UNIT_XP"      )	//brandon added this DUE TO ERROR ->: Unknown column 'unit0_.unitxp' in 'field list'	
	private Integer unitXP;
	
	@Column (name = "COMMANDER_ID")
	private Integer commanderId;
//	@ManyToOne
//	@JoinColumn(name="COMMANDER_ID") // brandon commented out, what entity class are you joining with here ?
	/* // Eric's Answer: I think it was a miscommunication. It's a commander Id on the table, but we don't need to persist it. Only use it to join to commander
	@ManyToOne
	@JoinColumn(name="COMMANDER_ID")
	 */
//	@Column(name="COMMANDER_ID")
//	private Integer commanderId;

	// Methods
	public static UnitDTO generateUnitDTO(Unit unit) {
		UnitDTO u = new UnitDTO();
		//u.setCommanderId(unit.getCommanderId());
		u.setUnitDamage(unit.getUnitDamage()); 
		u.setUnitHealth(unit.getUnitHealth());
		u.setUnitId(unit.getUnitId());
		u.setUnitName(unit.getUnitName());
		u.setUnitShield(unit.getUnitShield());
		u.setUnitXP(unit.getUnitXP());
		return u;
	}
	
	// Accessor 
	public Integer getUnitId() {
		return unitId;
	}
	public void setUnitId(Integer unitId) {
		this.unitId = unitId;
	}
	public String getUnitName() {
		return unitName;
	}
	public void setUnitName(String unitName) {
		this.unitName = unitName;
	}
	public Integer getUnitHealth() {
		return unitHealth;
	}
	public void setUnitHealth(Integer unitHealth) {
		this.unitHealth = unitHealth;
	}
	public Integer getUnitShield() {
		return unitShield;
	}
	public void setUnitShield(Integer unitShield) {
		this.unitShield = unitShield;
	}
	public Integer getUnitDamage() {
		return unitDamage;
	}
	public void setUnitDamage(Integer unitDamage) {
		this.unitDamage = unitDamage;
	}
	public Integer getUnitXP() {
		return unitXP;
	}
	public void setUnitXP(Integer unitXP) {
		this.unitXP = unitXP;
	}
	
	public Integer getCommanderId() {
		return commanderId;
	}
	public void setCommanderId(Integer commanderId) {
		this.commanderId = commanderId;
	}
	
	@Override
	public String toString() {
		return "Unit [unitId=" + unitId + ", unitName=" + unitName + ", unitHealth=" + unitHealth + ", unitShield="
				+ unitShield + ", unitDamage=" + unitDamage + ", unitXP=" + unitXP + ", commanderId=" + commanderId
				+ "]";
	}

	
	

}
