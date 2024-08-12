package com.group.sdf.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="encounters")
public class Encounter {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column	(name = "ENEMY_ID")
	private Integer enemyId;
	@Column	(name = "ENEMY_NAME")
	private String enemyName;
	@Column	(name = "ENEMY_HEALTH")
	private Integer enemyHealth;
	@Column	(name = "ENEMY_XP_GIVEN")
	private Integer enemyXPGiven;
	@Column	(name = "ENEMY_SHIELD")
	private Integer enemyShield;
	@Column	(name = "ENEMY_DAMAGE")
	private Integer enemyDamage;
	
	@Column (name = "ENEMY_PRESTIGE_GIVEN")
	private Integer enemyPrestigeGiven;

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

	public void setEnemyPrestigeGiven(Integer enemyPrestigeGiven) {
		this.enemyPrestigeGiven = enemyPrestigeGiven;
	}

	@Override
	public int hashCode() {
		return Objects.hash(enemyDamage, enemyHealth, enemyId, enemyName, enemyPrestigeGiven, enemyShield,
				enemyXPGiven);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Encounter other = (Encounter) obj;
		return Objects.equals(enemyDamage, other.enemyDamage) && Objects.equals(enemyHealth, other.enemyHealth)
				&& Objects.equals(enemyId, other.enemyId) && Objects.equals(enemyName, other.enemyName)
				&& Objects.equals(enemyPrestigeGiven, other.enemyPrestigeGiven)
				&& Objects.equals(enemyShield, other.enemyShield) && Objects.equals(enemyXPGiven, other.enemyXPGiven);
	}
	
	
	
}
