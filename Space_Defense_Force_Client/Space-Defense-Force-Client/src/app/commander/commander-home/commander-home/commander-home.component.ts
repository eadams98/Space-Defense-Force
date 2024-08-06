import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-commander-home',
  templateUrl: './commander-home.component.html',
  styleUrls: ['./commander-home.component.css']
})
export class CommanderHomeComponent implements OnInit {

  
  commander = { "commanderName": "BillyBob", "commanderPrestige": 100, "commanderXP": 0, "unitDTOList": [ { "unitName": "Boom Squad", "unitHealth": 100, "unitShield": 10, "unitDamage": 20, "unitXP": 10, } ], "upgrades": [ { "modelId": 0, "modelName": "Zappy Zap", "prestigeCost": 10, "score": 100,  "type": "long" }, { "modelId": 1, "modelName": "BoomStick9000", "prestigeCost": 20, "score": 150,  "type": "long" } ] }
  /*
  let test = new Commander;
    test.commanderName = "BillyBob";
    test.commanderPrestige = 100;
    test.commanderXP = 0;
    test.unitDTOList = [ { "unitName": "Boom Squad", "unitHealth": 100, "unitShield": 10, "unitDamage": 20, "unitXP": 10, } ]
    test.upgrades = [ { "modelId": 0, "modelName": "Zappy Zap", "prestigeCost": 10, "score": 100,  "type": "long" }, { "modelId": 1, "modelName": "BoomStick9000", "prestigeCost": 20, "score": 150,  "type": "long" } ]
    this.commander = test;
    */

  constructor(private router: Router) { 
    console.log("passed data below");
    console.log(router.getCurrentNavigation().extras.state.data)
    this.commander = router.getCurrentNavigation().extras.state.data
  }

  ngOnInit(): void {
  }

  handleNavigation(pageName: string) {
    console.log(pageName)
    if (pageName === "armory") {
      this.router.navigate(['/', 'armory'], {state: {commander: this.commander}})
    }
  }

}
