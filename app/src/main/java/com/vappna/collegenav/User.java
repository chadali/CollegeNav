package com.vappna.collegenav;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by vnair on 11/14/2015.
 */
public class User {

    private String username;
    private String password;

    private String homeCollege;

    private ArrayList<String> friends;

    private LatLng userLocation;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.friends = null;
        userLocation = new LatLng(0,0);
    }

    public User(String username, String password, String homeCollege) {
        this.username = username;
        this.password = password;
        this.homeCollege = homeCollege;
        this.friends = null;
        userLocation = new LatLng(0,0);
    }

    public User(String username, String password, String homeCollege, ArrayList<String> friends) {
        this.username = username;
        this.password = password;
        this.homeCollege = homeCollege;
        this.friends = friends;
        userLocation = new LatLng(0,0);
    }

    public User(String username, String password, String homeCollege, ArrayList<String> friends, LatLng userLocation) {
        this.username = username;
        this.password = password;
        this.homeCollege = homeCollege;
        this.friends = friends;
        this.userLocation = userLocation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHomeCollege() {
        return homeCollege;
    }

    public void setHomeCollege(String homeCollege) {
        this.homeCollege = homeCollege;
    }

    public ArrayList<String> getFriends() {
        return friends;
    }

    public void setFriends(ArrayList<String> friends) {
        this.friends = friends;
    }

    public LatLng getUserLocation() {
        return userLocation;
    }

    public void setUserLocation(LatLng userLocation) {
        this.userLocation = userLocation;
    }
}
