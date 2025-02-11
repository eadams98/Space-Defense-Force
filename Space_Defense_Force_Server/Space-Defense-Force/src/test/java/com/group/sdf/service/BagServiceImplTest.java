package com.group.sdf.service;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import java.util.ArrayList;
import java.util.List;

import com.group.sdf.dto.UpgradeDTO;
import com.group.sdf.entity.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.group.sdf.dto.UnitDTO;
import com.group.sdf.repository.BagRepository;
import com.group.sdf.utility.JwtUtils;

import junit.framework.Assert;


@ExtendWith(MockitoExtension.class)
public class BagServiceImplTest {

    private static final Logger logger = LoggerFactory.getLogger(BagServiceImplTest.class);

    @InjectMocks
    BagServiceImpl bagService;

    @Mock
    BagRepository bagRepository;

    @Mock
    PlayerService playerService;

    @Mock
    JwtUtils jwtUtil;

    @Test
    public void getBagUnitsNonEmptyBagUnits() throws Exception {
        int returnedCommanderId = 1;
        Bag bag = new Bag();
        bag.setBagId(1);
        UnitCommander returnedUC = new UnitCommander(bag);

        List<BagUnit> bagUnitList = new ArrayList<>();
        BagUnit bagUnit = new BagUnit();
        Unit unit = new Unit();
        unit.setUnitId(101);
        unit.setUnitName("Warrior");
        unit.setUnitHealth(100);
        unit.setUnitShield(50);
        unit.setUnitDamage(20);
        unit.setUnitXP(200);
        bagUnit.setUnit(unit);
        bagUnitList.add(bagUnit);

        Page<BagUnit> returnedPage = new PageImpl<>(bagUnitList);

        Mockito.when(jwtUtil.getCommanderIdFromToken(anyString())).thenReturn(returnedCommanderId);
        Mockito.when(playerService.getCommander(anyInt())).thenReturn(returnedUC);
        Mockito.when(bagRepository.getUnitsForCommander(anyInt(), any(Pageable.class))).thenReturn(returnedPage);

        List<UnitDTO> results = bagService.getBagUnits(1, 10, "jwt-token");

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("Warrior", results.get(0).getUnitName());
    }

    @Test
    public void getBagUnitsCommanderNotFound() {
        Mockito.when(jwtUtil.getCommanderIdFromToken(anyString())).thenReturn(0);
        Mockito.when(playerService.getCommander(anyInt())).thenReturn(null);

        try {
            bagService.getBagUnits(1, 10, "jwt-token");
            Assert.fail("Expected exception for missing commander");
        } catch (Exception e) {
            Assert.assertEquals("unable to find a commander with the given id", e.getMessage());
        }
    }

    @Test
    public void getBagUnitsEmptyBag() throws Exception {
        int returnedCommanderId = 1;
        Bag bag = new Bag();
        bag.setBagId(1);
        UnitCommander returnedUC = new UnitCommander(bag);

        Page<BagUnit> returnedPage = new PageImpl<>(new ArrayList<>());

        Mockito.when(jwtUtil.getCommanderIdFromToken(anyString())).thenReturn(returnedCommanderId);
        Mockito.when(playerService.getCommander(anyInt())).thenReturn(returnedUC);
        Mockito.when(bagRepository.getUnitsForCommander(anyInt(), any(Pageable.class))).thenReturn(returnedPage);

        List<UnitDTO> results = bagService.getBagUnits(1, 10, "jwt-token");

        Assert.assertNotNull(results);
        Assert.assertTrue(results.isEmpty());
    }

    @Test
    public void getBagUpgradesCommanderNotFound() {
        Mockito.when(jwtUtil.getCommanderIdFromToken(anyString())).thenReturn(0);
        Mockito.when(playerService.getCommander(anyInt())).thenReturn(null);

        try {
            bagService.getBagUpgrades(1, 10, "jwt-token");
            Assert.fail("Expected exception for missing commander");
        } catch (Exception e) {
            Assert.assertEquals("unable to find a commander with the given id", e.getMessage());
        }
    }

    @Test
    public void getBagUpgradesNonEmptyBagUnits() throws Exception {
        int returnedCommanderId = 1;
        Bag bag = new Bag();
        bag.setBagId(1);
        UnitCommander returnedUC = new UnitCommander(bag);

        List<BagUpgrade> bagUpgradeList = new ArrayList<>();

        UpgradeType upgradeType = new UpgradeType();
        upgradeType.setModelId(1);
        upgradeType.setModelName("Model-name");
        upgradeType.setPrestigeCost(100);
        upgradeType.setScore(100);
        upgradeType.setType("type");

        Upgrade upgrade = new Upgrade();
        upgrade.setCommanderId(1);
        upgrade.setUpgradeId(1);
        upgrade.setUpgradeType(upgradeType);

        BagUpgrade bagUpgrade = new BagUpgrade();
        bagUpgrade.setUpgrade(upgrade);
        bagUpgradeList.add(bagUpgrade);

        Page<BagUpgrade> returnedPage = new PageImpl<>(bagUpgradeList);

        Mockito.when(jwtUtil.getCommanderIdFromToken(anyString())).thenReturn(returnedCommanderId);
        Mockito.when(playerService.getCommander(anyInt())).thenReturn(returnedUC);
        Mockito.when(bagRepository.getUpgradesForCommander(anyInt(), any(Pageable.class))).thenReturn(returnedPage);

        List<UpgradeDTO> results = bagService.getBagUpgrades(1, 10, "jwt-token");

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("Model-name", results.get(0).getUpgradeTypeDTO().getModeleName());
    }

    @Test
    public void getBagUpgradesEmptyBag() throws Exception {
        int returnedCommanderId = 1;
        Bag bag = new Bag();
        bag.setBagId(1);
        UnitCommander returnedUC = new UnitCommander(bag);

        Page<BagUpgrade> returnedPage = new PageImpl<>(new ArrayList<>());
        logger.info("test = " + returnedPage);

        Mockito.when(jwtUtil.getCommanderIdFromToken(anyString())).thenReturn(returnedCommanderId);
        Mockito.when(playerService.getCommander(anyInt())).thenReturn(returnedUC);
        Mockito.when(bagRepository.getUpgradesForCommander(anyInt(), any(Pageable.class))).thenReturn(returnedPage);

        List<UpgradeDTO> results = bagService.getBagUpgrades(1, 10, "jwt-token");

        Assert.assertNotNull(results);
        Assert.assertTrue(results.isEmpty());
    }
}
