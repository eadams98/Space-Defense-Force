package com.group.sdf.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "bag_upgrades")
@IdClass(BagUpgradeId.class)
public class BagUpgrade {
    @Id
    @Column(name = "BAG_ID", nullable = false) // Match the name in BagUnitId
    private Integer bagId;
    
    @Id
    @Column(name = "UPGRADE_ID", nullable = false) // Match the name in BagUnitId
    private Integer upgradeId;

    @ManyToOne
    @JoinColumn(name = "BAG_ID", insertable = false, updatable = false)
    private Bag bag;

    @ManyToOne
    @JoinColumn(name = "UPGRADE_ID", insertable = false, updatable = false)
    private Upgrade upgrade;

    // Getters and Setters
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

    @Override
    public String toString() {
        return "BagUpgrade [bagId=" + bagId + ", upgradeId=" + upgradeId + ", upgrade=" + upgrade + "]";
    }
}

