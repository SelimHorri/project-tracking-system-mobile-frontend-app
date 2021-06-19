package com.selimhorri.pack.service.impl.dynmc;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.selimhorri.pack.constant.BackendApiUrlConstant;
import com.selimhorri.pack.listener.ResponseCallbackListener;
import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Project;
import com.selimhorri.pack.pattern.QueuePattern;
import com.selimhorri.pack.service.ProjectService;

public class ProjectServiceDynamicImpl implements ProjectService {

    private static final String API_URL = BackendApiUrlConstant.ProjectBackendUrl.PROJECT_API_URL;
    private final Context context;

    public ProjectServiceDynamicImpl(final Context context) {
        this.context = context;
    }

    @Override
    public void findAll(final ResponseCallbackListener.ResponseCallbackSuccessListener<DtoCollection<Project>> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                API_URL,
                null,
                response -> resp.onResponse(new Gson().fromJson(response.toString(), new TypeToken<DtoCollection<Project>>() {}.getType())),
                error -> err.onError(error.toString())
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void findById(Integer projectId, ResponseCallbackListener.ResponseCallbackSuccessListener<Project> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                API_URL + "/" + projectId,
                null,
                response -> resp.onResponse(new Gson().fromJson(response.toString(), Project.class)),
                error -> err.onError(error.toString())
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void save(Project project, ResponseCallbackListener.ResponseCallbackSuccessListener<Project> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                API_URL,
                null,
                response -> resp.onResponse(new Gson().fromJson(response.toString(), Project.class)),
                error -> err.onError(error.toString())
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void update(Project project, ResponseCallbackListener.ResponseCallbackSuccessListener<Project> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.PUT,
                API_URL,
                null,
                response -> resp.onResponse(new Gson().fromJson(response.toString(), Project.class)),
                error -> err.onError(error.toString())
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void deleteById(Integer projectId, ResponseCallbackListener.ResponseCallbackSuccessListener<Boolean> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.DELETE,
                API_URL + "/" + projectId,
                null,
                response -> resp.onResponse(new Gson().fromJson(response.toString(), Boolean.class)),
                error -> err.onError(error.toString())
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }



}
