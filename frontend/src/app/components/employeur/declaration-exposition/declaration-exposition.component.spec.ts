import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeclarationExpositionComponent } from './declaration-exposition.component';

describe('DeclarationExpositionComponent', () => {
  let component: DeclarationExpositionComponent;
  let fixture: ComponentFixture<DeclarationExpositionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [DeclarationExpositionComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(DeclarationExpositionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
