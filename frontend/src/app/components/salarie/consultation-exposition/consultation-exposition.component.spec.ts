import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ConsultationExpositionComponent } from './consultation-exposition.component';

describe('ConsultationExpositionComponent', () => {
  let component: ConsultationExpositionComponent;
  let fixture: ComponentFixture<ConsultationExpositionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ConsultationExpositionComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ConsultationExpositionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
