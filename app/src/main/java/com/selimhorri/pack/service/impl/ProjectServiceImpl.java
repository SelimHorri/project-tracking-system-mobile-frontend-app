package com.selimhorri.pack.service.impl;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.selimhorri.pack.constant.BackendApiUrlConstant;
import com.selimhorri.pack.exception.payload.ExceptionMsg;
import com.selimhorri.pack.listener.ResponseCallbackListener;
import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Project;
import com.selimhorri.pack.pattern.singleton.GsonSingletonPattern;
import com.selimhorri.pack.pattern.singleton.QueueSingletonPattern;
import com.selimhorri.pack.service.ProjectService;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class ProjectServiceImpl implements ProjectService {

    private static final String API_URL = BackendApiUrlConstant.ProjectBackendUrl.PROJECT_API_URL;
    private static final Gson gson = GsonSingletonPattern.getInstance().configDeserialization(LocalDate.now(), "dd-MM-yyyy");
    private final Context context;

    public ProjectServiceImpl(final Context context) {
        this.context = context;
    }

    @Override
    public void findAll(final ResponseCallbackListener.ResponseCallbackSuccessListener<DtoCollection<Project>> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                API_URL,
                null,
                response -> resp.onResponse(gson.fromJson(response.toString(), new TypeToken<DtoCollection<Project>>() {}.getType())),
                error -> err.onError(gson.fromJson(new String(error.networkResponse.data, StandardCharsets.UTF_8), ExceptionMsg.class).getMsg())
        );
        QueueSingletonPattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void findById(Integer projectId, ResponseCallbackListener.ResponseCallbackSuccessListener<Project> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                API_URL + "/" + projectId,
                null,
                response -> resp.onResponse(gson.fromJson(response.toString(), Project.class)),
                error -> err.onError(gson.fromJson(new String(error.networkResponse.data, StandardCharsets.UTF_8), ExceptionMsg.class).getMsg())
        );
        QueueSingletonPattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void save(Project project, ResponseCallbackListener.ResponseCallbackSuccessListener<Project> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final Map<String, Object> map = new HashMap<>();
        map.put("title", project.getTitle());
        map.put("startDate", project.getStartDate());
        map.put("endDate", project.getEndDate());
        map.put("status", project.getStatus());

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                API_URL,
                new JSONObject(map),
                response -> resp.onResponse(gson.fromJson(response.toString(), Project.class)),
                error -> err.onError(gson.fromJson(new String(error.networkResponse.data, StandardCharsets.UTF_8), ExceptionMsg.class).getMsg())
        );
        QueueSingletonPattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void update(Project project, ResponseCallbackListener.ResponseCallbackSuccessListener<Project> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final Map<String, Object> map = new HashMap<>();
        map.put("projectId", project.getProjectId());
        map.put("title", project.getTitle());
        map.put("startDate", project.getStartDate());
        map.put("endDate", project.getEndDate());
        map.put("status", project.getStatus());

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.PUT,
                API_URL,
                new JSONObject(map),
                response -> resp.onResponse(gson.fromJson(response.toString(), Project.class)),
                error -> err.onError(gson.fromJson(new String(error.networkResponse.data, StandardCharsets.UTF_8), ExceptionMsg.class).getMsg())
        );
        QueueSingletonPattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void deleteById(Integer projectId, ResponseCallbackListener.ResponseCallbackSuccessListener<Boolean> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.DELETE,
                API_URL + "/" + projectId,
                null,
                response -> resp.onResponse(gson.fromJson(response.toString(), Boolean.class)),
                error -> err.onError(gson.fromJson(new String(error.networkResponse.data, StandardCharsets.UTF_8), ExceptionMsg.class).getMsg())
        );
        QueueSingletonPattern.getInstance(this.context).addToRequestQueue(request);

    }



}
