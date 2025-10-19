import { Routes } from '@angular/router';
import { AuthGuard } from './core/auth/auth.guard';

// Import des composants standalone
import { DashboardAgentComponent } from './pages/dashboard-agent/dashboard-agent.component';
import { DashboardEmployeurComponent } from './pages/dashboard-employeur/dashboard-employeur.component';
import { DashboardSalarieComponent } from './pages/dashboard-salarie/dashboard-salarie.component';
import { NotFoundComponent } from './pages/not-found/not-found.component';

export const routes: Routes = [
  { path: '', redirectTo: 'not-found', pathMatch: 'full' },
  {
    path: 'not-found',
    component: NotFoundComponent,
    loadChildren: () => import("./pages/not-found/not-found.routes")
  },
  {
    path: 'dashboard-agent',
    component: DashboardAgentComponent,
    canActivate: [AuthGuard],
    data: {
        roles: ['ROLE_AGENT']
    },
    loadChildren: () => import("./pages/dashboard-agent/dashboard-agent.routes")
  },
  {
    path: 'dashboard-employeur',
    component: DashboardEmployeurComponent,
    canActivate: [AuthGuard],
    data: {
        roles: ['ROLE_EMPLOYEUR']
    },
    loadChildren: () => import("./pages/dashboard-employeur/dashboard-employeur.routes")
  },
  {
    path: 'dashboard-salarie',
    component: DashboardSalarieComponent,
    canActivate: [AuthGuard],
    data: {
        roles: ['ROLE_SALARIE']
    },
    loadChildren: () => import("./pages/dashboard-salarie/dashboard-salarie.routes")
  },
   
  { path: '**', redirectTo: 'not-found' }
];

