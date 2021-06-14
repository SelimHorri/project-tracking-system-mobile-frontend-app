package com.selimhorri.pack.service.impl.dynmc;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.selimhorri.pack.listener.ResponseCallbackListener;
import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Employee;
import com.selimhorri.pack.pattern.QueuePattern;
import com.selimhorri.pack.service.EmployeeService;

public class EmployeeServiceDynamicImpl implements EmployeeService {

    private static final String API_URL = "https://project-tracking-system-heroku.herokuapp.com/app/api/employees";
    private final Context context;

    public EmployeeServiceDynamicImpl(final Context context) {
        this.context = context;
    }

    @Override
    public void findAll(final ResponseCallbackListener.ResponseCallbackSuccessListener<DtoCollection<Employee>> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                API_URL,
                null,
                response -> resp.onResponse(new Gson().fromJson(response.toString(), new TypeToken<DtoCollection<Employee>>() {}.getType())),
                error -> err.onError(error.toString())
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void findById(Integer employeeId, ResponseCallbackListener.ResponseCallbackSuccessListener<Employee> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                API_URL + "/" + employeeId,
                null,
                response -> resp.onResponse(new Gson().fromJson(response.toString(), Employee.class)),
                error -> err.onError(error.toString())
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void save(Employee employee, ResponseCallbackListener.ResponseCallbackSuccessListener<Employee> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                API_URL,
                null,
                response -> resp.onResponse(new Gson().fromJson(response.toString(), Employee.class)),
                error -> err.onError(error.toString())
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void update(Employee employee, ResponseCallbackListener.ResponseCallbackSuccessListener<Employee> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.PUT,
                API_URL,
                null,
                response -> resp.onResponse(new Gson().fromJson(response.toString(), Employee.class)),
                error -> err.onError(error.toString())
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void deleteById(Integer employeeId, ResponseCallbackListener.ResponseCallbackSuccessListener<Boolean> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.DELETE,
                API_URL + "/" + employeeId,
                null,
                response -> resp.onResponse(new Gson().fromJson(response.toString(), Boolean.class)),
                error -> err.onError(error.toString())
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }



}
