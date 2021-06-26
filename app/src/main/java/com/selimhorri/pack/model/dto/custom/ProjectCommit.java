package com.selimhorri.pack.model.dto.custom;

import java.time.LocalDateTime;

public final class ProjectCommit {

    private Integer employeeId;
    private String firstName;
    private String lastName;
    private String projectId;
    private LocalDateTime commitDate;
    private String commitEmpDesc;
    private String commitMgrDesc;

    public ProjectCommit() {

    }

    public ProjectCommit(Integer employeeId, String firstName, String lastName, String projectId, LocalDateTime commitDate, String commitEmpDesc, String commitMgrDesc) {
        this.employeeId = employeeId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.projectId = projectId;
        this.commitDate = commitDate;
        this.commitEmpDesc = commitEmpDesc;
        this.commitMgrDesc = commitMgrDesc;
    }

    @Override
    public String toString() {
        return "ProjectCommit{" +
                "employeeId=" + employeeId +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", projectId='" + projectId + '\'' +
                ", commitDate=" + commitDate +
                ", commitEmpDesc='" + commitEmpDesc + '\'' +
                ", commitMgrDesc='" + commitMgrDesc + '\'' +
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

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public LocalDateTime getCommitDate() {
        return commitDate;
    }

    public void setCommitDate(LocalDateTime commitDate) {
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
}
