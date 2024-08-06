import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CommanderHomeComponent } from './commander-home.component';

describe('CommanderHomeComponent', () => {
  let component: CommanderHomeComponent;
  let fixture: ComponentFixture<CommanderHomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CommanderHomeComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CommanderHomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
