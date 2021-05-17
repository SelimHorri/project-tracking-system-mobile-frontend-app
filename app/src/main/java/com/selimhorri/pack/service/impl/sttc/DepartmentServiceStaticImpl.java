package com.selimhorri.pack.service.impl.sttc;

import android.content.Context;
import android.widget.Toast;

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

    private final Context context;
    private static SortedMap<Integer, Department> depts;

    public DepartmentServiceStaticImpl(final Context context) {
        this.context = context;
        depts = new TreeMap<>();
        depts.put(1, new Department(1, "billing", new Location()));
        depts.put(2, new Department(2, "DWH", new Location()));
        depts.put(3, new Department(3, "digital", new Location()));
    }

    @Override
    public DtoCollection<Department> findAll() {

        List<Department> list = new ArrayList<>();

        for (Map.Entry<Integer, Department> map : depts.entrySet())
            list.add(map.getValue());

        return new DtoCollection<>(list);
    }

    @Override
    public Department findById(final Integer departmentId) {

        if (!depts.containsKey(departmentId))
            throw new ObjectNotFoundException("#### Department does not exist! ####");

        return depts.get(departmentId);
    }

    @Override
    public Department save(final Department department) {

        if (depts.containsKey(department.getDepartmentId()))
            throw new ObjectAlreadyExistsException("#### department exists already ####");

        depts.put(department.getDepartmentId(), department);

        return depts.get(department.getDepartmentId());
    }

    @Override
    public Department update(final Department department) {

        if (!depts.containsKey(department.getDepartmentId()))
            throw new ObjectNotFoundException("#### department does not exist ####");

        depts.get(department.getDepartmentId()).setDepartmentName(department.getDepartmentName());
        depts.get(department.getDepartmentId()).setLocation(department.getLocation());

        return depts.get(department.getDepartmentId());
    }

    @Override
    public void deleteById(final Integer departmentId) {

        if (!depts.containsKey(departmentId))
            throw new ObjectNotFoundException("#### department does not exist! ####");

        depts.remove(departmentId);
    }



}
