package com.selimhorri.pack.service;

import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Project;

public interface ProjectService {

    DtoCollection<Project> findAll();
    Project findById(final Integer projectId);
    Project save(final Project project);
    Project update(final Project project);
    void deleteById(final Integer projectId);

}
