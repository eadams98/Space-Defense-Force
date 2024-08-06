import { Component, Input, OnInit } from '@angular/core';
import { Unit } from 'src/shared/models/unit';

@Component({
  selector: 'app-unit-card',
  templateUrl: './unit-card.component.html',
  styleUrls: ['./unit-card.component.css']
})
export class UnitCardComponent implements OnInit {

  @Input() // recieve from parent component
  selectedUnit: Unit; // BillyBob: {"unitId":1,"unitName":"Boom Squad","unitHealth":100,"unitShield":10,"unitDamage":20,"unitXP":10,"commanderId":null}
  errorMessage: string;
  successMessage: string;
  parent: String;  // ["shop", "bag"] 

  constructor() { }

  ngOnInit(): void {
    let testUnit = new Unit;
    testUnit.unitName = "Boom Squad";
    testUnit.unitDamage = 10;
    testUnit.unitHealth = 100;
    testUnit.unitShield = 100;
    testUnit.unitXP = 10;
    this.selectedUnit = testUnit;
    //this.parent = "bag"
  }

}
