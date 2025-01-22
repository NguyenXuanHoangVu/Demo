import { bootstrapApplication } from '@angular/platform-browser';
import { importProvidersFrom, APP_INITIALIZER } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { provideHttpClient } from '@angular/common/http';
import { provideRouter } from '@angular/router';
import { KeycloakService, KeycloakAngularModule } from 'keycloak-angular';
import { AppComponent } from './app/app.component';
import { routes } from './app/app-routing.module';
import { appConfig } from '../src/app/app.config';

// Define your Keycloak configuration
const keycloakConfig = {
  url: 'http://localhost:8080/',
  realm: 'Demo',
  clientId: 'frontend-client'
};


const initializeKeycloak = (keycloak: KeycloakService) => {
  return () =>
    keycloak.init({
      config: keycloakConfig,
      initOptions: {
        onLoad: 'login-required',
        checkLoginIframe: false
      }
    });
};

bootstrapApplication(AppComponent, {
  providers: [
    importProvidersFrom(FormsModule, KeycloakAngularModule),
    provideHttpClient(), // Provide HttpClient globally
    provideRouter(routes), // Provide the routes
    KeycloakService,
    {
      provide: APP_INITIALIZER,
      useFactory: initializeKeycloak,
      multi: true,
      deps: [KeycloakService]
    },
    ...appConfig.providers // Include any other app-level configurations
  ],
}).catch((err) => console.error(err));
