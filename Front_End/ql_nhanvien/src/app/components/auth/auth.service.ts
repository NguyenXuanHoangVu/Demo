import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject } from '@angular/core';
import { tap } from 'rxjs/operators';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  private keycloakUrl = environment.keycloakUrl;
  private apiUrl = environment.apiUrl;
  constructor() { }
  httpClient = inject(HttpClient);

  login(data: any) {     //, 'Access-Control-Allow-Origin': 'localhost:8080'
    const headers = new HttpHeaders({'Content-Type': 'application/x-www-form-urlencoded'});
    const body = new URLSearchParams();
    body.set('client_id', 'frontend-client'); 
    body.set('username', data.username);
    body.set('password', data.password);+
    body.set('grant_type', 'password');

    return this.httpClient.post(`${this.keycloakUrl}/realms/Demo/protocol/openid-connect/token`, body.toString(), { headers }) //
    .pipe(tap((result: any) => { 
      const userData = { 
        accessToken: result.access_token, 
        refreshToken: result.refresh_token, 
        role: this.extractRole(result.access_token) // Extract role from token 
        }; 
        localStorage.setItem('authUser', JSON.stringify(userData));
      }));
  }

  logout() {
    localStorage.removeItem('authUser');
  }
//
  isLoggedIn() {
    return localStorage.getItem('authUser') !== null;
  }
  private extractRole(token: string): string { 
    // Decode the JWT token and extract the role 
    const payload = JSON.parse(atob(token.split('.')[1])); 
    return payload.realm_access.roles.includes('admin') ? 'ADMIN' : 'USER'; }
}
