package com.selimhorri.pack.service;

import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Department;

public interface DepartmentService {

    DtoCollection<Department> findAll();
    Department findById(final Integer departmentId);
    Department save(final Department department);
    Department update(final Department department);
    void deleteById(final Integer departmentId);

}
