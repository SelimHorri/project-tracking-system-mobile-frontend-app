package com.selimhorri.pack.service.impl.sttc;

import android.content.Context;

import com.selimhorri.pack.exception.ObjectAlreadyExistsException;
import com.selimhorri.pack.exception.ObjectNotFoundException;
import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Department;
import com.selimhorri.pack.model.dto.Location;
import com.selimhorri.pack.service.DepartmentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class DepartmentServiceStaticImpl implements DepartmentService {

    private static final SortedMap<Integer, Department> DUMMY_DEPARTMENTS = new TreeMap<>();
    private final Context context;

    static {
        DUMMY_DEPARTMENTS.put(1, new Department(1, "billing", new Location()));
        DUMMY_DEPARTMENTS.put(2, new Department(2, "DWH", new Location()));
        DUMMY_DEPARTMENTS.put(3, new Department(3, "digital", new Location()));
    }

    public DepartmentServiceStaticImpl(final Context context) {
        this.context = context;
    }

    @Override
    public DtoCollection<Department> findAll() {

        final List<Department> list = new ArrayList<>();

        for (Map.Entry<Integer, Department> entry : DUMMY_DEPARTMENTS.entrySet())
            list.add(entry.getValue());

        return new DtoCollection<>(list);
    }

    @Override
    public Department findById(final Integer departmentId) {

        if (!DUMMY_DEPARTMENTS.containsKey(departmentId))
            throw new ObjectNotFoundException("#### Department does not exist! ####");

        return DUMMY_DEPARTMENTS.get(departmentId);
    }

    @Override
    public Department save(final Department department) {

        if (DUMMY_DEPARTMENTS.containsKey(department.getDepartmentId()))
            throw new ObjectAlreadyExistsException("#### department exists already ####");

        DUMMY_DEPARTMENTS.put(department.getDepartmentId(), department);

        return DUMMY_DEPARTMENTS.get(department.getDepartmentId());
    }

    @Override
    public Department update(final Department department) {

        if (!DUMMY_DEPARTMENTS.containsKey(department.getDepartmentId()))
            throw new ObjectNotFoundException("#### department does not exist ####");

        DUMMY_DEPARTMENTS.get(department.getDepartmentId()).setDepartmentName(department.getDepartmentName());
        DUMMY_DEPARTMENTS.get(department.getDepartmentId()).setLocation(department.getLocation());

        return DUMMY_DEPARTMENTS.get(department.getDepartmentId());
    }

    @Override
    public void deleteById(final Integer departmentId) {

        if (!DUMMY_DEPARTMENTS.containsKey(departmentId))
            throw new ObjectNotFoundException("#### department does not exist! ####");

        DUMMY_DEPARTMENTS.remove(departmentId);
    }



}
