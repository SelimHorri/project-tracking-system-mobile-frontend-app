package com.selimhorri.pack.model.dto.custom;

import java.time.LocalDateTime;

public final class ProjectCommit {

    private Integer employeeId;
    private String firstName;
    private String lastName;
    private Integer projectId;
    private String commitDate;
    private String commitEmpDesc;
    private String commitMgrDesc;

    public ProjectCommit() {

    }

    public ProjectCommit(Integer employeeId, String firstName, String lastName, Integer projectId, String commitDate, String commitEmpDesc, String commitMgrDesc) {
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
}
