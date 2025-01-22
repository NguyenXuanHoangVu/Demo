import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { Employee } from '../../app/components/models/employee.model';

@Injectable({
  providedIn: 'root',
})
export class EmployeeService {
  private baseUrl = 'http://localhost:8081/employee'; // Backend API endpoint
  private userURL = 'http://localhost:8081/employee/user';
  private adminURL = 'http://localhost:8081/employee/admin';
  constructor(private http: HttpClient) {}

  // Get all employees
  getAllEmployees(): Observable<Employee[]> {
    console.log('Fetching employees from backend...');
    return this.http.get<Employee[]>(this.baseUrl).pipe(
      tap((data) => console.log('Received employees:', data)), // Log response
      catchError((error) => {
        console.error('Error fetching employees:', error);
        return throwError(() => new Error('Failed to fetch employees'));
      })
    );
  }

  getAllEmployeesUser(): Observable<Employee[]> {
    console.log('Fetching employees from backend...');
    return this.http.get<Employee[]>(this.userURL).pipe(
      tap((data) => console.log('Received employees:', data)), // Log response
      catchError((error) => {
        console.error('Error fetching employees:', error);
        return throwError(() => new Error('Failed to fetch employees'));
      })
    );
  }

  getAllEmployeesAdmin(): Observable<Employee[]> {
    console.log('Fetching employees from backend...');
    return this.http.get<Employee[]>(this.adminURL).pipe(
      tap((data) => console.log('Received employees:', data)), // Log response
      catchError((error) => {
        console.error('Error fetching employees:', error);
        return throwError(() => new Error('Failed to fetch employees'));
      })
    );
  }

  // Search employees by name
  searchEmployees(query: string): Observable<Employee[]> {
    return this.http.get<Employee[]>(`${this.baseUrl}/search?name=${query}`);
}
  // Add a new employee
  addEmployee(employee: Employee): Observable<Employee> {
    return this.http.post<Employee>(this.baseUrl, employee);
  }
  //Get emplyee by id 
  getEmployeeByIdUser(id: number): Observable<Employee> {
    return this.http.get<Employee>(`${this.userURL}/${id}`);
  }

  getEmployeeByIdAdmin(id: number): Observable<Employee> {
    return this.http.get<Employee>(`${this.adminURL}/${id}`);
  }

  // Update an employee
  updateEmployee(id: number, employee: Employee): Observable<Employee> {
    return this.http.put<Employee>(`${this.baseUrl}/${id}`, employee);
  }

  // Delete an employee
  deleteEmployee(id: number): Observable<void> {
    return this.http.delete<void>(`${this.baseUrl}/${id}`);
  }
}
