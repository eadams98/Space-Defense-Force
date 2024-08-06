package com.group.sdf.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class EncounterDTO {
	@NotNull
	private Integer enemyId;
	@NotNull
	@Pattern(regexp="([A-Z][a-z0-9]{2,}){1}([ ][A-Z][a-z0-9]*)*")
	private String enemyName;
	private Integer enemyHealth;
	private Integer enemyXPGiven;
	private Integer enemyShield;
	private Integer enemyDamage;
	
	private Integer enemyPrestigeGiven;
	
	public EncounterDTO() {
		super();
	}

	public EncounterDTO(Integer enemyId, String enemyName, Integer enemyHealth, Integer enemyXPGiven,
			Integer enemyShield, Integer enemyDamage, Integer enemyPrestigeGiven) {
		super();
		this.enemyId = enemyId;
		this.enemyName = enemyName;
		this.enemyHealth = enemyHealth;
		this.enemyXPGiven = enemyXPGiven;
		this.enemyShield = enemyShield;
		this.enemyDamage = enemyDamage;
		this.enemyPrestigeGiven = enemyPrestigeGiven;
	}
	
	public Integer getEnemyId() {
		return enemyId;
	}
	public void setEnemyId(Integer enemyId) {
		this.enemyId = enemyId;
	}
	public String getEnemyName() {
		return enemyName;
	}
	public void setEnemyName(String enemyName) {
		this.enemyName = enemyName;
	}
	public Integer getEnemyHealth() {
		return enemyHealth;
	}
	public void setEnemyHealth(Integer enemyHealth) {
		this.enemyHealth = enemyHealth;
	}
	public Integer getEnemyXPGiven() {
		return enemyXPGiven;
	}
	public void setEnemyXPGiven(Integer enemyXPGiven) {
		this.enemyXPGiven = enemyXPGiven;
	}
	public Integer getEnemyShield() {
		return enemyShield;
	}
	public void setEnemyShield(Integer enemyShield) {
		this.enemyShield = enemyShield;
	}
	public Integer getEnemyDamage() {
		return enemyDamage;
	}
	public void setEnemyDamage(Integer enemyDamage) {
		this.enemyDamage = enemyDamage;
	}
	public Integer getEnemyPrestigeGiven() {
		return enemyPrestigeGiven;
	}
	public void setEnemyPrestigeGiven(Integer EnemyPrestigeGiven) {
		this.enemyPrestigeGiven = enemyPrestigeGiven;
	}
	
	
}
