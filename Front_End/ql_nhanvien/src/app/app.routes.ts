import { Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { CreateComponent } from './create/create.component';
import { UpdateComponent } from './update/update.component';

export const routes: Routes = [{
    path: '',
    pathMatch: 'full',
    loadComponent: () => {
        return import('./home/home.component').then(m => m.HomeComponent);
        },
    },
    {
        path: 'create',
        loadComponent: () => {
            return import('./create/create.component').then(m => m.CreateComponent);
            },
    },
    {
        path: 'update/:id',
        loadComponent: () => {
            return import('./update/update.component').then(m => m.UpdateComponent);
            },
    },
];
