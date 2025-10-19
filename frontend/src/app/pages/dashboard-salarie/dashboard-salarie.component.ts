import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { ConsultationExpositionComponent } from '../../components/salarie/consultation-exposition/consultation-exposition.component';
import { KeycloakService } from 'keycloak-angular';
import { Salarie } from '../../models/salarie.model';
import { SalarieService } from '../../services/salarie.service';

@Component({
  selector: 'app-dashboard-salarie',
  standalone: true,
  imports: [CommonModule, ConsultationExpositionComponent],
  templateUrl: './dashboard-salarie.component.html',
  styleUrl: './dashboard-salarie.component.css'
})
export class DashboardSalarieComponent   implements OnInit {

salarie: Salarie | undefined;

  constructor(
    private keycloak: KeycloakService,
    private salarieService: SalarieService
  ) {}

  ngOnInit(): void {
    
     const token = this.keycloak.getKeycloakInstance().tokenParsed;
    const email: string = token?.['email'];
    this.salarieService.getSalarieByEmail(email).subscribe({
      next: (salarie) => {
        this.salarie = salarie;
      },
      error: (err) => console.error('Erreur lors de la recuperation du profil database du Salarie :', err)
    });
  
  }

}

