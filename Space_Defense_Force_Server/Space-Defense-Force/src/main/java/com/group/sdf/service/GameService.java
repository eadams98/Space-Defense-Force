package com.group.sdf.service;

import java.util.List;

import com.group.sdf.dto.BattleResultDTO;

public interface GameService {

	List<BattleResultDTO> battle(int unitId, int encounterId, String token) throws Exception;
	
}
