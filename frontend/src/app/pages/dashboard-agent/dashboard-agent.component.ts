import { Component, OnInit } from '@angular/core';
import { ValidationExpositionComponent } from '../../components/agent/validation-exposition/validation-exposition.component';
import { CommonModule } from '@angular/common';
import { KeycloakService } from 'keycloak-angular';
import { AgentService } from '../../services/agent.service';
import { Agent } from '../../models/agent.model';

@Component({
  selector: 'app-dashboard-agent',
  standalone: true,
  imports: [CommonModule, ValidationExpositionComponent],
  templateUrl: './dashboard-agent.component.html',
  styleUrl: './dashboard-agent.component.css'
})
export class DashboardAgentComponent  implements OnInit {

agent: Agent | undefined;

  constructor(
    private keycloak: KeycloakService,
    private agentService: AgentService
  ) {}

  ngOnInit(): void {
    
    const token = this.keycloak.getKeycloakInstance().tokenParsed;
    const email: string = token?.['email'];
    this.agentService.getAgent(email).subscribe({
      next: (agent) => {
        this.agent = agent;
      },
      error: (err) => console.error('Erreur lors de la recuperation du profil database de Agent :', err)
    });

  
  }

}