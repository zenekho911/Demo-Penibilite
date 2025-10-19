import { Component, OnInit } from '@angular/core';
import { Router, RouterOutlet } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css'
})
export class AppComponent implements OnInit {
title='demo-hamadi';
userName: string = "";
email: string = "";
prenom: string = "";
nom: string = "";
userType:string = "";

  constructor(
    private keycloak: KeycloakService,
    private router: Router
  ) {}

  ngOnInit(): void {
    
    const token = this.keycloak.getKeycloakInstance().tokenParsed;
    this.userName = token?.['preferred_username'];
    this.email = token?.['email'];
    this.prenom = token?.['given_name'];
    this.nom = token?.['family_name'];
    
    const roles = this.keycloak.getUserRoles();
    if (roles.includes('ROLE_AGENT')) {
      this.userType = 'AGENT';
      this.router.navigate(['/dashboard-agent']);
    } else if (roles.includes('ROLE_EMPLOYEUR')) {
      this.userType = 'EMPLOYEUR';
      this.router.navigate(['/dashboard-employeur']);
    } else if (roles.includes('ROLE_SALARIE')) {
      this.userType = 'SALARIE';
      this.router.navigate(['/dashboard-salarie']);
    } else {
      this.userType = '';
      this.router.navigate(['/not-found']); // fallback
    }
  }


  onLogOut(){
    this.keycloak.logout();
    
  }
}