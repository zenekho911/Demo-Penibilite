import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ValidationExpositionComponent } from './validation-exposition.component';

describe('ValidationExpositionComponent', () => {
  let component: ValidationExpositionComponent;
  let fixture: ComponentFixture<ValidationExpositionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ValidationExpositionComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ValidationExpositionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
