import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { EmployeeService } from '../../services/employee.service';
import { Employee } from '../../components/models/employee.model';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-update',
  imports: [FormsModule],
  templateUrl: './update.component.html',
  styleUrl: './update.component.scss'
})
export class UpdateComponent implements OnInit{
  employee: Employee = { id: 0, name: '', email: '' , phoneNumber: '' ,birthday: new Date(),position: '',username: '', password: ''};

  constructor(
    private route: ActivatedRoute,
    private employeeService: EmployeeService,
    private router: Router
  ) {}

  ngOnInit() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.employeeService.getEmployeeById(id).subscribe({
      next: (emp) => (this.employee = emp),
      error: (err) => console.error(err),
    });
  }

  onSubmit() {
    console.log('Submitting employee:', this.employee); // Debugging
    this.employeeService.updateEmployee(this.employee.id!, this.employee).subscribe({
      next: () => {
        alert('Employee updated successfully!');
        this.router.navigate(['/']);
      },
      error: (err) => {
        console.error(err);
        alert('Failed to update employee');
      },
    });
  }  
}
