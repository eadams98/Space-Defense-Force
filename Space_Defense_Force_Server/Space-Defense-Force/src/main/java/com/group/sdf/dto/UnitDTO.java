package com.group.sdf.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.group.sdf.entity.Unit;


public class UnitDTO {
	@NotNull
	private Integer unitId;
	@NotNull
	//pattern allows both strings that begin with capital letters followed by lowercase letters and/or numbers. This pattern can repeat with intermittent spaces.
	//ex. R2D2, Commander1, Battle Cruiser2, Battle Cruiser 2, Intergalactic Star Destroyer accepted. I1nT2, aT1, at at, 2at A5 sq1, not accepted.
	//may need to replace"[\\s]" with "[ ]" or blank space if validations fail. 
	@Pattern(regexp="^[A-Z]+[a-z]*[0-9]*([ ]*([A-Z]+[a-z]*)*[0-9]*)*")
	private String unitName;
	private Integer unitHealth;
	private Integer unitShield;
	private Integer unitDamage;
	private Integer unitXP;
	@NotNull
	private Integer commanderId;
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
	
	//generate Unit method may need a tweak based on available data passed to it
	public static Unit generateUnit(UnitDTO unitDto) {
		Unit u = new Unit();
		//u.setCommanderId(unitDto.getCommanderId());
		u.setUnitDamage(unitDto.getUnitDamage()); 
		u.setUnitHealth(unitDto.getUnitHealth());
		u.setUnitId(unitDto.getUnitId());
		u.setUnitName(unitDto.getUnitName());
		u.setUnitShield(unitDto.getUnitShield());
		u.setUnitXP(unitDto.getUnitXP());
		return u;
	}
	
	//generate Unit method may need a tweak based on available data passed to it
	
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
}
