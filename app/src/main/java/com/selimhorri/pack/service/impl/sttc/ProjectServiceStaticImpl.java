package com.selimhorri.pack.service.impl.sttc;

import android.content.Context;

import com.selimhorri.pack.exception.ObjectAlreadyExistsException;
import com.selimhorri.pack.exception.ObjectNotFoundException;
import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Project;
import com.selimhorri.pack.model.dto.Project;
import com.selimhorri.pack.service.ProjectService;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class ProjectServiceStaticImpl implements ProjectService {

    private static final SortedMap<Integer, Project> DUMMY_PROJECTS = new TreeMap<>();
    private final Context context;

    static {
        DUMMY_PROJECTS.put(1, new Project(1, "TRANSBSCS", LocalDate.of(2020, Month.SEPTEMBER, 28), LocalDate.of(2020, Month.NOVEMBER, 4), "COMPLETED"));
        DUMMY_PROJECTS.put(2, new Project(2, "SYNCH_BSCS_IMX", LocalDate.of(2020, 11, 26), LocalDate.of(2021, 3, 25), "IN_PROGRESS"));
        DUMMY_PROJECTS.put(3, new Project(3, "TASYI9A LILVIRANDA", LocalDate.of(2020, 11, 26), LocalDate.of(2020, 11, 26), "COMPLETED"));
        DUMMY_PROJECTS.put(4, new Project(4, "MACHYA_RANDONNEE", LocalDate.of(2021, Month.JANUARY, 29), LocalDate.of(2021, 4, 30), "NOT_STARTED"));
        DUMMY_PROJECTS.put(5, new Project(5, "TATIB LEFTOUR", LocalDate.of(2021, Month.NOVEMBER, 14), LocalDate.of(2021, Month.NOVEMBER, 14), "COMPLETED"));
    }

    public ProjectServiceStaticImpl(final Context context) {
        this.context = context;
    }

    @Override
    public DtoCollection<Project> findAll() {

        final List<Project> list = new ArrayList<>();

        for (Map.Entry<Integer, Project> entry : DUMMY_PROJECTS.entrySet())
            list.add(entry.getValue());

        return new DtoCollection<>(list);
    }

    @Override
    public Project findById(final Integer projectId) {

        if (!DUMMY_PROJECTS.containsKey(projectId))
            throw new ObjectNotFoundException("#### Project does not exist! ####");

        return DUMMY_PROJECTS.get(projectId);
    }

    @Override
    public Project save(final Project project) {

        if (DUMMY_PROJECTS.containsKey(project.getProjectId()))
            throw new ObjectAlreadyExistsException("#### project exists already ####");

        DUMMY_PROJECTS.put(project.getProjectId(), project);

        return DUMMY_PROJECTS.get(project.getProjectId());
    }

    @Override
    public Project update(final Project project) {

        if (!DUMMY_PROJECTS.containsKey(project.getProjectId()))
            throw new ObjectNotFoundException("#### project does not exist ####");

        DUMMY_PROJECTS.get(project.getProjectId()).setTitle(project.getTitle());
        DUMMY_PROJECTS.get(project.getProjectId()).setStartDate(project.getStartDate());
        DUMMY_PROJECTS.get(project.getProjectId()).setEndDate(project.getEndDate());
        DUMMY_PROJECTS.get(project.getProjectId()).setStatus(project.getStatus());

        return DUMMY_PROJECTS.get(project.getProjectId());
    }

    @Override
    public void deleteById(final Integer projectId) {

        if (!DUMMY_PROJECTS.containsKey(projectId))
            throw new ObjectNotFoundException("#### project does not exist! ####");

        DUMMY_PROJECTS.remove(projectId);
    }



}
