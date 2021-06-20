package com.selimhorri.pack.model.dto.custom;

public class EmployeeProjectData {

    private String title;
    private String startDate;
    private String endDate;
    private Integer projectId;
    private String status;

    public EmployeeProjectData() {

    }

    public EmployeeProjectData(String title, String startDate, String endDate, Integer projectId, String status) {
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.projectId = projectId;
        this.status = status;
    }

    @Override
    public String toString() {
        return "EmployeeProjectData{" +
                "title='" + title + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", projectId=" + projectId +
                ", status='" + status + '\'' +
                '}';
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Integer getProjectId() {
        return projectId;
    }

    public void setProjectId(Integer projectId) {
        this.projectId = projectId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
