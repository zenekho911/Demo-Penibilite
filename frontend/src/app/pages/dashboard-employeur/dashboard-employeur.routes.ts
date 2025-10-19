import { Routes } from '@angular/router';

export default [{ path: '', loadComponent: () => import('./dashboard-employeur.component').then(m => m.DashboardEmployeurComponent)}] as Routes;