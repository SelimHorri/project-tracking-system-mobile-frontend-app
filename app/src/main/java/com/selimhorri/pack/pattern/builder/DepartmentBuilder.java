package com.selimhorri.pack.pattern.builder;

import com.selimhorri.pack.model.dto.Department;
import com.selimhorri.pack.model.dto.Location;

public class DepartmentBuilder {

    private final Department department;

    public DepartmentBuilder() {
        this.department = new Department();
    }

    public DepartmentBuilder(final Department department) {
        this.department = department;
    }

    public Department build() {
        return this.department;
    }

    public DepartmentBuilder departmentId(final int departmentId) {
        this.department.setDepartmentId(departmentId);
        return this;
    }

    public DepartmentBuilder departmentName(final String departmentName) {
        this.department.setDepartmentName(departmentName);
        return this;
    }

    public DepartmentBuilder location(final Location location) {
        this.department.setLocation(location);
        return this;
    }

}
