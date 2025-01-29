package com.group.sdf.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.group.sdf.entity.Unit;

public interface UnitRepository extends JpaRepository<Unit, Integer> {
	List<Unit> findByCommanderId(int commanderId);
}
