import { Component, OnInit } from '@angular/core'; // Import OnInit
import { CommonModule } from '@angular/common'; // Import CommonModule for *ngFor
import { EmployeeService } from '../../services/employee.service';
import { Employee } from '../models/employee.model';
import { CounterComponent } from '../../components/counter/counter.component';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-user',
  standalone: true,
  imports: [CommonModule, CounterComponent, RouterModule], // Use CommonModule and CounterComponent
  templateUrl: './user.component.html',
  styleUrl: './user.component.scss'
})
export class UserComponent implements OnInit {
  employees: Employee[] = []; // Array to store employee data
  searchResults: Employee[] = []; // Array to store search results

  constructor(private employeeService: EmployeeService) {}

  ngOnInit() {
    this.loadEmployeeList(); // Fetch employees on component initialization
  }

  // Fetch the list of employees from the service
  loadEmployeeList() {
    this.employeeService.getAllEmployeesUser().subscribe(
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

}
