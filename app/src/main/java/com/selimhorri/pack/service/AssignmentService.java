package com.selimhorri.pack.service;

import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Assignment;

import java.time.LocalDateTime;

public interface AssignmentService {

    DtoCollection<Assignment> findAll();
    Assignment findById(final Integer employeeId, final Integer projectId, final LocalDateTime commitDate);
    Assignment save(final Assignment assignment);
    Assignment update(final Assignment assignment);
    void deleteById(final Integer employeeId, final Integer projectId, final LocalDateTime commitDate);

}
