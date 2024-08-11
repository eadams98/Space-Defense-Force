package com.group.sdf.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.group.sdf.entity.UpgradeType;


public interface UpgradeTypeRepository extends CrudRepository<UpgradeType, Integer>{
	
	@Query(value="SELECT  * FROM UPGRADE_TYPES WHERE MODEL_ID = :id", nativeQuery=true )
	List<UpgradeType> getById(Integer id );	
	
	//> GET ALL ROWS FROM TABLE FOR A LIST OF MODEL IDS PROVIDED (MODEL ITS COMMANDER OWNS)
	@Query(value="SELECT  * FROM UPGRADE_TYPES WHERE MODEL_ID in :idArray", nativeQuery=true )
	List<UpgradeType> getAllBysId(int [] idArray );	
	
////	@Query("SELECT u FROM UpgradeType u WHERE modelId = :id" )
//	Optional<UpgradeType> findById(Integer id );

}
