package com.selimhorri.pack.service;

import com.selimhorri.pack.listener.ResponseCallbackListener;
import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Credential;
import com.selimhorri.pack.model.dto.Department;

public interface DepartmentService {

    void findAll(final ResponseCallbackListener.ResponseCallbackSuccessListener<DtoCollection<Department>> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err);
    void findById(final Integer departmentId, final ResponseCallbackListener.ResponseCallbackSuccessListener<Department> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err);
    void save(final Department department, final ResponseCallbackListener.ResponseCallbackSuccessListener<Department> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err);
    void update(final Department department, final ResponseCallbackListener.ResponseCallbackSuccessListener<Department> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err);
    void deleteById(final Integer departmentId, final ResponseCallbackListener.ResponseCallbackSuccessListener<Boolean> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err);

}
