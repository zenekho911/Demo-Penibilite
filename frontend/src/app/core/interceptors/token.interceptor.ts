import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';
import { Observable } from 'rxjs';

@Injectable()
export class tokenInterceptor implements HttpInterceptor {
  constructor(private kcService: KeycloakService) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    
      // Cloner la requête  et ajouter l'en-tête 'Authorization'
      const authReq = req.clone({ setHeaders: { Authorization: 'Bearer ' + this.kcService.getKeycloakInstance().token } });
      //console.log("private interceptor: " + authReq.url);
      return next.handle(authReq);
    
  }
}

