package com.group.sdf.service;

import com.group.sdf.repository.CommanderRepository;
import com.group.sdf.repository.UnitRepository;
import com.group.sdf.repository.UpgradeRepository;
import com.group.sdf.repository.UpgradeTypeRepository;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class PlayerServiceImpl {

    @Autowired
    private CommanderRepository commanderRepo;

    @Autowired
    private UnitRepository unitRepository;

    @Autowired
    private UpgradeTypeRepository upgradeTypeRepository;

    @Autowired
    private UpgradeRepository upgradeRepository;

    private static final Logger logger = LoggerFactory.getLogger(PlayerServiceImpl.class);


}
