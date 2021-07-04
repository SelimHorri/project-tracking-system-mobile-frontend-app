package com.selimhorri.pack.pattern.builder;

import com.selimhorri.pack.model.dto.Location;

public class LocationBuilder {

    private final Location location;

    public LocationBuilder() {
        this.location = new Location();
    }

    public LocationBuilder(final Location location) {
        this.location = location;
    }

    public Location build() {
        return this.location;
    }

    public LocationBuilder locationId(final int locationId) {
        this.location.setLocationId(locationId);
        return this;
    }

    public LocationBuilder adr(final String adr) {
        this.location.setAdr(adr);
        return this;
    }

    public LocationBuilder postalCode(final String postalCode) {
        this.location.setPostalCode(postalCode);
        return this;
    }

    public LocationBuilder city(final String city) {
        this.location.setAdr(city);
        return this;
    }

}
