package com.group.sdf.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group.sdf.entity.Encounter;

public interface EncounterRepository extends JpaRepository<Encounter, Integer> {
	
	ArrayList<Encounter> findByEnemyHealthBetween(Integer low, Integer high);
	
	
	
}
