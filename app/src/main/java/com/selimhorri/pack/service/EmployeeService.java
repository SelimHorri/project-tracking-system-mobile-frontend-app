package com.selimhorri.pack.service;

import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Employee;

public interface EmployeeService {

    DtoCollection<Employee> findAll();
    Employee findById(final Integer employeeId);
    Employee save(final Employee employee);
    Employee update(final Employee employee);
    void deleteById(final Integer employeeId);

}
