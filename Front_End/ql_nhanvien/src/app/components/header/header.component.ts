import { Component, signal } from '@angular/core';
import { RouterLink } from '@angular/router';

@Component({
  selector: 'app-header',
  imports: [RouterLink],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {
  title = signal ('Phần mềm quản lý nhân viên');  // dùng signal để gán dữ liệu vào title
  title2 = ' - (Phần mềm quản lý nhân viên - Không dùng signal)';  //gán thẳng dữ liệu vào title2
}
