import { Component, Input, OnInit } from '@angular/core';
import { AgentService } from '../../../services/agent.service';
import { Exposition } from '../../../models/exposition.model';
import { Observable, of } from 'rxjs';
import { CommonModule } from '@angular/common';
import { Agent } from '../../../models/agent.model';
import { DeciderExposition } from '../../../models/decider-exposition.model';

@Component({
  selector: 'app-validation-exposition',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './validation-exposition.component.html',
  styleUrl: './validation-exposition.component.css'
})
export class ValidationExpositionComponent implements OnInit {


  @Input() agent!: Agent;
  expoEnAttente: Observable<Exposition[]> = of([]);


  constructor(private agentService: AgentService) { }

  ngOnInit(): void {

    this.expoEnAttente = this.agentService.getExpositionsEnAttente();
  }


  valider(expositionId: number, commentaire: string) {
    const decision: DeciderExposition = {
      agentId: this.agent.id,
      valide: true,
      commentaire: commentaire ? commentaire : 'valide'
    };

    let message = 'Confirmer la décision  ?\n\n' +
      `Exposition-ID : ${expositionId}\n` +
      `Commentaire-Agent : ${decision.commentaire}\n` +
      `Decision-Agent : VALIDE`;

    let confirmValid = confirm(message);

      if (confirmValid) {

        this.agentService.decisionExposition(expositionId, decision).subscribe({
          next: (response) => {
            // mettre à jour la liste
            if(response) this.expoEnAttente = this.agentService.getExpositionsEnAttente();
          },
          error: (err) => console.error('Erreur lors de la sauvegarde de la validation ok :', err)
        });
      }
    
    
   
  }


  rejeter(expositionId: number, commentaire: string) {
    const decision: DeciderExposition = {
      agentId: this.agent.id,
      valide: false,
      commentaire: commentaire ? commentaire : 'invalide'
    };

    let message = 'Confirmer la décision  ?\n\n' +
      `Exposition-ID : ${expositionId}\n` +
      `Commentaire-Agent : ${decision.commentaire}\n` +
      `Decision-Agent : NON VALIDE`;

    let confirmNotValid = confirm(message);
    if (confirmNotValid) {
      
      this.agentService.decisionExposition(expositionId, decision).subscribe({
        next: (response) => {
          // mettre à jour la liste
          if(response) this.expoEnAttente = this.agentService.getExpositionsEnAttente();
        },
        error: (err) => console.error('Erreur lors de la sauvegarde de la validation nok :', err)
      });
    }
    
  }

}

