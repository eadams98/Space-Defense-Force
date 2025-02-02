package com.group.sdf.entity;

import java.io.Serializable;
import java.util.Objects;

public class BagUpgradeId implements Serializable {

    // composite primary key
    private Integer bagId;
    private Integer upgradeId;
    
    public BagUpgradeId(Integer bagId, Integer upgradeId) {
        this.bagId = bagId;
        this.upgradeId = upgradeId;
    }
    
    public Integer getBagId() {
        return bagId;
    }

    public void setBagId(Integer bagId) {
        this.bagId = bagId;
    }

    public Integer getUpgradeId() {
        return upgradeId;
    }

    public void setUnitId(Integer upgradeid) {
        this.upgradeId = upgradeid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BagUpgradeId that = (BagUpgradeId) o;
        return Objects.equals(bagId, that.bagId) && Objects.equals(upgradeId, that.upgradeId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bagId, upgradeId);
    }
    
}
