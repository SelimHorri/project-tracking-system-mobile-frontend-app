package com.selimhorri.pack.service.impl.dynmc;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.selimhorri.pack.constant.BackendApiUrlConstant;
import com.selimhorri.pack.exception.payload.ExceptionMsg;
import com.selimhorri.pack.listener.ResponseCallbackListener;
import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Assignment;
import com.selimhorri.pack.model.id.AssignmentId;
import com.selimhorri.pack.pattern.singleton.GsonSingletonPattern;
import com.selimhorri.pack.pattern.singleton.QueueSingletonPattern;
import com.selimhorri.pack.service.AssignmentService;

import org.json.JSONObject;

import java.nio.charset.StandardCharsets;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class AssignmentServiceDynamicImpl implements AssignmentService {

    private static final String API_URL = BackendApiUrlConstant.AssignmentBackendUrl.ASSIGNMENT_API_URL;
    private static final Gson gson = GsonSingletonPattern.getInstance().configDeserialization("dd-MM-yyyyHH:mm:ss");
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
                response -> resp.onResponse(gson.fromJson(response.toString(), new TypeToken<DtoCollection<Assignment>>() {}.getType())),
                error -> err.onError(gson.fromJson(new String(error.networkResponse.data, StandardCharsets.UTF_8), ExceptionMsg.class).getMsg())
        );
        QueueSingletonPattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void findById(AssignmentId assignmentId, ResponseCallbackListener.ResponseCallbackSuccessListener<Assignment> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                API_URL + "/" + assignmentId.getEmployeeId() + "/" + assignmentId.getProjectId() + "/" + assignmentId.getCommitDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyyHH:mm:ss")),
                null,
                response -> resp.onResponse(gson.fromJson(response.toString(), Assignment.class)),
                error -> err.onError(gson.fromJson(new String(error.networkResponse.data, StandardCharsets.UTF_8), ExceptionMsg.class).getMsg())
        );
        QueueSingletonPattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void save(Assignment assignment, ResponseCallbackListener.ResponseCallbackSuccessListener<Assignment> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final Map<String, Object> map = new HashMap<>();
        map.put("employeeId", assignment.getEmployeeId());
        map.put("projectId", assignment.getProjectId());
        map.put("commitDate", assignment.getCommitDate());
        map.put("commitEmpDesc", assignment.getCommitEmpDesc());
        map.put("commitMgrDesc", assignment.getCommitMgrDesc());
        map.put("employee", assignment.getEmployee());
        map.put("project", assignment.getProject());

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                API_URL,
                new JSONObject(map),
                response -> resp.onResponse(gson.fromJson(response.toString(), Assignment.class)),
                error -> err.onError(gson.fromJson(new String(error.networkResponse.data, StandardCharsets.UTF_8), ExceptionMsg.class).getMsg())
        );
        QueueSingletonPattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void update(Assignment assignment, ResponseCallbackListener.ResponseCallbackSuccessListener<Assignment> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final Map<String, Object> map = new HashMap<>();
        map.put("employeeId", assignment.getEmployeeId());
        map.put("projectId", assignment.getProjectId());
        map.put("commitDate", assignment.getCommitDate());
        map.put("commitEmpDesc", assignment.getCommitEmpDesc());
        map.put("commitMgrDesc", assignment.getCommitMgrDesc());
        map.put("employee", assignment.getEmployee());
        map.put("project", assignment.getProject());

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.PUT,
                API_URL,
                new JSONObject(map),
                response -> resp.onResponse(gson.fromJson(response.toString(), Assignment.class)),
                error -> err.onError(gson.fromJson(new String(error.networkResponse.data, StandardCharsets.UTF_8), ExceptionMsg.class).getMsg())
        );
        QueueSingletonPattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void deleteById(AssignmentId assignmentId, ResponseCallbackListener.ResponseCallbackSuccessListener<Boolean> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.DELETE,
                API_URL + "/" + assignmentId.getEmployeeId() + "/" + assignmentId.getProjectId() + "/" + assignmentId.getCommitDate().format(DateTimeFormatter.ofPattern("dd-MM-yyyyHH:mm:ss")),
                null,
                response -> resp.onResponse(gson.fromJson(response.toString(), Boolean.class)),
                error -> err.onError(gson.fromJson(new String(error.networkResponse.data, StandardCharsets.UTF_8), ExceptionMsg.class).getMsg())
        );
        QueueSingletonPattern.getInstance(this.context).addToRequestQueue(request);

    }


}
