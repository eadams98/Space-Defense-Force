package com.group.sdf.dto;

import javax.validation.constraints.Pattern;

import com.group.sdf.entity.Upgrade;
import com.group.sdf.entity.UpgradeType;
import com.sun.istack.NotNull;

public class UpgradeTypeDTO {
	
	//Instance Variables
		//@NotNull
		//private Integer upgradeId;
		@NotNull
		private Integer modelId;
		@NotNull
		/*
		 * Pattern for upgradeName
		 * Must start with a capitalized word of length at least 3.
		 * It may be followed with as many words (of any length) seperated by spaces
		 * NOTE: words in this case allow for numbers to be included in the middle or end
		 */
		@Pattern(regexp="([A-Z][a-z0-9]{2,}){1}([ ][A-Z][a-z0-9]*)*")
		private String modelName;
		private Integer score;
		private Integer prestigeCost;
//		private Type type; // NOTE FROM SELF TO TAZ. MAYBE SET CONTSTAINT SO TYPE CAN ONLY BE short, long, etc
		
		private String type;
		private UpgradeDTO upgradeDTO; // DELETE THIS.
		
		public UpgradeTypeDTO(){}
		
		// Constructors
//		public UpgradeTypeDTO( Integer modelId, String modelName, Integer score, Integer prestigeCost, Type type, UpgradeDTO upgradeDTO) {
		public UpgradeTypeDTO( Integer modelId, String modelName, Integer score, Integer prestigeCost, String type, UpgradeDTO upgradeDTO) {
			/*
			 * Note: 	I have made a paramaterized constructor, and not made another parameterless constructor.
			 * 			Therefore you can no longer create an instance of this class with parameterless constructor.
			 * 			You must use this if you wish to make an object of this class
			 */
			
			this.modelId = modelId;
			this.modelName = modelName;
			this.score = score;
			this.prestigeCost = prestigeCost;
			this.type = type;
			this.upgradeDTO = upgradeDTO;
			
			
		}


		// Methods
		public UpgradeType prepareUpgradeType() {
			Upgrade upgrade = null; // create upgrade entity here
			upgrade = upgradeDTO.prepareUpgrade(); // set value here
			return new UpgradeType(this.modelId, this.modelName, this.score, this.prestigeCost, this.type, upgrade);
		}
		
		@Override
		public String toString() {
			return "UpgradeTypeDTO [modelId=" + modelId + ", modelName=" + modelName + ", score=" + score
					+ ", prestigeCost=" + prestigeCost + ", type=" + type + ", upgradeDTO=" + upgradeDTO + "]";
		}

		// Getters
		//public Integer getUpgradeId() 		{ return upgradeId; }
		public Integer getModelId() 		{ return modelId; }
		public String getModeleName() 		{ return modelName; }
		public Integer getScore() 			{ return score; }
		public Integer getPrestigeCost() 	{ return prestigeCost; }
		public String getType() 			{ return type; }
//		public Type getType() 				{ return type; }
		public UpgradeDTO getUpgradeDTO()	{ return upgradeDTO;	}
		
		// Setters
		public void setModelId(Integer modelId) 			{ this.modelId = modelId; }
		public void setModelName(String modelName) 			{ this.modelName = modelName; }
		public void setScore(Integer score) 				{ this.score = score; }
		public void setPrestigeCost(Integer prestigeCost) 	{ this.prestigeCost = prestigeCost; }
		public void setType(String type) 						{ this.type = type; }
//		public void setType(Type type) 						{ this.type = type; }
		public void setUpgradeDTO(UpgradeDTO upgradeDTO)	{ this.upgradeDTO = upgradeDTO; }

}
