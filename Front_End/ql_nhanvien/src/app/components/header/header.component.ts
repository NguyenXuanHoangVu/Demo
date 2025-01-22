import { Component, signal, inject } from '@angular/core';
import { Router, RouterLink } from '@angular/router';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-header',
  standalone: true,
  imports: [RouterLink],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {
  title = signal('Phần mềm quản lý nhân viên');  // dùng signal để gán dữ liệu vào title
  title2 = ' - (Phần mềm quản lý nhân viên - Không dùng signal)';  //gán thẳng dữ liệu vào title2
  keycloakService = inject(KeycloakService);
  router = inject(Router);

  public logout() {
    this.keycloakService.logout(window.location.origin);
  }
}