import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { EmployeeService } from '../../services/employee.service';
import { Employee } from '../models/employee.model';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-create',
  imports: [FormsModule],
  templateUrl: './create.component.html',
  styleUrl: './create.component.scss'
})
export class CreateComponent {
    employee: Employee = { name: '', email: '' , phoneNumber: '' ,birthday: new Date(),position: '',username: '', password: ''}; // Initialize without id

  constructor(private employeeService: EmployeeService, private router: Router) {}

  onSubmit() {
    this.employeeService.addEmployee(this.employee).subscribe({
      next: () => {
        alert('Employee added successfully!');
        this.router.navigate(['/']); // Navigate back to the home page
      },
      error: (err) => {
        console.error(err);
        alert('Failed to add employee');
      },
    });
  }
}
