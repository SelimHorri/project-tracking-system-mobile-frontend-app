package com.selimhorri.pack.service.impl.dynmc;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.selimhorri.pack.constant.BackendApiUrlConstant;
import com.selimhorri.pack.listener.ResponseCallbackListener;
import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Assignment;
import com.selimhorri.pack.model.id.AssignmentId;
import com.selimhorri.pack.pattern.QueuePattern;
import com.selimhorri.pack.service.AssignmentService;

import java.time.format.DateTimeFormatter;

public class AssignmentServiceDynamicImpl implements AssignmentService {

    private static final String API_URL = BackendApiUrlConstant.AssignmentBackendUrl.ASSIGNMENT_API_URL;
    private final Context context;

    public AssignmentServiceDynamicImpl(final Context context) {
        this.context = context;
    }

    @Override
    public void findAll(final ResponseCallbackListener.ResponseCallbackSuccessListener<DtoCollection<Assignment>> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                API_URL,
                null,
                response -> resp.onResponse(new Gson().fromJson(response.toString(), new TypeToken<DtoCollection<Assignment>>() {}.getType())),
                error -> err.onError(error.toString())
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void findById(AssignmentId assignmentId, ResponseCallbackListener.ResponseCallbackSuccessListener<Assignment> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                API_URL + "/" + assignmentId.getEmployeeId() + "/" + assignmentId.getProjectId() + "/" + assignmentId.getCommitDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyyHH:mm:ss")),
                null,
                response -> resp.onResponse(new Gson().fromJson(response.toString(), Assignment.class)),
                error -> err.onError(error.toString())
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void save(Assignment assignment, ResponseCallbackListener.ResponseCallbackSuccessListener<Assignment> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                API_URL,
                null,
                response -> resp.onResponse(new Gson().fromJson(response.toString(), Assignment.class)),
                error -> err.onError(error.toString())
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void update(Assignment assignment, ResponseCallbackListener.ResponseCallbackSuccessListener<Assignment> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.PUT,
                API_URL,
                null,
                response -> resp.onResponse(new Gson().fromJson(response.toString(), Assignment.class)),
                error -> err.onError(error.toString())
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void deleteById(AssignmentId assignmentId, ResponseCallbackListener.ResponseCallbackSuccessListener<Boolean> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.DELETE,
                API_URL + "/" + assignmentId.getEmployeeId() + "/" + assignmentId.getProjectId() + "/" + assignmentId.getCommitDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyyHH:mm:ss")),
                null,
                response -> resp.onResponse(new Gson().fromJson(response.toString(), Boolean.class)),
                error -> err.onError(error.toString())
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }



}
