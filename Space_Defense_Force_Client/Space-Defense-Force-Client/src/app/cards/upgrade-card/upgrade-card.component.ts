import { Component, Input, OnInit } from '@angular/core';
import { Upgrade } from 'src/shared/models/upgrade';

@Component({
  selector: 'app-upgrade-card',
  templateUrl: './upgrade-card.component.html',
  styleUrls: ['./upgrade-card.component.css']
})
export class UpgradeCardComponent implements OnInit {

  @Input() // recieve from parent component
  selectedUpgrade: Upgrade; // BillyBob:  "upgradeDTOList": [ { "upgradeId": "10101", "commanderId": 1, "upgradeTypeDTO": { "modelId": 1001, "score": 100, "prestigeCost": 10, "type": "long", "upgradeDTO": null, "modeleName": "Zappy Zap" } }, { "upgradeId": "10102", "commanderId": 1, "upgradeTypeDTO": { "modelId": 1002, "score": 150, "prestigeCost": 20, "type": "long", "upgradeDTO": null, "modeleName": "BoomStick9000" } } ]
  errorMessage: string;
  successMessage: string;
  parent: String;  // ["shop", "bag"] 

  constructor() { }

  ngOnInit(): void {

    let test = new Upgrade; // { "upgradeId": "10101", "commanderId": 1, "upgradeTypeDTO": { "modelId": 1001, "score": 100, "prestigeCost": 10, "type": "long", "upgradeDTO": null, "modeleName": "Zappy Zap" } }
    test.modelName = "Zappy Zap";
    test.prestigeCost = 10;
    test.score = 100; 
    test.type = "long";
    // NEEDS DESCRIPTION ATTACHED (BACKEND FIX)
    this.selectedUpgrade = test;
    this.parent = "bag";
  }

}
