import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { HeaderComponent } from './components/header/header.component';
import { HomeComponent } from "./components/home/home.component";

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, HeaderComponent,HomeComponent ], //HomeComponent
  template: `
    <app-header/>
    <main>
      <router-outlet/>
    </main>
  `,
  styles: [`
    main{
      padding: 16px
    }
    `],
})
export class AppComponent {
  title = 'ql_nhanvien';
}
