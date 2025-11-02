import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { map, Observable } from 'rxjs';
import { Exposition } from '../models/exposition.model';
import { Salarie } from '../models/salarie.model';

@Injectable({
  providedIn: 'root'
})
export class SalarieService {




  private host: string = '/api/salaries';



  constructor(private http: HttpClient) { }

  // recuperer le profil du salarie ***********************************************************************
  getSalarieByEmail(email: string): Observable<Salarie> {
    const url = `${this.host}/email/${email}`;

    return this.http.get<Salarie>(url).pipe(
      map((salarieDTO: any) => this.mapToSalarieModel(salarieDTO)));

  }

  getSalarieByNumSecu(secu: string): Observable<Salarie> {
    const url = `${this.host}/secu/${secu}`;

    return this.http.get<Salarie>(url).pipe(
      map((salarieDTO: any) => this.mapToSalarieModel(salarieDTO)));

  }

  // consulter les expositions d'un salarié ***********************************************************************
  getSalarieExpositions(salarieId: number): Observable<Exposition[]> {
    const url = `${this.host}/${salarieId}/expositions`;

    return this.http.get<Exposition[]>(url).pipe(
      map((expositionDTOs: any) => expositionDTOs.map((expositionDTO: any) => this.mapToExpositionModel(expositionDTO))));

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



  private mapToSalarieModel(salarieDTO: any): Salarie {

    return {
      id: salarieDTO.id,
      nom: salarieDTO.nom,
      prenom: salarieDTO.prenom,
      numeroSecu: salarieDTO.numeroSecu,
      totalPoints: salarieDTO.totalPoints,
      dateLastBatchUpdate: salarieDTO.dateLastBatchUpdate

    };
  }


}




