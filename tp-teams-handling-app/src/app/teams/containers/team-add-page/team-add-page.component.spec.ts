import { ComponentFixture, TestBed } from '@angular/core/testing';

import { TeamAddPageComponent } from './team-add-page.component';

describe('TeamAddComponent', () => {
  let component: TeamAddPageComponent;
  let fixture: ComponentFixture<TeamAddPageComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ TeamAddPageComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(TeamAddPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
