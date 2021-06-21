package com.selimhorri.pack.service;

import com.selimhorri.pack.listener.ResponseCallbackListener;
import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Location;

public interface LocationService {

    void findAll(final ResponseCallbackListener.ResponseCallbackSuccessListener<DtoCollection<Location>> response, final ResponseCallbackListener.ResponseCallbackErrorListener error);
    void findById(final Integer locationId, final ResponseCallbackListener.ResponseCallbackSuccessListener<Location> response, final ResponseCallbackListener.ResponseCallbackErrorListener error);
    void save(final Location location, final ResponseCallbackListener.ResponseCallbackSuccessListener<Location> response, final ResponseCallbackListener.ResponseCallbackErrorListener error);
    void update(final Location location, final ResponseCallbackListener.ResponseCallbackSuccessListener<Location> response, final ResponseCallbackListener.ResponseCallbackErrorListener error);
    void deleteById(final Integer locationId, final ResponseCallbackListener.ResponseCallbackSuccessListener<Boolean> response, final ResponseCallbackListener.ResponseCallbackErrorListener error);

}
