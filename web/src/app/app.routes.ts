import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    loadComponent: () =>
      import('./pages/home/home.component').then((mod) => mod.HomeComponent),
  },
  {
    path: 'connection/:id',
    loadComponent: () =>
      import('./pages/connection/connection.component').then(
        (mod) => mod.ConnectionComponent
      ),
  },
  {
    path: 'database/:id',
    loadComponent: () =>
      import('./pages/database/database.component').then(
        (mod) => mod.DatabaseComponent
      ),
  },
  {
    path: 'schema/:id',
    loadComponent: () =>
      import('./pages/schema/schema.component').then(
        (mod) => mod.SchemaComponent
      ),
  },
  {
    path: 'schema/:schema/table/:table',
    loadComponent: () =>
      import('./pages/table/table.component').then((mod) => mod.TableComponent),
  },
  // { path: '**', redirectTo: '', pathMatch: 'full' }
];
