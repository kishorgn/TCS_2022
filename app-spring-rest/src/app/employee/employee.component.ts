import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Employee } from '../emloyee';
import { EmployeeService } from '../employee.service';

@Component({
  selector: 'app-employee',
  templateUrl: './employee.component.html',
  styleUrls: ['./employee.component.css']
})
export class EmployeeComponent implements OnInit {

  employees : Employee[] = []
  statusCode = 0;
  requestProcessing = false;
  employeeIdToUpdate = 0;
  processValidation = false;

  // Create form
  employeeForm = new FormGroup({
    name: new FormControl('', Validators.required),
    role: new FormControl('', Validators.required),
    tech: new FormControl('', Validators.required)
  })

  constructor(private employeeService:EmployeeService) { }

  ngOnInit(): void {
    this.getAllEmployees();
  }

  // Fetch all employees
  getAllEmployees(){
    this.employeeService.getAllEmployees()
            .subscribe(
              data=>this.employees = data,
              errorCode => this.statusCode = errorCode
            )
  }

  //Handle create and update employee
  onEmployeeFormSubmit(){
    this.processValidation = true;
    if(this.employeeForm.invalid){
      return ;
    }
    // Form is valid, now perform create or update
    this.preProcessConfigurations();
    let employee = this.employeeForm.value ;
    console.log('Employee from template : '+employee.toString)
    console.log("employeeIdToUpdate : "+this.employeeIdToUpdate)
    if(this.employeeIdToUpdate === 0){
      // Generate employee id then create employee
      console.log(`Saving employee ${employee.id} - ${ employee.name} - ${employee.role} - ${employee.tech} `)
      this.employeeService.createEmployee(employee)
            .subscribe(statusCode => {
              //Expecting success code 201 from server
              this.statusCode = statusCode;
              this.getAllEmployees();
              this.backToCreateEmployee();
            },
              errorCode => this.statusCode = errorCode
            )
    } 
    else{
      // Handle update employee
      employee.id = this.employeeIdToUpdate;
      this.employeeService.updateEmployee(employee)
          .subscribe(statusCode => {
            this.statusCode = 200;
            this.getAllEmployees();
            this.backToCreateEmployee();
          },
            errorCode => this.statusCode = errorCode
          );
    }
  }

  // Load employee by id to edit
  loadEmployeeToEdit(empId: number){
    this.preProcessConfigurations();
    this.employeeService.getEmployeeById(empId)
        .subscribe(employee =>{
          this.employeeIdToUpdate = employee.id;
          this.employeeForm.setValue({
            name:employee.name,
            role:employee.role,
            tech:employee.tech
          });
          this.processValidation = true;
          this.requestProcessing = false;
        },
        errorCode => this.statusCode = errorCode
        );
  }

  // Delete employee
  deleteEmployee(empId: number){
    this.preProcessConfigurations();
    this.employeeService.deleteEmployeeById(empId)
        .subscribe(successCode => {
          this.statusCode = 204;
          this.getAllEmployees();
          this.backToCreateEmployee();
        },
        errorCode => this.statusCode = errorCode
        );
  }

  //Perform preliminary processing configurations
	preProcessConfigurations() {
		this.statusCode = 0;
		this.requestProcessing = true;
	}
	//Go back from update to create
	backToCreateEmployee() {
		this.employeeIdToUpdate = 0;
		this.employeeForm.reset();
		this.processValidation = false;
	}
}
