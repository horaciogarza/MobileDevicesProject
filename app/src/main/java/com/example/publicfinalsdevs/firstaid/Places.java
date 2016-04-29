package com.example.publicfinalsdevs.firstaid;

/**
 * Created by diego on 24/04/2016.
 */
public class Places {
    private String id;
    private String placeId;
    private String lat;
    private String lng;
    private String name;
    private String address;

    public Places(String placeId, String address, String name, String lat, String lng) {
        this.placeId = placeId;
        this.address = address;
        this.name = name;
        this.lng = lng;
        this.lat = lat;
    }

    public Places(String id, String placeId, String address, String name, String lat, String lng) {
        this.id = id;
        this.placeId = placeId;
        this.lat = lat;
        this.lng = lng;
        this.name = name;
        this.address = address;
    }

    public Places(String placeId, String lat, String lng) {
        this.placeId = placeId;
        this.lat = lat;
        this.lng = lng;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public String getPlaceId() {
        return placeId;
    }

    public String getLat() {
        return lat;
    }

    public String getLng() {
        return lng;
    }

    public String getId(){ return id;}

    @Override
    public String toString() {
        return "Places{" +
                "placeId='" + placeId + '\'' +
                ", lat='" + lat + '\'' +
                ", lng='" + lng + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
