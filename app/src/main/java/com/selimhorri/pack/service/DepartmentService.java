package com.selimhorri.pack.service;

import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Department;
import com.selimhorri.pack.model.dto.Location;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DepartmentService {

    private static List<Department> departments;

    public DepartmentService() {
        departments = Arrays.asList(
                new Department(1, "Billing", new Location()),
                new Department(2, "DWH", new Location()),
                new Department(3, "Digital", new Location())
        );
    }

    public DtoCollection<Department> findAll() {
        return new DtoCollection<>(departments);
    }

    public Department findById(final Integer departmentId) {
        return departments.get(departmentId - 1);
    }

}
