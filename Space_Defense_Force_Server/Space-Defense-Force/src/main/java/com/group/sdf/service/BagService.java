package com.group.sdf.service;

import java.util.List;

import com.group.sdf.dto.UnitDTO;
import com.group.sdf.dto.UpgradeDTO;

public interface BagService {
    
    public List<UnitDTO> getBagUnits(int pageNo, int pageSize, String token) throws Exception;
    
    public List<UpgradeDTO> getBagUpgrades(int pageNo, int pageSize, String token) throws Exception;

}
