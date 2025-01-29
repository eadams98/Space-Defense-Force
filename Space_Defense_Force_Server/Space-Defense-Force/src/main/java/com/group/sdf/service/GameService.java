package com.group.sdf.service;

import java.util.Map;

public interface GameService {

	Map<Integer, Map<String, String>> battle(int unitId, int encounterId, String token) throws Exception;
	
}
