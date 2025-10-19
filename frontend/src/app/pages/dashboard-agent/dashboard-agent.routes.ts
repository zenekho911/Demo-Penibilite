import { Routes } from '@angular/router';

export default [{ path: '', loadComponent: () => import('./dashboard-agent.component').then(m => m.DashboardAgentComponent)}] as Routes;