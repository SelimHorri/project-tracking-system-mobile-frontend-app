package com.selimhorri.pack.service;

import com.selimhorri.pack.listener.ResponseCallbackListener;
import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Project;

public interface ProjectService {

    void findAll(final ResponseCallbackListener.ResponseCallbackSuccessListener<DtoCollection<Project>> response, final ResponseCallbackListener.ResponseCallbackErrorListener error);
    void findById(final Integer projectId, final ResponseCallbackListener.ResponseCallbackSuccessListener<Project> response, final ResponseCallbackListener.ResponseCallbackErrorListener error);
    void save(final Project project, final ResponseCallbackListener.ResponseCallbackSuccessListener<Project> response, final ResponseCallbackListener.ResponseCallbackErrorListener error);
    void update(final Project project, final ResponseCallbackListener.ResponseCallbackSuccessListener<Project> response, final ResponseCallbackListener.ResponseCallbackErrorListener error);
    void deleteById(final Integer projectId, final ResponseCallbackListener.ResponseCallbackSuccessListener<Boolean> response, final ResponseCallbackListener.ResponseCallbackErrorListener error);

}
