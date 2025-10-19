import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DashboardSalarieComponent } from './dashboard-salarie.component';

describe('DashboardSalarieComponent', () => {
  let component: DashboardSalarieComponent;
  let fixture: ComponentFixture<DashboardSalarieComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DashboardSalarieComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DashboardSalarieComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
