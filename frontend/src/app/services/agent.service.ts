import { Injectable } from '@angular/core';
import { Exposition } from '../models/exposition.model';
import { catchError, map, Observable, throwError } from 'rxjs';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Agent } from '../models/agent.model';
import { DeciderExposition } from '../models/decider-exposition.model';

@Injectable({
  providedIn: 'root'
})
export class AgentService {


  private host: string = '/api/agents';



  constructor(private http: HttpClient) { }

  // recuperer le profil de l'agent ***********************************************************************
  getAgent(email: string): Observable<Agent> {
    const url = `${this.host}/${email}`;

    return this.http.get<Agent>(url).pipe(
      map((agentDTO: any) => this.mapToAgentModel(agentDTO)));

  }

  // expositions en attente ***********************************************************************
  getExpositionsEnAttente(): Observable<Exposition[]> {
    const url = `${this.host}/expositions/attente`;

    return this.http.get<Exposition[]>(url).pipe(
      map((expositionDTOs: any) => expositionDTOs.map((expositionDTO: any) => this.mapToExpositionModel(expositionDTO))));

  }


  //  decider de valider ou non une exposition en attente ***********************************************************************
  decisionExposition(expositionId: number, decision: DeciderExposition): Observable<Exposition> {
    const url = `${this.host}/valider-exposition/${expositionId}`;



    return this.http.post<Exposition>(url, decision);
  }



  // lancement du batch processing pour les points de penibilités des salariés
   salariePointsBatch(): Observable<string> {
    const url = `${this.host}/update-salaries-points`;

    return this.http.get(url, { responseType: 'text' }).pipe(
        catchError(this.handleError)
      );

  }



   private handleError(error: HttpErrorResponse) {
    let errorMessage = 'Erreur inconnue';
    if (error.error instanceof ErrorEvent) {
      // Erreur côté client
      errorMessage = `Erreur client : ${error.error.message}`;
    } else {
      // Erreur côté serveur
      errorMessage = `Erreur serveur : ${error.status} - ${error.message}`;
    }
    console.error(errorMessage);
    return throwError(() => new Error(errorMessage));
  }







  private mapToExpositionModel(expositionDTO: any): Exposition {

    return {
      id: expositionDTO.id,
      dateDeclaration: expositionDTO.dateDeclaration,
      periode: expositionDTO.periode,
      jourPenibles: expositionDTO.jourPenibles,
      status: expositionDTO.status,
      salarie: expositionDTO.salarie,
      employeur: expositionDTO.employeur,
      facteur: expositionDTO.facteur,
      validePar: expositionDTO.validePar,
      commentaireAgent: expositionDTO.commentaireAgent,
      dateValidation: expositionDTO.dateValidation
    };
  }


  private mapToAgentModel(agentDTO: any): Agent {

    return {
      id: agentDTO.id,
      nomComplet: agentDTO.nomComplet,
      email: agentDTO.email,
      username: agentDTO.username

    };
  }


}



