package com.selimhorri.pack.service.impl.dynmc;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.selimhorri.pack.constant.BackendApiUrlConstant;
import com.selimhorri.pack.listener.ResponseCallbackListener;
import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Credential;
import com.selimhorri.pack.pattern.GsonPattern;
import com.selimhorri.pack.pattern.QueuePattern;
import com.selimhorri.pack.service.CredentialService;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class CredentialServiceDynamicImpl implements CredentialService {

    private static final String API_URL = BackendApiUrlConstant.CredentialBackendUrl.CREDENTIAL_API_URL;
    private static final Gson gson = GsonPattern.getInstance().configDeserialization("dd/MM/yyyy");
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
                response -> resp.onResponse(gson.fromJson(response.toString(), new TypeToken<DtoCollection<Credential>>() {}.getType())),
                error -> err.onError(error.getMessage())
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void findById(Integer credentialId, ResponseCallbackListener.ResponseCallbackSuccessListener<Credential> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                API_URL + "/" + credentialId,
                null,
                response -> resp.onResponse(gson.fromJson(response.toString(), Credential.class)),
                error -> err.onError(error.getMessage())
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void save(Credential credential, ResponseCallbackListener.ResponseCallbackSuccessListener<Credential> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final Map<String, Object> map = new HashMap<>();
        map.put("username", credential.getUsername());
        map.put("password", credential.getPassword());
        map.put("enabled", credential.getEnabled());
        map.put("role", credential.getRole());

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                API_URL,
                new JSONObject(map),
                response -> resp.onResponse(gson.fromJson(response.toString(), Credential.class)),
                error -> err.onError(error.getMessage())
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void update(Credential credential, ResponseCallbackListener.ResponseCallbackSuccessListener<Credential> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final Map<String, Object> map = new HashMap<>();
        map.put("credentialId", credential.getCredentialId());
        map.put("username", credential.getUsername());
        map.put("password", credential.getPassword());
        map.put("enabled", credential.getEnabled());
        map.put("role", credential.getRole());

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.PUT,
                API_URL,
                new JSONObject(map),
                response -> resp.onResponse(gson.fromJson(response.toString(), Credential.class)),
                error -> err.onError(error.getMessage())
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void deleteById(Integer credentialId, ResponseCallbackListener.ResponseCallbackSuccessListener<Boolean> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.DELETE,
                API_URL + "/" + credentialId,
                null,
                response -> resp.onResponse(gson.fromJson(response.toString(), Boolean.class)),
                error -> err.onError(error.getMessage())
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void findByUsername(final String username, final ResponseCallbackListener.ResponseCallbackSuccessListener<Credential> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                API_URL + "/username/" + username,
                null,
                response -> resp.onResponse(gson.fromJson(response.toString(), Credential.class)),
                error -> err.onError(error.getMessage())
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }



}
