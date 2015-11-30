package com.vappna.collegenav;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by vnair on 11/14/2015.
 */
public class LocalUser {

    public static final String SP_Name = "userDetails";
    SharedPreferences preferences;


    public LocalUser(Context cxt){
        preferences = cxt.getSharedPreferences(SP_Name, 0);
    }

    public void storeUserData(User user){
        SharedPreferences.Editor spEditor = preferences.edit();
        spEditor.putString("username", user.getUsername());
        spEditor.putString("password", user.getPassword());
        spEditor.putString("homeCollege", user.getHomeCollege());
        spEditor.commit();
    }

    public User getLoggedInUser(){
        String username = preferences.getString("username", "");
        String password = preferences.getString("password", "");
        String homeCollege = preferences.getString("homeCollege", "");
        User storedUser = new User(username, password, homeCollege);

        return storedUser;
    }

    public void setUserLoggedIn(boolean loggedIn){
        SharedPreferences.Editor spEditor = preferences.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public boolean getUserLoggedIn(){
        if (preferences.getBoolean("loggedIn", false)){
            return true;
        }
        else{
            return false;
        }
    }

    public void clearUserData(){
        SharedPreferences.Editor spEditor = preferences.edit();
        spEditor.clear();
        spEditor.commit();
    }

}
