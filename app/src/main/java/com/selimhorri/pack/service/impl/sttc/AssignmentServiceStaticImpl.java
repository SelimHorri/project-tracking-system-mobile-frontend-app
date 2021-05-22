package com.selimhorri.pack.service.impl.sttc;

import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Assignment;
import com.selimhorri.pack.service.AssignmentService;

import java.time.LocalDateTime;

public class AssignmentServiceStaticImpl implements AssignmentService {

    @Override
    public DtoCollection<Assignment> findAll() {
        return null;
    }

    @Override
    public Assignment findById(final Integer employeeId, final Integer projectId, final LocalDateTime commitDate) {
        return null;
    }

    @Override
    public Assignment save(final Assignment assignment) {
        return null;
    }

    @Override
    public Assignment update(final Assignment assignment) {
        return null;
    }

    @Override
    public void deleteById(final Integer employeeId, final Integer projectId, final LocalDateTime commitDate) {

    }



}
