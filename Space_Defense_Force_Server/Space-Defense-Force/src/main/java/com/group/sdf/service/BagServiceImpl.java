package com.group.sdf.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.group.sdf.dto.UnitDTO;
import com.group.sdf.dto.UpgradeDTO;
import com.group.sdf.dto.UpgradeTypeDTO;
import com.group.sdf.entity.Bag;
import com.group.sdf.entity.BagUnit;
import com.group.sdf.entity.BagUpgrade;
import com.group.sdf.entity.Unit;
import com.group.sdf.entity.UnitCommander;
import com.group.sdf.entity.Upgrade;
import com.group.sdf.entity.UpgradeType;
import com.group.sdf.repository.BagRepository;
import com.group.sdf.repository.UnitRepository;
import com.group.sdf.repository.UpgradeRepository;
import com.group.sdf.utility.JwtUtils;

@Service
public class BagServiceImpl implements BagService {
    
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @Autowired
    JwtUtils jwtUtil;
    
    @Autowired
    BagRepository bagRepo;
    
    @Autowired
    PlayerService playerService;

    @Override
    public List<UnitDTO> getBagUnits(int pageNo, int pageSize, String token) throws Exception {
        Integer commanderId = jwtUtil.getCommanderIdFromToken(token);
        UnitCommander commander = playerService.getCommander(commanderId);
        if (commander == null)
            throw new Exception("unable to find a commander with the given id");
        
        int offsetZeroIndex = 1;
        pageNo -= offsetZeroIndex;
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        
        Page<BagUnit> unitPage = bagRepo.getUnitsForCommander(commander.getBag().getBagId(), pageable);

        List<UnitDTO> unitsInBag = new ArrayList<>();
        if (unitPage == null || unitPage.getNumberOfElements() == 0)
            return unitsInBag;
        

        unitPage.forEach(bu -> {
            Unit u = bu.getUnit();
            
            UnitDTO uDTO = new UnitDTO();
            uDTO.setUnitId(u.getUnitId()); //Might not be a use for it like commanderId
            uDTO.setUnitName(u.getUnitName());
            uDTO.setUnitHealth(u.getUnitHealth());
            uDTO.setUnitShield(u.getUnitShield());
            uDTO.setUnitDamage(u.getUnitDamage());
            uDTO.setUnitXP(u.getUnitXP());
            unitsInBag.add(uDTO);
        });
        
        
        return unitsInBag;
    }

    @Override
    public List<UpgradeDTO> getBagUpgrades(int pageNo, int pageSize, String token) throws Exception {
        Integer commanderId = jwtUtil.getCommanderIdFromToken(token);
        UnitCommander commander = playerService.getCommander(commanderId);
        if (commander == null)
            throw new Exception("unable to find a commander with the given id");
        
        int offsetZeroIndex = 1;
        pageNo -= offsetZeroIndex;
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        
        Page<BagUpgrade> upgradePage = bagRepo.getUpgradesForCommander(commander.getBag().getBagId(), pageable);

        List<UpgradeDTO> upgradesInBag = new ArrayList<>();
        if (upgradePage == null || upgradePage.getNumberOfElements() == 0)
            return upgradesInBag;
        

        upgradePage.forEach(bu -> {
            Upgrade u = bu.getUpgrade();
            
            UpgradeDTO uDTO = new UpgradeDTO();
            uDTO.setUpgradeId(u.getUpgradeId());
            
            UpgradeType ut = u.getUpgradeType();
            if (ut != null) {
                UpgradeTypeDTO utDTO = new UpgradeTypeDTO();
                utDTO.setModelId(ut.getModelId());
                utDTO.setModelName(ut.getModelName());
                utDTO.setScore(ut.getScore());
                utDTO.setPrestigeCost(ut.getPrestigeCost());
                utDTO.setType(ut.getType());
                uDTO.setUpgradeTypeDTO(utDTO);
            }
            upgradesInBag.add(uDTO);
        });
        
        
        return upgradesInBag;
    }

}
