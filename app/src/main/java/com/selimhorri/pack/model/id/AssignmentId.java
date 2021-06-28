package com.selimhorri.pack.model.id;

import java.util.Objects;

public class AssignmentId {

    private Integer employeeId;
    private Integer projectId;
    private String commitDate;

    public AssignmentId() {

    }

    public AssignmentId(final Integer employeeId, final Integer projectId, final String commitDate) {
        this.employeeId = employeeId;
        this.projectId = projectId;
        this.commitDate = commitDate;
    }

    @Override
    public String toString() {
        return "AssignmentId{" +
                "employeeId=" + employeeId +
                ", projectId=" + projectId +
                ", commitDate=" + commitDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AssignmentId that = (AssignmentId) o;
        return Objects.equals(employeeId, that.employeeId) &&
                Objects.equals(projectId, that.projectId) &&
                Objects.equals(commitDate, that.commitDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeId, projectId, commitDate);
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



}
