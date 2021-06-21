package com.selimhorri.pack.service.impl.dynmc;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.selimhorri.pack.constant.BackendApiUrlConstant;
import com.selimhorri.pack.listener.ResponseCallbackListener;
import com.selimhorri.pack.model.dto.custom.AuthenticationRequest;
import com.selimhorri.pack.model.dto.custom.AuthenticationResponse;
import com.selimhorri.pack.pattern.singleton.GsonSingletonPattern;
import com.selimhorri.pack.pattern.singleton.QueueSingletonPattern;
import com.selimhorri.pack.service.AuthenticationService;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class AuthenticationServiceDynamicImpl implements AuthenticationService {

    private static final String API_URL = BackendApiUrlConstant.AuthenticationBackendUrl.AUTHENTICATE_API_URL;
    private static final Gson gson = GsonSingletonPattern.getInstance().configDeserialization("dd/MM/yyyy");
    private final Context context;

    public AuthenticationServiceDynamicImpl(final Context context) {
        this.context = context;
    }

    @Override
    public void authenticate(final AuthenticationRequest authenticationRequest, final ResponseCallbackListener.ResponseCallbackSuccessListener<AuthenticationResponse> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final Map<String, String> bodyMap = new HashMap<>();
        bodyMap.put("username", authenticationRequest.getUsername());
        bodyMap.put("password", authenticationRequest.getPassword());

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                API_URL,
                new JSONObject(bodyMap),
                response -> resp.onResponse(gson.fromJson(response.toString(), AuthenticationResponse.class)),
                error -> err.onError(error.getMessage())
        );
        QueueSingletonPattern.getInstance(this.context).addToRequestQueue(request);
    }



}
