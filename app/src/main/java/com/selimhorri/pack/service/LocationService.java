package com.selimhorri.pack.service;

import com.selimhorri.pack.model.collection.DtoCollection;
import com.selimhorri.pack.model.dto.Location;

public interface LocationService {

    DtoCollection<Location> findAll();
    Location findById(final Integer locationId);
    Location save(final Location location);
    Location update(final Location location);
    void deleteById(final Integer locationId);

}
