package com.group.sdf.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.group.sdf.entity.Bag;
import com.group.sdf.entity.BagUnit;
import com.group.sdf.entity.BagUpgrade;

public interface BagRepository extends JpaRepository<Bag, Integer> {

	@Query("SELECT CASE WHEN COUNT(bu) > 0 THEN true ELSE false END " +
		       "FROM BagUnit bu JOIN bu.bag b " +
		       "WHERE bu.unitId = :unitId AND b.commander.commanderId = :commanderId")
	boolean existsByUnitIdAndCommanderId(@Param("unitId") int unitId, @Param("commanderId") int commanderId);
	
	
	// bagUnits below
	@Query(value="select bu from BagUnit bu where bagId = :commanderBagId", countQuery="select count(bu) from BagUnit bu where bagId = :commanderBagId")
    Page<BagUnit> getUnitsForCommander(int commanderBagId, Pageable pageable);
	
	// bagUpgrades below
	@Query(value="select bu from BagUpgrade bu where bagId = :commanderBagId", countQuery="select count(bu) from BagUpgrade bu where bagId = :commanderBagId")
    Page<BagUpgrade> getUpgradesForCommander(int commanderBagId, Pageable pageable);
}
