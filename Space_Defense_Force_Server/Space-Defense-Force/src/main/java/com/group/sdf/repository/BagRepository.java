package com.group.sdf.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.group.sdf.entity.Bag;

public interface BagRepository extends JpaRepository<Bag, Integer> {

	@Query("SELECT CASE WHEN COUNT(bu) > 0 THEN true ELSE false END " +
		       "FROM BagUnit bu JOIN bu.bag b " +
		       "WHERE bu.unitId = :unitId AND b.commander.commanderId = :commanderId")
	    boolean existsByUnitIdAndCommanderId(@Param("unitId") int unitId, @Param("commanderId") int commanderId);

	
}
