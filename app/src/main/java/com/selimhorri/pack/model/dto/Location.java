package com.selimhorri.pack.model.dto;

import java.util.Objects;

public class Location {

    private Integer locationId;
    private String adr;
    private String postalCode;
    private String city;

    public Location() {

    }

    public Location(String adr, String postalCode, String city) {
        this.adr = adr;
        this.postalCode = postalCode;
        this.city = city;
    }

    public Location(Integer locationId, String adr, String postalCode, String city) {
        this.locationId = locationId;
        this.adr = adr;
        this.postalCode = postalCode;
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return Objects.equals(locationId, location.locationId) &&
                Objects.equals(adr, location.adr) &&
                Objects.equals(postalCode, location.postalCode) &&
                Objects.equals(city, location.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(locationId, adr, postalCode, city);
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId=" + locationId +
                ", adr='" + adr + '\'' +
                ", postalCode='" + postalCode + '\'' +
                ", city='" + city + '\'' +
                '}';
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public String getAdr() {
        return adr;
    }

    public void setAdr(String adr) {
        this.adr = adr;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
