package com.selimhorri.pack.pattern.builder;

import com.selimhorri.pack.model.dto.Credential;
import com.selimhorri.pack.model.dto.Department;
import com.selimhorri.pack.model.dto.Employee;

public class EmployeeBuilder {

    private final Employee employee;

    public EmployeeBuilder() {
        this.employee = new Employee();
    }

    public EmployeeBuilder(final Employee employee) {
        this.employee = employee;
    }

    public Employee build() {
        return this.employee;
    }

    public EmployeeBuilder employeeId(final int employeeId) {
        this.employee.setEmployeeId(employeeId);
        return this;
    }

    public EmployeeBuilder firstName(final String firstName) {
        this.employee.setFirstName(firstName);
        return this;
    }

    public EmployeeBuilder lastName(final String lastName) {
        this.employee.setLastName(lastName);
        return this;
    }

    public EmployeeBuilder email(final String email) {
        this.employee.setEmail(email);
        return this;
    }

    public EmployeeBuilder phone(final String phone) {
        this.employee.setPhone(phone);
        return this;
    }

    public EmployeeBuilder hiredate(final String hiredate) {
        this.employee.setHiredate(hiredate);
        return this;
    }

    public EmployeeBuilder job(final String job) {
        this.employee.setJob(job);
        return this;
    }

    public EmployeeBuilder salary(final double salary) {
        this.employee.setSalary(salary);
        return this;
    }

    public EmployeeBuilder manager(final Employee manager) {
        this.employee.setManager(manager);
        return this;
    }

    public EmployeeBuilder department(final Department department) {
        this.employee.setDepartment(department);
        return this;
    }

    public EmployeeBuilder credential(final Credential credential) {
        this.employee.setCredential(credential);
        return this;
    }

}
