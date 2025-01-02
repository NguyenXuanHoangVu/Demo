import { Component, signal, Output, EventEmitter } from '@angular/core';
import { EmployeeService } from '../../employee.service';
import { Employee } from '../../models/employee.model';

@Component({
  selector: 'app-counter',
  imports: [],
  templateUrl: './counter.component.html',
  styleUrl: './counter.component.scss'
})
export class CounterComponent {
  counterValue = signal(0);
  searchedValue = signal(0);
  employees: Employee[] = [];
  @Output() searchResults = new EventEmitter<Employee[]>(); // Emit search results

  constructor(private employeeService: EmployeeService) {}

  ngOnInit() {
    this.totalEmployee();
  }

  totalEmployee() {
    this.employeeService.getAllEmployees().subscribe((employees) => {
      this.employees = employees; // Store the employees locally
      this.counterValue.set(employees.length); // Count all employees
    });
  }

  keyPressHandle(event: KeyboardEvent) {
    const searchQuery = (event.target as HTMLInputElement).value; // Search function

    if (searchQuery.trim() === '') {
      // Reset count when search input is empty
      this.searchedValue.set(0);
      this.searchResults.emit([]); // Emit empty results
      return;
    }

    this.employeeService.searchEmployees(searchQuery).subscribe((searchedEmp) => {
      console.log('Search results:', searchedEmp); // Debugging
      this.searchedValue.set(searchedEmp.length);
      this.searchResults.emit(searchedEmp); // Emit search results
    });
  }
}
