package com.selimhorri.pack.model.dto;

public class Assignment {

    private Integer employeeId;
    private Integer projectId;
    private String commitDate;
    private String commitEmpDesc;
    private String commitMgrDesc;
    private Employee employee;
    private Project project;

    public Assignment() {

    }

    public Assignment(Integer employeeId, Integer projectId, String commitDate, String commitEmpDesc, String commitMgrDesc, Employee employee, Project project) {
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.commitDate = commitDate;
        this.commitEmpDesc = commitEmpDesc;
        this.commitMgrDesc = commitMgrDesc;
        this.employee = employee;
        this.project = project;
    }

    @Override
    public String toString() {
        return "Assignment{" +
                "employeeId=" + employeeId +
                ", projectId=" + projectId +
                ", commitDate=" + commitDate +
                ", commitEmpDesc='" + commitEmpDesc + '\'' +
                ", commitMgrDesc='" + commitMgrDesc + '\'' +
                // ", employeeId=" + employee.getEmployeeId() +
                // ", projectId=" + project.getProjectId() +
                '}';
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(String commitDate) {
        this.commitDate = commitDate;
    }

    public String getCommitEmpDesc() {
        return commitEmpDesc;
    }

    public void setCommitEmpDesc(String commitEmpDesc) {
        this.commitEmpDesc = commitEmpDesc;
    }

    public String getCommitMgrDesc() {
        return commitMgrDesc;
    }

    public void setCommitMgrDesc(String commitMgrDesc) {
        this.commitMgrDesc = commitMgrDesc;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
