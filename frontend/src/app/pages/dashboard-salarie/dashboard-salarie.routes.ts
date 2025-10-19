import { Routes } from '@angular/router';

export default [{ path: '', loadComponent: () => import('./dashboard-salarie.component').then(m => m.DashboardSalarieComponent)}] as Routes;