import { Upgrade } from "./upgrade";
import { Unit } from "./unit";

export class Commander {
    
    "commanderName": string;
    "commanderPassword": string;
    "commanderNewPassword": string;
    "commanderPrestige": number;
    "commanderXP": number;
    "unitDTOList": Unit[]; // rename to units
    "upgrades": Upgrade[];

}