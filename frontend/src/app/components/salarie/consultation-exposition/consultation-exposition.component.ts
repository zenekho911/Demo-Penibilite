import { Component, Input, OnInit } from '@angular/core';
import { Salarie } from '../../../models/salarie.model';
import { Observable, of } from 'rxjs';
import { Exposition } from '../../../models/exposition.model';
import { SalarieService } from '../../../services/salarie.service';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-consultation-exposition',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './consultation-exposition.component.html',
  styleUrl: './consultation-exposition.component.css'
})
export class ConsultationExpositionComponent implements OnInit {

  @Input() salarie!: Salarie;
  salarieExpositions: Observable<Exposition[]> = of([]);

   constructor(private salarieService: SalarieService) { }

  ngOnInit(): void {
    this.salarieExpositions = this.salarieService.getSalarieExpositions(this.salarie.id);
  }

}
