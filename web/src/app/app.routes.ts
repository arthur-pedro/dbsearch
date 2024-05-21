import { Routes } from '@angular/router';
import { DashboardComponent } from './pages/dashboard/dashboard.component';
import { WelcomeComponent } from './pages/welcome/welcome.component';

export const routes: Routes = [
  {
    path: 'auth',
    loadComponent: () =>
      import('./pages/auth/auth.component').then((mod) => mod.AuthComponent),
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    children: [
      {
        path: '',
        redirectTo: 'welcome',
        pathMatch: 'full',
      },
      {
        path: 'welcome',
        loadComponent: () =>
          import('./pages/welcome/welcome.component').then(
            (mod) => mod.WelcomeComponent
          ),
      },
      {
        path: 'connection/:id',
        loadComponent: () =>
          import('./pages/connection/connection.component').then(
            (mod) => mod.ConnectionComponent
          ),
      },
      {
        path: 'connection/:connectionId/database/:id',
        loadComponent: () =>
          import('./pages/database/database.component').then(
            (mod) => mod.DatabaseComponent
          ),
      },
      {
        path: 'connection/:connectionId/schema/:id',
        loadComponent: () =>
          import('./pages/schema/schema.component').then(
            (mod) => mod.SchemaComponent
          ),
      },
      {
        path: 'connection/:connectionId/schema/:schema/table/:table',
        loadComponent: () =>
          import('./pages/table/table.component').then(
            (mod) => mod.TableComponent
          ),
      },
    ],
  },

  { path: '**', redirectTo: 'auth', pathMatch: 'full' },
];
