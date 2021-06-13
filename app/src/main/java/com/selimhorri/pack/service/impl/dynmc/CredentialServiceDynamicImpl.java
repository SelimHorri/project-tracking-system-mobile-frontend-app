package com.selimhorri.pack.service.impl.dynmc;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.selimhorri.pack.listener.ResponseCallbackListener;
import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Credential;
import com.selimhorri.pack.pattern.QueuePattern;
import com.selimhorri.pack.service.CredentialService;

public class CredentialServiceDynamicImpl implements CredentialService {

    private static final String API_URL = "https://project-tracking-system-heroku.herokuapp.com/app/api/credentials";
    private final Context context;

    public CredentialServiceDynamicImpl(final Context context) {
        this.context = context;
    }

    @Override
    public void findAll(final ResponseCallbackListener.ResponseCallbackSuccessListener<DtoCollection<Credential>> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                API_URL,
                null,
                response -> {
                    resp.onResponse(new Gson().fromJson(response.toString(), new TypeToken<DtoCollection<Credential>>() {}.getType()));
                },
                error -> {
                    err.onError(error.toString());
                }
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void findById(Integer credentialId, ResponseCallbackListener.ResponseCallbackSuccessListener<Credential> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                API_URL + "/" + credentialId,
                null,
                response -> {
                    resp.onResponse(new Gson().fromJson(response.toString(), new TypeToken<Credential>() {}.getType()));
                },
                error -> {
                    err.onError(error.toString());
                }
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void save(Credential credential, ResponseCallbackListener.ResponseCallbackSuccessListener<Credential> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final StringRequest request = new StringRequest(
                Request.Method.POST,
                API_URL,
                response -> {
                    resp.onResponse(new Gson().fromJson(response.toString(), new TypeToken<Credential>() {}.getType()));
                },
                error -> {
                    err.onError(error.toString());
                }
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void update(Credential credential, ResponseCallbackListener.ResponseCallbackSuccessListener<Credential> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final StringRequest request = new StringRequest(
                Request.Method.PUT,
                API_URL,
                response -> {
                    resp.onResponse(new Gson().fromJson(response.toString(), new TypeToken<Credential>() {}.getType()));
                },
                error -> {
                    err.onError(error.toString());
                }
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void deleteById(Integer credentialId, ResponseCallbackListener.ResponseCallbackSuccessListener<Boolean> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.DELETE,
                API_URL + "/" + credentialId,
                null,
                response -> {
                    resp.onResponse(new Gson().fromJson(response.toString(), new TypeToken<Boolean>() {}.getType()));
                },
                error -> {
                    err.onError(error.toString());
                }
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void findByUsername(final String username, final ResponseCallbackListener.ResponseCallbackSuccessListener<Credential> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                API_URL + "/username/" + username,
                null,
                response -> {
                    resp.onResponse(new Gson().fromJson(response.toString(), new TypeToken<Credential>() {}.getType()));
                },
                error -> {
                    err.onError(error.toString());
                }
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }


}
