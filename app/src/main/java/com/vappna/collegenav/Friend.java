package com.vappna.collegenav;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by vnair on 12/5/2015.
 */
public class Friend {
    private String username, homeCollege;
    private LatLng userLocation;

    public Friend(String username){
        this.username = username;
    }
    public Friend(String username, String homeCollege){
        this.username = username;
        this.homeCollege = homeCollege;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getHomeCollege() {
        return homeCollege;
    }

    public void setHomeCollege(String homeCollege) {
        this.homeCollege = homeCollege;
    }

    public LatLng getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(LatLng userLocation) {
        this.userLocation = userLocation;
    }
}
