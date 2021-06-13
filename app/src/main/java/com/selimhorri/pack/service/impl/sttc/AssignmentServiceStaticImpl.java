package com.selimhorri.pack.service.impl.sttc;

import android.content.Context;

import com.selimhorri.pack.exception.ObjectAlreadyExistsException;
import com.selimhorri.pack.exception.ObjectNotFoundException;
import com.selimhorri.pack.listener.ResponseCallbackListener;
import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Assignment;
import com.selimhorri.pack.model.dto.Employee;
import com.selimhorri.pack.model.dto.Assignment;
import com.selimhorri.pack.model.dto.Project;
import com.selimhorri.pack.model.id.AssignmentId;
import com.selimhorri.pack.service.AssignmentService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class AssignmentServiceStaticImpl implements AssignmentService {

    private static final SortedMap<AssignmentId, Assignment> DUMMY_ASSIGNMENTS = new TreeMap<>();
    private final Context context;

    static {
        DUMMY_ASSIGNMENTS.put(new AssignmentId(1, 1, LocalDateTime.of(2020, 11, 26, 10, 50, 9)), new Assignment(1, 1, LocalDateTime.of(2020, 11, 26, 10, 50, 9), null, "init", new Employee(), new Project()));
        DUMMY_ASSIGNMENTS.put(new AssignmentId(1, 1, LocalDateTime.of(2020, 11, 26, 13, 14, 22)), new Assignment(1, 1, LocalDateTime.of(2020, 11, 26, 13, 14, 22), "set up some configs", "you need to implement sec solution", new Employee(), new Project()));
        DUMMY_ASSIGNMENTS.put(new AssignmentId(1, 1, LocalDateTime.of(2020, 12, 12, 16, 49, 42)), new Assignment(1, 1, LocalDateTime.of(2020, 12, 12, 16, 49, 42), "implement customer by invoice", null, new Employee(), new Project()));
        /*DUMMY_ASSIGNMENTS.put(new AssignmentId(1, 1, LocalDateTime.of(2020, 12, 12, 17, 04, 14)), new Assignment());
        DUMMY_ASSIGNMENTS.put(new AssignmentId(1, 1, LocalDateTime.of(2020, 12, 12, 17, 4, 30)), new Assignment());
        DUMMY_ASSIGNMENTS.put(new AssignmentId(1, 1, LocalDateTime.of(2020, 12, 12, 17, 25, 48)), new Assignment());*/
        DUMMY_ASSIGNMENTS.put(new AssignmentId(1, 2, LocalDateTime.of(2020, 11, 26, 10, 51, 59)), new Assignment());
        DUMMY_ASSIGNMENTS.put(new AssignmentId(1, 2, LocalDateTime.of(2020, 11, 26, 13, 14, 22)), new Assignment());
        DUMMY_ASSIGNMENTS.put(new AssignmentId(2, 5, LocalDateTime.of(2020, 11, 26, 10, 52, 32)), new Assignment());
        DUMMY_ASSIGNMENTS.put(new AssignmentId(2, 5, LocalDateTime.of(2020, 12, 12, 15, 10, 28)), new Assignment());
        DUMMY_ASSIGNMENTS.put(new AssignmentId(3, 4, LocalDateTime.of(2020, 12, 13, 19, 55, 14)), new Assignment());
        DUMMY_ASSIGNMENTS.put(new AssignmentId(3, 4, LocalDateTime.of(2020, 12, 17, 11, 20, 47)), new Assignment());
    }

    public AssignmentServiceStaticImpl(final Context context) {
        this.context = context;
    }

    @Override
    public void findAll(final ResponseCallbackListener.ResponseCallbackSuccessListener<DtoCollection<Assignment>> response, final ResponseCallbackListener.ResponseCallbackErrorListener error) {

        final List<Assignment> list = new ArrayList<>();

        for (SortedMap.Entry<AssignmentId, Assignment> entry : DUMMY_ASSIGNMENTS.entrySet())
            list.add(entry.getValue());

    }

    @Override
    public void findById(final AssignmentId assignmentId, final ResponseCallbackListener.ResponseCallbackSuccessListener<Assignment> response, final ResponseCallbackListener.ResponseCallbackErrorListener error) {

        if (!DUMMY_ASSIGNMENTS.containsKey(assignmentId))
            throw new ObjectNotFoundException("#### Assignment does not exist! ####");

    }

    @Override
    public void save(final Assignment assignment, final ResponseCallbackListener.ResponseCallbackSuccessListener<Assignment> response, final ResponseCallbackListener.ResponseCallbackErrorListener error) {

        final AssignmentId assignmentId = new AssignmentId(assignment.getEmployeeId(), assignment.getProjectId(), assignment.getCommitDate());

        if (DUMMY_ASSIGNMENTS.containsKey(assignmentId))
            throw new ObjectAlreadyExistsException("#### assignment exists already ####");

        DUMMY_ASSIGNMENTS.put(assignmentId, assignment);

    }

    @Override
    public void update(final Assignment assignment, final ResponseCallbackListener.ResponseCallbackSuccessListener<Assignment> response, final ResponseCallbackListener.ResponseCallbackErrorListener error) {

        final AssignmentId assignmentId = new AssignmentId(assignment.getEmployeeId(), assignment.getProjectId(), assignment.getCommitDate());

        if (!DUMMY_ASSIGNMENTS.containsKey(assignmentId))
            throw new ObjectNotFoundException("#### assignment does not exist ####");

        DUMMY_ASSIGNMENTS.get(assignmentId).setEmployeeId(assignment.getEmployeeId());
        DUMMY_ASSIGNMENTS.get(assignmentId).setProjectId(assignment.getProjectId());
        DUMMY_ASSIGNMENTS.get(assignmentId).setCommitDate(assignment.getCommitDate());
        DUMMY_ASSIGNMENTS.get(assignmentId).setCommitEmpDesc(assignment.getCommitEmpDesc());
        DUMMY_ASSIGNMENTS.get(assignmentId).setCommitMgrDesc(assignment.getCommitMgrDesc());
        DUMMY_ASSIGNMENTS.get(assignmentId).setEmployee(assignment.getEmployee());
        DUMMY_ASSIGNMENTS.get(assignmentId).setProject(assignment.getProject());

    }

    @Override
    public void deleteById(final AssignmentId assignmentId, final ResponseCallbackListener.ResponseCallbackSuccessListener<Boolean> response, final ResponseCallbackListener.ResponseCallbackErrorListener error) {

        if (!DUMMY_ASSIGNMENTS.containsKey(assignmentId))
            throw new ObjectNotFoundException("#### assignment does not exist! ####");

    }



}
