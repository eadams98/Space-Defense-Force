import { Component, Input, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Commander } from 'src/shared/models/commander';

@Component({
  selector: 'app-commander-armoury',
  templateUrl: './commander-armoury.component.html',
  styleUrls: ['./commander-armoury.component.css']
})
export class CommanderArmouryComponent implements OnInit {

  @Input()
  commander: Commander;
  errorMessage: string;
  successMessage: string;
  showView: string;

  // Billy bob below
  /*Commander: 
    { 
      "commanderId": 1,
      "commanderName": "BillyBob",
      "commanderPassword": null, 
      "commanderNewPassword": null, 
      "commanderPrestige": 100, 
      "commanderXP": 0, 
      "unitDTOList": [ { "unitId": 1, "unitName": "Boom Squad", "unitHealth": 100, "unitShield": 10, "unitDamage": 20, "unitXP": 10, "commanderId": null } ], 
      "upgradeDTOList": [ { "upgradeId": "10101", "commanderId": 1, "upgradeTypeDTO": { "modelId": 1001, "score": 100, "prestigeCost": 10, "type": "long", "upgradeDTO": null, "modeleName": "Zappy Zap" } }, { "upgradeId": "10102", "commanderId": 1, "upgradeTypeDTO": { "modelId": 1002, "score": 150, "prestigeCost": 20, "type": "long", "upgradeDTO": null, "modeleName": "BoomStick9000" } } ], 
      "upgradeTypeDTOList": null 
    }
  */

  constructor(private router: Router) { 
    
    this.commander = router.getCurrentNavigation().extras.state.commander
    this.commander.upgrades = router.getCurrentNavigation().extras.state.commander.upgradeDTOList
    console.log(this.commander)
  }

  ngOnInit(): void {

    /* upgrade looks like this
    let test = new Upgrade; // { "upgradeId": "10101", "commanderId": 1, "upgradeTypeDTO": { "modelId": 1001, "score": 100, "prestigeCost": 10, "type": "long", "upgradeDTO": null, "modeleName": "Zappy Zap" } }
    test.modelName = "Zappy Zap";
    test.prestigeCost = 10;
    test.score = 100; 
    test.type = "close";
    */

    /*
    let test = new Commander;
    test.commanderName = "BillyBob";
    test.commanderPrestige = 100;
    test.commanderXP = 0;
    test.unitDTOList = null;//[ { "unitName": "Boom Squad", "unitHealth": 100, "unitShield": 10, "unitDamage": 20, "unitXP": 10, } ]
    test.upgrades = null;//[ { "modelId": 0, "modelName": "Zappy Zap", "prestigeCost": 10, "score": 100,  "type": "long" }, { "modelId": 1, "modelName": "BoomStick9000", "prestigeCost": 20, "score": 150,  "type": "long" } ]
    this.commander = test;
    */

    this.showView = "units";
  }

  changeView(view: string) {
    this.showView = view;
    console.log(this.commander.unitDTOList)
    console.log(this.commander.upgrades)

  }

}
