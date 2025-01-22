import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { KeycloakAuthGuard, KeycloakService } from 'keycloak-angular';
import { AdminComponent } from '../app/components/admin/admin.component';
import { UserComponent } from '../app/components/user/user.component';
import { HomeComponent } from '../app/components/home/home.component';
//import { LoginComponent } from '../app/components/login/login.component';
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

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}