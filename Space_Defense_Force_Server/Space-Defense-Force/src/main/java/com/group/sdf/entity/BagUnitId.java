package com.group.sdf.entity;

import java.io.Serializable;
import java.util.Objects;

public class BagUnitId implements Serializable {

    private Integer bagId;
    private Integer unitId;

    public BagUnitId() {}

    public BagUnitId(Integer bagId, Integer unitId) {
        this.bagId = bagId;
        this.unitId = unitId;
    }

    public Integer getBagId() {
        return bagId;
    }

    public void setBagId(Integer bagId) {
        this.bagId = bagId;
    }

    public Integer getUnitId() {
        return unitId;
    }

    public void setUnitId(Integer unitId) {
        this.unitId = unitId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BagUnitId that = (BagUnitId) o;
        return Objects.equals(bagId, that.bagId) && Objects.equals(unitId, that.unitId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bagId, unitId);
    }
}

