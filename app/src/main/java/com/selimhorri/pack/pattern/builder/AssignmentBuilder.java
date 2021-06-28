package com.selimhorri.pack.pattern.builder;

import com.selimhorri.pack.model.dto.Assignment;
import com.selimhorri.pack.model.dto.Employee;
import com.selimhorri.pack.model.dto.Project;
import com.selimhorri.pack.model.id.AssignmentId;

public class AssignmentBuilder {

    private final Assignment assignment;

    public AssignmentBuilder() {
        this.assignment = new Assignment();
    }

    public AssignmentBuilder(final Assignment assignment) {
        this.assignment = assignment;
    }

    public Assignment build() {
        return this.assignment;
    }

    public AssignmentBuilder assignmentId(final AssignmentId assignmentId) {
        this.assignment.setEmployeeId(assignmentId.getEmployeeId());
        this.assignment.setProjectId(assignmentId.getProjectId());
        this.assignment.setCommitDate(assignmentId.getCommitDate());
        return this;
    }

    public AssignmentBuilder commitEmpDesc(final String commitEmpDesc) {
        this.assignment.setCommitEmpDesc(commitEmpDesc);
        return this;
    }

    public AssignmentBuilder commitMgrDesc(final String commitMgrDesc) {
        this.assignment.setCommitMgrDesc(commitMgrDesc);
        return this;
    }

    public AssignmentBuilder employee(final Employee employee) {
        this.assignment.setEmployee(employee);
        return this;
    }

    public AssignmentBuilder project(final Project project) {
        this.assignment.setProject(project);
        return this;
    }



}



