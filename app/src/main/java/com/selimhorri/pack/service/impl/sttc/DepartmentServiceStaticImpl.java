package com.selimhorri.pack.service.impl.sttc;

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

    private static SortedMap<Integer, Department> depts;

    public DepartmentServiceStaticImpl() {
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
        return depts.get(departmentId);
    }

    @Override
    public Department save(final Department department) {

        if (depts.containsKey(department.getDepartmentId()))
            throw new RuntimeException("#### department exists already ####");

        depts.put(department.getDepartmentId(), department);

        return depts.get(department.getDepartmentId());
    }

    @Override
    public Department update(final Department department) {

        if (!depts.containsKey(department.getDepartmentId()))
            throw new RuntimeException("#### department does not exist ####");

        depts.get(department.getDepartmentId()).setDepartmentName(department.getDepartmentName());
        depts.get(department.getDepartmentId()).setLocation(department.getLocation());

        return depts.get(department.getDepartmentId());
    }

    @Override
    public void deleteById(final Integer departmentId) {
        depts.remove(departmentId);
    }



}
