package com.group.sdf.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bag_upgrades")
public class BagUpgrade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bagUpgradeId;

    @ManyToOne
    @JoinColumn(name = "BAG_ID", nullable = false)
    private Bag bag;

    @ManyToOne
    @JoinColumn(name = "UPGRADE_ID", nullable = false)
    private Upgrade upgrade;

    // Getters and Setters
    public int getBagUpgradeId() {
        return bagUpgradeId;
    }

    public void setBagUpgradeId(int bagUpgradeId) {
        this.bagUpgradeId = bagUpgradeId;
    }

    public Bag getBag() {
        return bag;
    }

    public void setBag(Bag bag) {
        this.bag = bag;
    }

    public Upgrade getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(Upgrade upgrade) {
        this.upgrade = upgrade;
    }
}

