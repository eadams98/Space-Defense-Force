package com.group.sdf.dto;

import com.group.sdf.enums.Winner;

public class BattleResultDTO {
    
    private Integer turn;
    private Winner winner;
    
    private Integer unitAttack;
    private Integer unitShield;
    private Integer unitHealth;
    private Integer unitDiceRoll;
    
    private Integer enemyAttack;
    private Integer enemyShield;
    private Integer enemyHealth;
    private Integer enemyDiceRoll;
    
    public Integer getTurn() {
        return turn;
    }
    public void setTurn(Integer turn) {
        this.turn = turn;
    }
    public Winner getWinner() {
        return winner;
    }
    public void setWinner(Winner winner) {
        this.winner = winner;
    }
    public Integer getUnitAttack() {
        return unitAttack;
    }
    public void setUnitAttack(Integer unitAttack) {
        this.unitAttack = unitAttack;
    }
    public Integer getUnitShield() {
        return unitShield;
    }
    public void setUnitShield(Integer unitShield) {
        this.unitShield = unitShield;
    }
    public Integer getUnitHealth() {
        return unitHealth;
    }
    public void setUnitHealth(Integer unitHealth) {
        this.unitHealth = unitHealth;
    }
    public Integer getUnitDiceRoll() {
        return unitDiceRoll;
    }
    public void setUnitDiceRoll(Integer unitDiceRoll) {
        this.unitDiceRoll = unitDiceRoll;
    }
    public Integer getEnemyAttack() {
        return enemyAttack;
    }
    public void setEnemyAttack(Integer enemyAttack) {
        this.enemyAttack = enemyAttack;
    }
    public Integer getEnemyShield() {
        return enemyShield;
    }
    public void setEnemyShield(Integer enemyShield) {
        this.enemyShield = enemyShield;
    }
    public Integer getEnemyHealth() {
        return enemyHealth;
    }
    public void setEnemyHealth(Integer enemyHealth) {
        this.enemyHealth = enemyHealth;
    }
    public Integer getEnemyDiceRoll() {
        return enemyDiceRoll;
    }
    public void setEnemyDiceRoll(Integer enemyDiceRoll) {
        this.enemyDiceRoll = enemyDiceRoll;
    }
    
}
