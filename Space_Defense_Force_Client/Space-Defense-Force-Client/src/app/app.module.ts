import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CommanderLoginComponent } from './commander/commander-login/commander-login.component';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommanderHomeComponent } from './commander/commander-home/commander-home/commander-home.component';
import { UnitCardComponent } from './cards/unit-card/unit-card.component';
import { UpgradeCardComponent } from './cards/upgrade-card/upgrade-card.component';
import { CommanderArmouryComponent } from './commander/commander-armoury/commander-armoury.component';

@NgModule({
  declarations: [
    AppComponent,
    CommanderLoginComponent,
    CommanderHomeComponent,
    UnitCardComponent,
    UpgradeCardComponent,
    CommanderArmouryComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    ReactiveFormsModule,
    AppRoutingModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
