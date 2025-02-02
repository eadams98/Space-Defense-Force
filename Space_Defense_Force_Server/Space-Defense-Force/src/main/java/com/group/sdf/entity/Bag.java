package com.group.sdf.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bag")
public class Bag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bagId;

    @OneToOne
    @JoinColumn(name = "COMMANDER_ID", nullable = false)
    private UnitCommander commander;
    
    @OneToMany(mappedBy = "bag", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BagUnit> bagUnits = new ArrayList<>();

    @OneToMany(mappedBy = "bag", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<BagUpgrade> bagUpgrades = new ArrayList<>();

	public UnitCommander getCommander() {
		return commander;
	}

	public List<BagUnit> getBagUnits() {
		return bagUnits;
	}

	public List<BagUpgrade> getBagUpgrades() {
		return bagUpgrades;
	}

	public void setCommander(UnitCommander commander) {
		this.commander = commander;
	}

	public void setBagUnits(List<BagUnit> bagUnits) {
		this.bagUnits = bagUnits;
	}

	public void setBagUpgrades(List<BagUpgrade> bagUpgrades) {
		this.bagUpgrades = bagUpgrades;
	}

    @Override
    public String toString() {
        return "Bag [bagId=" + bagId + ", bagUnits=" + bagUnits + ", bagUpgrades=" + bagUpgrades + "]";
    }

}

