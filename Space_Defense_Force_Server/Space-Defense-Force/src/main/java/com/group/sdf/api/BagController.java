package com.group.sdf.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.group.sdf.dto.UnitDTO;
import com.group.sdf.dto.UpgradeDTO;
import com.group.sdf.entity.Bag;
import com.group.sdf.service.BagService;

@CrossOrigin
@RestController
@RequestMapping(value="/bag")
public class BagController {
    
    @Autowired
    BagService bagService;
    
    @GetMapping(value = "/units/{pageNo}/{pageSize}")
    public ResponseEntity<List<UnitDTO>> getBagUnits(@PathVariable int pageNo,  @PathVariable int pageSize, @RequestHeader (name="Authorization") String token) throws Exception {
        token = token.split(" ")[1];
        List<UnitDTO> unitsInBag = bagService.getBagUnits(pageNo, pageSize, token);
        
        return new ResponseEntity<>(unitsInBag, HttpStatus.OK);
    }
    
    @GetMapping(value = "/upgrades/{pageNo}/{pageSize}")
    public ResponseEntity<List<UpgradeDTO>> getBagUpgrades(@PathVariable int pageNo,  @PathVariable int pageSize, @RequestHeader (name="Authorization") String token) throws Exception {
        token = token.split(" ")[1];
        List<UpgradeDTO> upgradessInBag = bagService.getBagUpgrades(pageNo, pageSize, token);
        
        return new ResponseEntity<>(upgradessInBag, HttpStatus.OK);
    }

}
