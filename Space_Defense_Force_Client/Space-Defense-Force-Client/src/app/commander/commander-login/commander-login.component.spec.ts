import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommanderLoginComponent } from './commander-login.component';

describe('CommanderLoginComponent', () => {
  let component: CommanderLoginComponent;
  let fixture: ComponentFixture<CommanderLoginComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CommanderLoginComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CommanderLoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
