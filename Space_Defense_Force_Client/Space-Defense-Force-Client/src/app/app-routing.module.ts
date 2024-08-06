import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CommanderArmouryComponent } from './commander/commander-armoury/commander-armoury.component';
import { CommanderHomeComponent } from './commander/commander-home/commander-home/commander-home.component';
import { CommanderLoginComponent } from './commander/commander-login/commander-login.component';

const routes: Routes = [
  {path: '', pathMatch: 'full', component: CommanderLoginComponent},
  {path: 'home', pathMatch: 'full', component: CommanderHomeComponent},
  {path: 'armory', pathMatch: 'full', component: CommanderArmouryComponent},
  {path: 'prestige', pathMatch: 'full', component: CommanderArmouryComponent},
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
