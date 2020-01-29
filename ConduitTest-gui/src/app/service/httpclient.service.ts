import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';

export class Employee {
  constructor(
    public empId: string,
    public name: string,
    public designation: string,
    public salary: string,
  ) { }
}

@Injectable({
  providedIn: 'root'
})
export class HttpClientService {

  EmployeeUrl = 'http://localhost:8080/employees';

  constructor(
    private httpClient: HttpClient
  ) {
  }

  getEmployees() {
    return this.httpClient.get<Employee[]>(this.EmployeeUrl);
  }

  public deleteEmployee(employee) {
    return this.httpClient.delete<Employee>(this.EmployeeUrl + "/" + employee.empId);
  }

  public createEmployee(employee) {
    return this.httpClient.post<Employee>(this.EmployeeUrl, employee);
  }

}