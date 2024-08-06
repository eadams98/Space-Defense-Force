package com.group.sdf.repository;


import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.group.sdf.entity.Unit;

public interface UnitRepository extends CrudRepository<Unit, Integer> {
	List<Unit> findByCommanderId(int commanderId);
}
