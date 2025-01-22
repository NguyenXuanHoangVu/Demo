/*import { Component } from '@angular/core';
import { inject } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';
import { ApiService } from '../../services/api.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [ReactiveFormsModule, RouterModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  keycloakService = inject(KeycloakService);
  apiService = inject(ApiService);
  router = inject(Router);

  protected loginForm = new FormGroup({
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required])
  });

  onSubmit() { 
    if (this.loginForm.valid) { 
      console.log(this.loginForm.value); 
      this.keycloakService.login({
        redirectUri: window.location.origin
      }).then(() => {
        const authUser = this.keycloakService.getKeycloakInstance().tokenParsed;
        if (authUser && authUser.realm_access && authUser.realm_access.roles.includes('ADMIN')) { 
          this.apiService.getAdminData().subscribe(adminData => {
            console.log(adminData);
            this.router.navigate(['/admin']);
          });
        } else if (authUser && authUser.realm_access && authUser.realm_access.roles.includes('USER')) { 
          this.apiService.getUserData().subscribe(userData => {
            console.log(userData);
            this.router.navigate(['/user']);
          });
        }
      }).catch((error) => {
        console.error('Login failed', error);
      });
    }
  }
}*/