package com.selimhorri.pack.pattern.builder;

import com.selimhorri.pack.model.dto.Project;

import java.time.LocalDate;

public class ProjectBuilder {

    private final Project project;

    public ProjectBuilder() {
        this.project = new Project();
    }

    public ProjectBuilder(final Project project) {
        this.project = project;
    }

    public Project build() {
        return this.project;
    }

    public ProjectBuilder projectId(final int projectId) {
        this.project.setProjectId(projectId);
        return this;
    }

    public ProjectBuilder title(final String title) {
        this.project.setTitle(title);
        return this;
    }

    public ProjectBuilder startDate(final LocalDate startDate) {
        this.project.setStartDate(startDate);
        return this;
    }

    public ProjectBuilder endDate(final LocalDate endDate) {
        this.project.setEndDate(endDate);
        return this;
    }

    public ProjectBuilder status(final String status) {
        this.project.setStatus(status);
        return this;
    }



}
