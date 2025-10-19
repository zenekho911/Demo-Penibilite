import { Routes } from '@angular/router';

export default [{ path: '', loadComponent: () => import('./not-found.component').then(m => m.NotFoundComponent)}] as Routes;