import { CanActivateFn } from '@angular/router';
import { inject } from '@angular/core';
import { Router } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';

export const authGuard: CanActivateFn = async (route, state) => {

  const keycloak = inject(KeycloakService);
  const router = inject(Router);

  if (await keycloak.isLoggedIn()) {
    const roles = route.data['roles'] as Array<string>;

    if (!roles || roles.length === 0) {
      return true;
    }

    const userRoles = keycloak.getUserRoles();
    if (roles.some(role => userRoles.includes(role))) {
      return true;
    }
  }

  await keycloak.login({ redirectUri: window.location.origin + state.url });
  return false;
};
