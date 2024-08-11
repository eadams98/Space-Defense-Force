package com.group.sdf.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;

import com.group.sdf.entity.Encounter;

public interface EncounterRepository extends CrudRepository<Encounter, Integer> {
	
	ArrayList<Encounter> findByEnemyHealthBetween(Integer low, Integer high);
	
}
