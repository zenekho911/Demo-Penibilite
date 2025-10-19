import { Injectable } from '@angular/core';
import { Employeur } from '../models/employeur.model';
import { catchError, map, Observable, throwError } from 'rxjs';
import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { DeclarerExposition } from '../models/declarer-exposition.model';
import { Exposition } from '../models/exposition.model';
import { FacteurPenibilite } from '../models/facteur-penibilite.model';

@Injectable({
  providedIn: 'root'
})
export class EmployeurService {

 private host: string = '/api/employeurs';
 
 
 
   constructor(private http: HttpClient) { }
 
   // recuperer le profil de l'agent ***********************************************************************
   getEmployeurByEmail(email: string): Observable<Employeur> {
     const url = `${this.host}/email/${email}`;
 
     return this.http.get<Employeur>(url).pipe(
       map((employeurDTO: any) => this.mapToEmployeurModel(employeurDTO)));
 
   }

    getEmployeurBySiret(siret: string): Observable<Employeur> {
     const url = `${this.host}/siret/${siret}`;
 
     return this.http.get<Employeur>(url).pipe(
       map((employeurDTO: any) => this.mapToEmployeurModel(employeurDTO)));
 
   }


   // lister les facteurs de penibilités ***********************************************************************
   getFacteurs(): Observable<FacteurPenibilite[]> {
    const url = `/api/facteurs`;

    return this.http.get<FacteurPenibilite[]>(url).pipe(
      map((facteurDTOs: any) => facteurDTOs.map((facteurDTO: any) => this.mapToFacteurModel(facteurDTO))));

  }
 
 
   //  declarer une exposition  d'un salarié à un facteur de penibilité ***********************************************************************
   declarationExposition(employeurId: number, declaration: DeclarerExposition): Observable<Exposition> {
     const url = `${this.host}/declarer-exposition/${employeurId}`;
 
     return this.http.post<Exposition>(url, declaration).pipe(catchError(this.handleError));
   }




   private handleError(error: HttpErrorResponse) {
    let errorMessage = 'Une erreur inconnue est survenue';
    if (error.error?.message) {
      errorMessage = error.error.message;
    } else if (error.status === 0) {
      errorMessage = 'Impossible de contacter le serveur.';
    }
    return throwError(() => new Error(errorMessage));
  }
 
 
 


    private mapToFacteurModel(facteurDTO: any): FacteurPenibilite {

      return {
        id: facteurDTO.id,
        code: facteurDTO.code,
        libelle: facteurDTO.libelle,
        description: facteurDTO.description,
        coefficient: facteurDTO.coefficient

      };
    }
 
 
   private mapToEmployeurModel(employeurDTO: any): Employeur {
 
     return {
       id: employeurDTO.id,
       raisonSociale: employeurDTO.raisonSociale,
       siret: employeurDTO.siret,
       adresse: employeurDTO.adresse
 
     };
   }
 
 
 
 
}
