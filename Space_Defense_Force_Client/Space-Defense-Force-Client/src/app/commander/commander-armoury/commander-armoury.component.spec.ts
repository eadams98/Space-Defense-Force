import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommanderArmouryComponent } from './commander-armoury.component';

describe('CommanderArmouryComponent', () => {
  let component: CommanderArmouryComponent;
  let fixture: ComponentFixture<CommanderArmouryComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CommanderArmouryComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CommanderArmouryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
