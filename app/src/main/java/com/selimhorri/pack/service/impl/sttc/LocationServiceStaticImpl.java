package com.selimhorri.pack.service.impl.sttc;

import android.content.Context;

import com.selimhorri.pack.exception.ObjectAlreadyExistsException;
import com.selimhorri.pack.exception.ObjectNotFoundException;
import com.selimhorri.pack.listener.ResponseCallbackListener;
import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Location;
import com.selimhorri.pack.model.dto.Location;
import com.selimhorri.pack.service.LocationService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

public class LocationServiceStaticImpl implements LocationService {

    private static final SortedMap<Integer, Location> DUMMY_LOCATIONS = new TreeMap<>();
    private final Context context;

    static {
        DUMMY_LOCATIONS.put(1, new Location(1, "RUE DE LA BOURSE", "2016", "LAC2"));
        DUMMY_LOCATIONS.put(2, new Location(2, "RUE DE BLA BLA", "2016", "CHARGUIA"));
    }

    public LocationServiceStaticImpl(final Context context) {
        this.context = context;
    }

    @Override
    public void findAll(final ResponseCallbackListener.ResponseCallbackSuccessListener<DtoCollection<Location>> response, final ResponseCallbackListener.ResponseCallbackErrorListener error) {

        final List<Location> list = new ArrayList<>();

        for (Map.Entry<Integer, Location> entry : DUMMY_LOCATIONS.entrySet())
            list.add(entry.getValue());

    }

    @Override
    public void findById(final Integer locationId, final ResponseCallbackListener.ResponseCallbackSuccessListener<Location> response, final ResponseCallbackListener.ResponseCallbackErrorListener error) {

        if (!DUMMY_LOCATIONS.containsKey(locationId))
            throw new ObjectNotFoundException("#### Location does not exist! ####");

    }

    @Override
    public void save(final Location location, final ResponseCallbackListener.ResponseCallbackSuccessListener<Location> response, final ResponseCallbackListener.ResponseCallbackErrorListener error) {

        if (DUMMY_LOCATIONS.containsKey(location.getLocationId()))
            throw new ObjectAlreadyExistsException("#### location exists already ####");

        DUMMY_LOCATIONS.put(location.getLocationId(), location);

    }

    @Override
    public void update(final Location location, final ResponseCallbackListener.ResponseCallbackSuccessListener<Location> response, final ResponseCallbackListener.ResponseCallbackErrorListener error) {

        if (!DUMMY_LOCATIONS.containsKey(location.getLocationId()))
            throw new ObjectNotFoundException("#### location does not exist ####");

        DUMMY_LOCATIONS.get(location.getLocationId()).setAdr(location.getAdr());
        DUMMY_LOCATIONS.get(location.getLocationId()).setPostalCode(location.getPostalCode());
        DUMMY_LOCATIONS.get(location.getLocationId()).setCity(location.getCity());

    }

    @Override
    public void deleteById(final Integer locationId, final ResponseCallbackListener.ResponseCallbackSuccessListener<Boolean> response, final ResponseCallbackListener.ResponseCallbackErrorListener error) {

        if (!DUMMY_LOCATIONS.containsKey(locationId))
            throw new ObjectNotFoundException("#### location does not exist! ####");

        DUMMY_LOCATIONS.remove(locationId);
    }



}
