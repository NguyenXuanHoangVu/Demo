import { Routes } from '@angular/router';
import { AdminComponent } from '../app/components/admin/admin.component';
import { UserComponent } from '../app/components/user/user.component';
import { HomeComponent } from '../app/components/home/home.component';
import { KeycloakAuthGuard, KeycloakService } from 'keycloak-angular';
import { ActivatedRouteSnapshot, RouterStateSnapshot } from '@angular/router';

function keycloakAuthGuard(keycloak: KeycloakService) {
  return {
    canActivate: async (route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<boolean> => {
      return await keycloak.isLoggedIn();
    }
  };
}

export const routes: Routes = [
  {
    path: '', redirectTo: '/home', pathMatch: 'full'
  },
  {
    path: 'home', component: HomeComponent, canActivate: [keycloakAuthGuard]
  },
  {
    path: 'admin', component: AdminComponent, canActivate: [keycloakAuthGuard], data: { roles: ['ADMIN'] }
  },
  {
    path: 'user', component: UserComponent, canActivate: [keycloakAuthGuard], data: { roles: ['USER'] }
  }
];



/*import { Routes } from '@angular/router';
import { LoginComponent } from '../app/components/login/login.component';
import { AdminComponent } from '../app/components/admin/admin.component';
import { authGuard } from './components/auth/auth.guard';

export const routes: Routes = [{
    path: '', redirectTo: '/login', pathMatch: 'full'
    },
    {
    path: 'login', component: LoginComponent
    },
    {
        path: 'admin', component: AdminComponent, canActivate: [authGuard]
    },
    {
    path: '',
    pathMatch: 'full',
    loadComponent: () => {
        return import('../app/components/home/home.component').then(m => m.HomeComponent);
        },
    },
    {
        path: 'create',
        loadComponent: () => {
            return import('../app/components/create/create.component').then(m => m.CreateComponent);
            },
    },
    {
        path: 'update/:id',
        loadComponent: () => {
            return import('../app/components/update/update.component').then(m => m.UpdateComponent);
            },
    },
];*/
