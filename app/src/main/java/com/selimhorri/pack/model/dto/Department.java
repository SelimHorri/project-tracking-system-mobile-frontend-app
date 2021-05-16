package com.selimhorri.pack.model.dto;

public class Department {

    private Integer departmentId;
    private String departmentName;
    private Location location;

    public Department() {

    }

    public Department(String departmentName, Location location) {
        this.departmentName = departmentName;
        this.location = location;
    }

    public Department(Integer departmentId, String departmentName, Location location) {
        this.departmentId = departmentId;
        this.departmentName = departmentName;
        this.location = location;
    }

    @Override
    public String toString() {
        return "Department{" +
                "departmentId=" + departmentId +
                ", departmentName='" + departmentName + '\'' +
                ", locationId=" + location.getLocationId() +
                '}';
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }



}
