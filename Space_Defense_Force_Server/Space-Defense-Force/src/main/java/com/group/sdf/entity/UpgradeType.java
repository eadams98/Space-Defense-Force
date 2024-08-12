package com.group.sdf.entity;

import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.group.sdf.dto.Type;
import com.group.sdf.dto.UpgradeDTO;
import com.group.sdf.dto.UpgradeTypeDTO;

@Entity
@Table(name = "upgrade_types")
public class UpgradeType {

	// Point for discussion. I don't believe I need the @OneToMany annotation because:
	// 1) We probably want it so that multiple commanders can have the same Units and Upgrades
	// 2) by adding a Commander class as a variable we are locking it to only 1 commander 
	// 
	// TAKE A LOOK AT ONE TO MANY vs. MANY TO ONE EXAMPLE IN LEX. ONE TO MANY IS THE CLOSEST TO WHAT WE WANT
	
	//private Integer upgradeId;
	
	//Instance Variables
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="MODEL_ID")
	private Integer modelId;
	private String  modelName;
	private Integer score;
	private Integer prestigeCost;
//	@Enumerated(EnumType.STRING)
	@Column(name="UPGRADE_TYPE")
	private String type;
	
	
//	private Type type; // NOTE FROM SELF TO TAZ. MAYBE SET CONTSTAINT SO TYPE CAN ONLY BE short, long, etc
//	@OneToOne(cascade=CascadeType.ALL)//BS comment out for testing 
//	@JoinColumn(name = "model_id")//BS comment out for testing 
//	private Upgrade upgrade;//BS comment out for testing 
	
	public UpgradeType(){} // GENERIC CONSTRUCTOR TO AVOID ERRORS 
	// Constructors

//	public UpgradeType( Integer modelId, String modelName, Integer score, Integer prestigeCost, Type type, Upgrade upgrade) {
	public UpgradeType( Integer modelId, String modelName, Integer score, Integer prestigeCost, String type, Upgrade upgrade) {	
		/*
		 * Note: 	I have made a paramaterized constructor, and not made another parameterless constructor.
		 * 			Therefore you can no longer create an instance of this class with parameterless constructor.
		 * 			You must use this if you wish to make an object of this class
		 */
		
		//this.upgradeId = upgradeId;
		this.modelId = modelId;
		this.modelName = modelName;
		this.score = score;
		this.prestigeCost = prestigeCost;
//		this.type = type;// BS COMMENT OUT THINK THROWING ERROR 
		this.type = type;
//		this.upgrade = upgrade;//BS comment out for testing 
		
	}
	
	///>><<< BS TO TEAM >>> -- THIS WE CAN GET RID OF UPGRADEDTO IN PARMS BELOW OR CREATE A PREPARE CONVERSION METHOD THAT ACCEPTS OBJ
	// Methods //method depends on Join happening above
	public UpgradeTypeDTO prepareUpgradeTypeDTO() {
		UpgradeDTO upgradeDTO = null;// create Upgrade DTO here
		return new UpgradeTypeDTO(this.modelId, this.modelName, this.score, this.prestigeCost, this.type, upgradeDTO);
		//pgradeDTO = upgrade.prepareUpgradeDTO(); // need to check for null probably (unless not null handles that issue for us. Best to do it anyway)
		//return new UpgradeTypeDTO(this.modelId, this.modelName, this.score, this.prestigeCost, this.type, upgradeDTO);
		
	}
	
	// Getters
	public Integer getModelId()			{ return modelId; }
	public String getModelName()		{ return modelName; }
	public Integer getScore()			{ return score; }
	public Integer getPrestigeCost()	{ return prestigeCost; }
	public String getType()				{ return type; }
//	public Type getType()				{ return type; }//BS COMMENT OUT FOR TESTING
//	public Upgrade getUpgrade()					{ return upgrade; }//BS COMMENT OUT FOR TESTING
	
	// Setters
	public void setModelId(Integer modelId) 			{ this.modelId = modelId; }
	public void setModelName(String modelName) 			{ this.modelName = modelName; }
	public void setScore(Integer score) 				{ this.score = score; }
	public void setPrestigeCost(Integer prestigeCost)	{ this.prestigeCost = prestigeCost; }
	public void setType(String type) 					{ this.type = type; }
//	public void setType(Type type) 						{ this.type = type; }//BS COMMENT OUT FOR TESTING
//	public void setUpgrade(Upgrade upgrade)				{ this.upgrade = upgrade; }//BS COMMENT OUT FOR TESTING

	
	/// BS ADDED HASH BELOW TO OMIT UPGRADE ABOVE FOR TESTING 
	@Override
	public int hashCode() {
		return Objects.hash(modelId, modelName, prestigeCost, score, type);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpgradeType other = (UpgradeType) obj;
		return Objects.equals(modelId, other.modelId) && Objects.equals(modelName, other.modelName)
				&& Objects.equals(prestigeCost, other.prestigeCost) && Objects.equals(score, other.score)
				&& type == other.type;
	}

}


/*// BS REMOVE  BELOW FOR TESTING WHEN REMOVE UPGRADE TYPE FROM LIST OF INSTANCE VARIABLES ABOVE 




	@Override
	public int hashCode() {
		return Objects.hash(modelId, modelName, prestigeCost, score, type, upgrade);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UpgradeType other = (UpgradeType) obj;
		return Objects.equals(modelId, other.modelId) && Objects.equals(modelName, other.modelName)
				&& Objects.equals(prestigeCost, other.prestigeCost) && Objects.equals(score, other.score)
				&& type == other.type && Objects.equals(upgrade, other.upgrade);
	}
 */















