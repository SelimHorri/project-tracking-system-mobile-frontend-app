package com.selimhorri.pack.service.impl.dynmc;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.selimhorri.pack.constant.BackendApiUrlConstant;
import com.selimhorri.pack.listener.ResponseCallbackListener;
import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Location;
import com.selimhorri.pack.pattern.singleton.GsonSingletonPattern;
import com.selimhorri.pack.pattern.singleton.QueueSingletonPattern;
import com.selimhorri.pack.service.LocationService;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LocationServiceDynamicImpl implements LocationService {

    private static final String API_URL = BackendApiUrlConstant.LocationBackendUrl.LOCATION_API_URL;
    private static final Gson gson = GsonSingletonPattern.getInstance().configDeserialization("dd-MM-yyyy");
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
                response -> resp.onResponse(gson.fromJson(response.toString(), new TypeToken<DtoCollection<Location>>() {}.getType())),
                error -> err.onError(error.getMessage())
        );
        QueueSingletonPattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void findById(Integer locationId, ResponseCallbackListener.ResponseCallbackSuccessListener<Location> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.GET,
                API_URL + "/" + locationId,
                null,
                response -> resp.onResponse(gson.fromJson(response.toString(), Location.class)),
                error -> err.onError(error.getMessage())
        );
        QueueSingletonPattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void save(Location location, ResponseCallbackListener.ResponseCallbackSuccessListener<Location> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final Map<String, Object> map = new HashMap<>();
        map.put("adr", location.getAdr());
        map.put("postalCode", location.getPostalCode());
        map.put("city", location.getCity());

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                API_URL,
                new JSONObject(map),
                response -> resp.onResponse(gson.fromJson(response.toString(), Location.class)),
                error -> err.onError(error.getMessage())
        );
        QueueSingletonPattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void update(Location location, ResponseCallbackListener.ResponseCallbackSuccessListener<Location> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final Map<String, Object> map = new HashMap<>();
        map.put("locationId", location.getLocationId());
        map.put("adr", location.getAdr());
        map.put("postalCode", location.getPostalCode());
        map.put("city", location.getCity());

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.PUT,
                API_URL,
                new JSONObject(map),
                response -> resp.onResponse(gson.fromJson(response.toString(), Location.class)),
                error -> err.onError(error.getMessage())
        );
        QueueSingletonPattern.getInstance(this.context).addToRequestQueue(request);

    }

    @Override
    public void deleteById(Integer locationId, ResponseCallbackListener.ResponseCallbackSuccessListener<Boolean> resp, ResponseCallbackListener.ResponseCallbackErrorListener err) {

        final JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.DELETE,
                API_URL + "/" + locationId,
                null,
                response -> resp.onResponse(gson.fromJson(response.toString(), Boolean.class)),
                error -> err.onError(error.getMessage())
        );
        QueueSingletonPattern.getInstance(this.context).addToRequestQueue(request);

    }



}
