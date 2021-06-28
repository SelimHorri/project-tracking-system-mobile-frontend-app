package com.selimhorri.pack.model.dto;

import java.lang.String;

public final class Employee {

    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String hiredate;
    private String job;
    private Double salary;
    private Employee manager;
    private Department department;
    private Credential credential;

    public Employee() {

    }

    public Employee(String firstName, String lastName, String email, String phone, String hiredate, String job, Double salary, Employee manager, Department department, Credential userCredential) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.hiredate = hiredate;
        this.job = job;
        this.salary = salary;
        this.manager = manager;
        this.department = department;
        this.credential = userCredential;
    }

    public Employee(Integer employeeId, String firstName, String lastName, String email, String phone, String hiredate, String job, Double salary, Employee manager, Department department, Credential userCredential) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.hiredate = hiredate;
        this.job = job;
        this.salary = salary;
        this.manager = manager;
        this.department = department;
        this.credential = userCredential;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", hiredate=" + hiredate +
                ", job='" + job + '\'' +
                ", salary=" + salary +
                ", managerId=" + manager.getEmployeeId() +
                ", departmentId=" + department.getDepartmentId() +
                ", credentialId=" + credential.getCredentialId() +
                '}';
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getHiredate() {
        return hiredate;
    }

    public void setHiredate(String hiredate) {
        this.hiredate = hiredate;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Double getSalary() {
        return salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Credential getCredential() {
        return credential;
    }

    public void setCredential(Credential credential) {
        this.credential = credential;
    }
}
