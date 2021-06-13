package com.selimhorri.pack.service.impl.dynmc;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.selimhorri.pack.listener.ResponseCallbackListener;
import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Location;
import com.selimhorri.pack.pattern.QueuePattern;
import com.selimhorri.pack.service.LocationService;

public class LocationServiceDynamicImpl implements LocationService {

    private static final String API_URL = "https://project-tracking-system-heroku.herokuapp.com/app/api/locations";
    private final Context context;

    public LocationServiceDynamicImpl(final Context context) {
        this.context = context;
    }

    @Override
    public void findAll(final ResponseCallbackListener.ResponseCallbackSuccessListener<DtoCollection<Location>> resp, final ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                API_URL,
                null,
                response -> {
                    resp.onResponse(new Gson().fromJson(response.toString(), new TypeToken<DtoCollection<Location>>() {}.getType()));
                },
                error -> {
                    err.onError(error.toString());
                }
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void findById(Integer locationId, ResponseCallbackListener.ResponseCallbackSuccessListener<Location> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                API_URL + "/" + locationId,
                null,
                response -> {
                    resp.onResponse(new Gson().fromJson(response.toString(), Location.class));
                },
                error -> {
                    err.onError(error.toString());
                }
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void save(Location location, ResponseCallbackListener.ResponseCallbackSuccessListener<Location> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final StringRequest request = new StringRequest(
                Request.Method.POST,
                API_URL,
                response -> {
                    resp.onResponse(new Gson().fromJson(response.toString(), Location.class));
                },
                error -> {
                    err.onError(error.toString());
                }
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void update(Location location, ResponseCallbackListener.ResponseCallbackSuccessListener<Location> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final StringRequest request = new StringRequest(
                Request.Method.PUT,
                API_URL,
                response -> {
                    resp.onResponse(new Gson().fromJson(response.toString(), Location.class));
                },
                error -> {
                    err.onError(error.toString());
                }
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void deleteById(Integer locationId, ResponseCallbackListener.ResponseCallbackSuccessListener<Boolean> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.DELETE,
                API_URL + "/" + locationId,
                null,
                response -> {
                    resp.onResponse(new Gson().fromJson(response.toString(), Boolean.class));
                },
                error -> {
                    err.onError(error.toString());
                }
        );
        QueuePattern.getInstance(this.context).addToRequestQueue(request);

    }
}
