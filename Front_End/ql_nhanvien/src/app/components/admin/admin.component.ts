import { Component, inject, OnInit } from '@angular/core';
import { AuthService } from '../auth/auth.service';
import { Router } from '@angular/router';
import { CommonModule } from '@angular/common'; // Import CommonModule for *ngFor
import { EmployeeService } from '../../services/employee.service';
import { Employee } from '../models/employee.model';
import { CounterComponent } from '../../components/counter/counter.component';
import { RouterModule } from '@angular/router';
@Component({
  selector: 'app-admin',
  standalone: true,
  imports: [CommonModule, CounterComponent, RouterModule], // Use CommonModule and CounterComponent
  templateUrl: './admin.component.html',
  styleUrl: './admin.component.scss'
})

export class AdminComponent implements OnInit{
  authService = inject(AuthService);
  router = inject(Router);
  employees: Employee[] = []; // Array to store employee data
  searchResults: Employee[] = []; // Array to store search results

  constructor(private employeeService: EmployeeService) {}

  ngOnInit() {
    this.loadEmployeeList(); // Fetch employees on component initialization
  }

  // Fetch the list of employees from the service
  loadEmployeeList() {
    this.employeeService.getAllEmployeesAdmin().subscribe(
      (employees) => {
        this.employees = employees; // Update the local array with fetched data
      },
      (error) => {
        console.error('Failed to load employees:', error); // Log errors if any
      }
    );
  }

  updateSearchResults(results: Employee[]) {
    console.log('Received search results:', results); // Debugging
    this.searchResults = results; // Update the search results array
  }

  confirmDelete(Id: number) {
    const confirmation = confirm("Xóa nhân viên này?");
    if (confirmation) {
      this.deleteEmployee(Id);
    }
  }

  deleteEmployee(Id: number) {
    this.employeeService.deleteEmployee(Id).subscribe(
      () => {
        // Update the employee list after deletion
        this.employees = this.employees.filter(emp => emp.id !== Id);
        alert('Employee deleted successfully.');
      },
      (error) => {
        console.error('Error deleting employee:', error);
        alert('Failed to delete the employee. Please try again.');
      }
    );
  }
  public logout(){
    this.authService.logout();
    this.router.navigate(['/login']);
  }
}