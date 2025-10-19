import { Component, Input, OnInit } from '@angular/core';
import { Employeur } from '../../../models/employeur.model';
import { EmployeurService } from '../../../services/employeur.service';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { DeclarerExposition } from '../../../models/declarer-exposition.model';
import { Observable } from 'rxjs';
import { FacteurPenibilite } from '../../../models/facteur-penibilite.model';

@Component({
  selector: 'app-declaration-exposition',
  standalone: true,
  imports: [CommonModule, ReactiveFormsModule],
  templateUrl: './declaration-exposition.component.html',
  styleUrl: './declaration-exposition.component.css'
})
export class DeclarationExpositionComponent implements OnInit {

  @Input() employeur!: Employeur;
  expositionForm!: FormGroup;
  listeFracteurs!: Observable<FacteurPenibilite[]>;
  errorMessage : string = '';

  constructor(private employeurService: EmployeurService, private fb: FormBuilder) {
    this.expositionForm = this.fb.group({
      salarie: ['', Validators.required],
      facteur: ['', Validators.required],
      periode: ['', Validators.required],
      duree: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.listeFracteurs = this.employeurService.getFacteurs();
  }

 onSubmit(): void {
  if (this.expositionForm.valid) {
    console.log('Données du formulaire :', this.expositionForm.value);

    const { salarie, facteur, periode, duree } = this.expositionForm.value;

    const declaration: DeclarerExposition = {
      salarieNumSecu: salarie as string,
      facteurId: facteur as number,
      periode: periode as string,
      jourPenibles: Number(duree) // conversion vers number
    };

    //si l'employeur choisi bien un facteur
    if(declaration.facteurId !== 0) {
      let message = 'Confirmer la déclaration ?\n\n' +
        `Numéro sécu : ${declaration.salarieNumSecu}\n` +
        `Facteur ID : ${declaration.facteurId}\n` +
        `Période : ${declaration.periode}\n` +
        `Jours pénibles : ${declaration.jourPenibles}`;

      let confirmDeclaration = confirm(message);


      if (confirmDeclaration) {
        this.employeurService.declarationExposition(this.employeur.id, declaration)
        .subscribe({ next: (response) => {
            console.log('Déclaration envoyée avec succès :', response);

            // Réinitialisation propre du formulaire
            this.expositionForm.reset({
              salarie: '',
              facteur: '',
              periode: '',
              duree: ''
            });

            // Remet l'état du formulaire comme "propre" / non touché
            this.expositionForm.markAsPristine();
            this.expositionForm.markAsUntouched();
            this.errorMessage = "";            
          },
          error: (err) => {
            console.error('Erreur lors de la déclaration :', err);
            // Optionnel : afficher un message d'erreur à l'utilisateur
            this.errorMessage = err.message;

          }
        });
      }
      

    }
  } else {
    alert('Formulaire incomplet.');
  }
}





}

