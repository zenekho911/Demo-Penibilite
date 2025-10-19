import { Agent } from "./agent.model";
import { Employeur } from "./employeur.model";
import { FacteurPenibilite } from "./facteur-penibilite.model";
import { Salarie } from "./salarie.model";

export interface Exposition {
    id: number;
    dateDeclaration: string;
    periode: string;
    jourPenibles: number;        
    status: string;
    salarie: Salarie;
    employeur: Employeur;
    facteur: FacteurPenibilite;
    validePar?: Agent;
    commentaireAgent?: string;
    dateValidation?: string;
   
}