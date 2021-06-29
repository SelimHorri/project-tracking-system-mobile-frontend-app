package com.selimhorri.pack.model.dto.custom;

public class ManagerProjectData extends EmployeeProjectData {

    public ManagerProjectData() {
        super();
    }

    public ManagerProjectData(String title, String startDate, String endDate, Integer projectId, String status) {
        super(title, startDate, endDate, projectId, status);
    }



}
