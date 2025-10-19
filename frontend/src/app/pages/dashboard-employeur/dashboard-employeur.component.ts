import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { DeclarationExpositionComponent } from '../../components/employeur/declaration-exposition/declaration-exposition.component';
import { KeycloakService } from 'keycloak-angular';
import { Employeur } from '../../models/employeur.model';
import { EmployeurService } from '../../services/employeur.service';

@Component({
  selector: 'app-dashboard-employeur',
  standalone: true,
  imports: [CommonModule, DeclarationExpositionComponent],
  templateUrl: './dashboard-employeur.component.html',
  styleUrl: './dashboard-employeur.component.css'
})
export class DashboardEmployeurComponent   implements OnInit {

employeur: Employeur | undefined;

  constructor(
    private keycloak: KeycloakService,
    private employeurService: EmployeurService
  ) {}

  ngOnInit(): void {
    
   const token = this.keycloak.getKeycloakInstance().tokenParsed;
    const email: string = token?.['email'];
    this.employeurService.getEmployeurByEmail(email).subscribe({
      next: (employeur) => {
        this.employeur = employeur;
      },
      error: (err) => console.error('Erreur lors de la recuperation du profil database de Employeur :', err)
    });
  
  }

}