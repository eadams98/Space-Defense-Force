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
@Table(name = "bag_units")
@IdClass(BagUnitId.class)
public class BagUnit {

    @Id
    @Column(name = "BAG_ID", nullable = false) // Match the name in BagUnitId
    private Integer bagId;

    @Id
    @Column(name = "UNIT_ID", nullable = false) // Match the name in BagUnitId
    private Integer unitId;

    @ManyToOne
    @JoinColumn(name = "BAG_ID", insertable = false, updatable = false)
    private Bag bag;

    @ManyToOne
    @JoinColumn(name = "UNIT_ID", insertable = false, updatable = false)
    private Unit unit;

    // Getters and Setters
    /*public int getBagUnitId() { 
        return bagUnitId;
    }

    public void setBagUnitId(int bagUnitId) {
        this.bagUnitId = bagUnitId;
    }*/

    public Bag getBag() {
        return bag;
    }

    public void setBag(Bag bag) {
        this.bag = bag;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
    
    @Override
    public String toString() {
        return "BagUnit [bagId=" + bagId + ", unitId=" + unitId + ", unit=" + unit + "]";
    }
}

