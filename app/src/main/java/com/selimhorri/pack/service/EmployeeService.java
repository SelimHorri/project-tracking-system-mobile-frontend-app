package com.selimhorri.pack.service;

import com.selimhorri.pack.listener.ResponseCallbackListener;
import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Employee;
import com.selimhorri.pack.model.dto.custom.EmployeeProjectData;
import com.selimhorri.pack.model.dto.custom.ManagerProjectData;

public interface EmployeeService {

    void findAll(final ResponseCallbackListener.ResponseCallbackSuccessListener<DtoCollection<Employee>> response, final ResponseCallbackListener.ResponseCallbackErrorListener error);
    void findById(final Integer employeeId, final ResponseCallbackListener.ResponseCallbackSuccessListener<Employee> response, final ResponseCallbackListener.ResponseCallbackErrorListener error);
    void save(final Employee employee, final ResponseCallbackListener.ResponseCallbackSuccessListener<Employee> response, final ResponseCallbackListener.ResponseCallbackErrorListener error);
    void update(final Employee employee, final ResponseCallbackListener.ResponseCallbackSuccessListener<Employee> response, final ResponseCallbackListener.ResponseCallbackErrorListener error);
    void deleteById(final Integer employeeId, final ResponseCallbackListener.ResponseCallbackSuccessListener<Boolean> response, final ResponseCallbackListener.ResponseCallbackErrorListener error);
    void findByUsername(final String username, final ResponseCallbackListener.ResponseCallbackSuccessListener<Employee> response, final ResponseCallbackListener.ResponseCallbackErrorListener error);
    void findAllEmployeeProjectDataByEmployeeId(final Integer employeeId, final ResponseCallbackListener.ResponseCallbackSuccessListener<DtoCollection<EmployeeProjectData>> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err);
    void findAllManagerProjectDataByEmployeeId(final Integer employeeId, final ResponseCallbackListener.ResponseCallbackSuccessListener<DtoCollection<EmployeeProjectData>> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err);
    void findByDepartmentId(final Integer departmentId, final ResponseCallbackListener.ResponseCallbackSuccessListener<DtoCollection<Employee>> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err);

}
