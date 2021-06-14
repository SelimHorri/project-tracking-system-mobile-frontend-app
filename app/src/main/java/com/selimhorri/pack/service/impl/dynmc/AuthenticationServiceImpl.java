package com.selimhorri.pack.service.impl.dynmc;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.selimhorri.pack.listener.ResponseCallbackListener;
import com.selimhorri.pack.model.dto.custom.AuthenticationResponse;
import com.selimhorri.pack.pattern.QueuePattern;
import com.selimhorri.pack.service.AuthenticationService;

public class AuthenticationServiceImpl implements AuthenticationService {

    private static final String API_URL = "https://project-tracking-system-heroku.herokuapp.com/app/api/authenticate";
    private final Context context;

    public AuthenticationServiceImpl(final Context context) {
        this.context = context;
    }

    @Override
    public void authenticate(final ResponseCallbackListener.ResponseCallbackSuccessListener<AuthenticationResponse> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                API_URL,
                null,
                response -> resp.onResponse(new Gson().fromJson(response.toString(), AuthenticationResponse.class)),
                error -> err.onError(error.toString())
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);
    }



}
