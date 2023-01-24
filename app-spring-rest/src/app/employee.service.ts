import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, tap, throwError } from 'rxjs';
import { Employee } from './emloyee';

@Injectable({
  providedIn: 'root'
})
export class EmployeeService {

  employeeUrl = "http://localhost:8080/employees"

  constructor(private http:HttpClient) { }

  //Fetch all employees
  getAllEmployees(): Observable<Employee[]>{
    return this.http.get<Employee[]> (this.employeeUrl).pipe(
      tap(employees => console.log("Number of employees : "+employees.length)),
      catchError(this.handleError)
    )
  }

  // Create an employee
  createEmployee(employee : Employee ) : Observable<number>{
    let httpHeaders = new HttpHeaders({
      'Content-Type':  'application/json'
    })
    console.log('Saving employee '+employee.name+' '+employee.role+" "+employee.tech)
    return this.http.post<Employee>(this.employeeUrl+"/", employee ,{
      headers: httpHeaders,
      observe : 'response'
    }).pipe(
      map(res => res.status),
      catchError(this.handleError)
    );

  }

   // Update employee
   updateEmployee(employee: Employee) : Observable<number>{
    let httpHeaders = new HttpHeaders({
      'Content-Type':'application/json'
    });
    console.log("Modifying employee : "+employee)
    return this.http.put<Employee>(this.employeeUrl+"/"+employee.id, employee, {
      headers: httpHeaders,
      observe : 'response'
    })
      .pipe(
        map(res=>res.status),
        catchError(this.handleError)
      )
  }


  // fetch employee by id
  getEmployeeById(employeeId: number): Observable<Employee>{
    return this.http.get<Employee> (this.employeeUrl+"/"+employeeId)
        .pipe(
          tap(employee => console.log("Finding : "+employee.id+" "+employee.name)),
          catchError(this.handleError)
        )
  }

 
  // Delete Employee
  deleteEmployeeById(empId : number): Observable<number>{
    return this.http.delete<number>(this.employeeUrl+"/"+empId).pipe(
      tap(status => console.log("status : "+status.toString)),
      catchError(this.handleError)
    )
  }



  private handleError(error: any) {
    console.error("Error : "+error);
    return throwError(error);
  }
}
