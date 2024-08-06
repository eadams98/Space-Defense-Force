import { TestBed } from '@angular/core/testing';

import { CommanderLoginService } from './commander-login.service';

describe('CommanderLoginService', () => {
  let service: CommanderLoginService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CommanderLoginService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
