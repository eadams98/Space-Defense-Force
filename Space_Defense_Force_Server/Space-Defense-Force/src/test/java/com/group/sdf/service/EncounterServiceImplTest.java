package com.group.sdf.service;

import com.group.sdf.dto.EncounterDTO;
import com.group.sdf.entity.Encounter;
import com.group.sdf.entity.Unit;
import com.group.sdf.entity.UnitCommander;
import com.group.sdf.repository.CommanderRepository;
import com.group.sdf.repository.EncounterRepository;
import junit.framework.Assert;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;

@ExtendWith(MockitoExtension.class)
public class EncounterServiceImplTest {

    @Mock
    private CommanderRepository commanderRepository;

    @Mock
    private EncounterRepository encounterRepository;

    @InjectMocks
    private EncounterServiceImpl encouterService;

    static Log logger = LogFactory.getLog(EncounterServiceImplTest.class);

    @Test
    public void generateEncountersWithResults() throws Exception {
        Integer CommanderId = 1;

        UnitCommander commander = new UnitCommander();
        ArrayList<Unit> unitList = new ArrayList<>();
        Unit unit = new Unit();
        unit.setUnitId(1);
        unit.setUnitName("unit-name-test");
        unit.setUnitHealth(100);
        unit.setUnitShield(50);
        unit.setUnitDamage(25);
        unitList.add(unit);
        commander.setUnit(unitList);

        ArrayList<Encounter> possibleEncounters = new ArrayList<>();
        Encounter encouter = new Encounter();
        encouter.setEnemyId(1);
        encouter.setEnemyName("encounter-test-name");
        encouter.setEnemyHealth(300);
        encouter.setEnemyDamage(10);
        encouter.setEnemyShield(0);
        encouter.setStaminaCost(10);
        possibleEncounters.add(encouter);

        Mockito.when(commanderRepository.findById(anyInt())).thenReturn(Optional.of(commander));
        Mockito.when(encounterRepository.findByEnemyHealthBetween(anyInt(), anyInt())).thenReturn(possibleEncounters);
        ArrayList<EncounterDTO> results = encouterService.generateEncounters(1);

        Assert.assertNotNull(results);
        Assert.assertEquals(1, results.size());
        Assert.assertEquals("encounter-test-name", results.get(0).getEnemyName());

    }

    @Test
    public void generateEncountersWithoutResults() throws Exception {
        Integer CommanderId = 1;
        UnitCommander commander = new UnitCommander();
        ArrayList<Encounter> possibleEncounters = new ArrayList<>();
        Mockito.when(commanderRepository.findById(anyInt())).thenReturn(Optional.of(commander));
        Mockito.when(encounterRepository.findByEnemyHealthBetween(anyInt(), anyInt())).thenReturn(possibleEncounters);
        ArrayList<EncounterDTO> results = encouterService.generateEncounters(1);

        Assert.assertNotNull(results);
        Assert.assertEquals(0, results.size());
    }

    @Test
    public void generateEncountersButCommanderNotFound() {
        Integer CommanderId = 1;
        UnitCommander commander = new UnitCommander();
        ArrayList<Encounter> possibleEncounters = new ArrayList<>();
        Mockito.when(commanderRepository.findById(anyInt())).thenReturn(Optional.ofNullable(null));

        try {
            ArrayList<EncounterDTO> results = encouterService.generateEncounters(1);
            Assert.fail("Expected exception for missing commander");
        } catch (Exception ex) {
            Assert.assertEquals("unable to find a commander with the given id", ex.getMessage());
        }
    }

    @Test
    public void getEncounterPageWithResults() throws Exception {
        List<Encounter> encounterList = new ArrayList<>();
        Encounter encounter = new Encounter();
        encounter.setEnemyId(1);
        encounter.setEnemyName("encounter-name");
        encounterList.add(encounter);
        Page<Encounter> encounterPage = new PageImpl<>(encounterList);

        Mockito.when(encounterRepository.findAll(any(Pageable.class))).thenReturn(encounterPage);
        List<EncounterDTO> results = encouterService.getEncounterPage(1, 1);

        Assert.assertNotNull(results);
        Assert.assertEquals(encounterList.size(), results.size());
        Assert.assertEquals("encounter-name", results.get(0).getEnemyName());
    }

    @Test
    public void getEncounterPageWithoutResults() {
        List<Encounter> encounterList = new ArrayList<>();
        Page<Encounter> encounterPage = new PageImpl<>(encounterList);

        Mockito.when(encounterRepository.findAll(any(Pageable.class))).thenReturn(encounterPage);
        List<EncounterDTO> results = null;
        try {
            results = encouterService.getEncounterPage(1, 1);
        } catch (Exception ex) {
            Assert.assertEquals("failure, no results in page", ex.getMessage());
        }
    }

    @Test
    public void getNumberOfEncounterForPagesOfSize() {
        Integer numberOfPages = 5;
        List<Encounter> encounterList = new ArrayList<>();
        for(int i = 0; i<numberOfPages; i++)
            encounterList.add(new Encounter());
        Page<Encounter> pages = new PageImpl<>(encounterList, PageRequest.ofSize(1) ,5);

        Mockito.when(encounterRepository.findAll(any(Pageable.class))).thenReturn(pages);
        Integer totalPages = encouterService.getNumberOfEncountersForPagesOfSize(1);

        Assert.assertEquals(numberOfPages, totalPages);
    }

}
