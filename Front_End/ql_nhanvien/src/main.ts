import { bootstrapApplication } from '@angular/platform-browser';
import { AppComponent } from './app/app.component';
import { provideHttpClient } from '@angular/common/http';
import { provideRouter } from '@angular/router';
import { routes } from './app/app.routes';
import { appConfig } from './app/app.config';
import { importProvidersFrom } from '@angular/core';
import { FormsModule } from '@angular/forms';

// Combine all configurations in one bootstrapApplication call
bootstrapApplication(AppComponent, {
  providers: [
    importProvidersFrom(FormsModule),
    provideHttpClient(), // Provide HttpClient globally
    provideRouter(routes), // Provide the routes
    ...appConfig.providers, // Include any other app-level configurations
  ],
}).catch((err) => console.error(err));