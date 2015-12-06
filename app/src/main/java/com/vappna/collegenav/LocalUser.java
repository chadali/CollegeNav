package com.vappna.collegenav;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by vnair on 11/14/2015.
 */
public class LocalUser {

    public static final String SP_Name = "userDetails";
    SharedPreferences preferences;


    public LocalUser(Context cxt) {
        preferences = cxt.getSharedPreferences(SP_Name, 0);
    }

    public void storeUserData(User user) {
        SharedPreferences.Editor spEditor = preferences.edit();
        spEditor.putString("username", user.getUsername());
        spEditor.putString("password", user.getPassword());
        spEditor.putString("homeCollege", user.getHomeCollege());
        if(user.getFriends() == null){
            spEditor.putStringSet("friends", new HashSet<String>());
        }
        else{
            spEditor.putStringSet("friends", new HashSet<String>(user.getFriends()));
        }
        spEditor.commit();
    }

    public User getLoggedInUser() {
        String username = preferences.getString("username", "");
        String password = preferences.getString("password", "");
        String homeCollege = preferences.getString("homeCollege", "");
        Set<String> friends = preferences.getStringSet("friends", new Set<String>() {
            @Override
            public boolean add(String object) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends String> collection) {
                return false;
            }

            @Override
            public void clear() {

            }

            @Override
            public boolean contains(Object object) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @NonNull
            @Override
            public Iterator<String> iterator() {
                return null;
            }

            @Override
            public boolean remove(Object object) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> collection) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> collection) {
                return false;
            }

            @Override
            public int size() {
                return 0;
            }

            @NonNull
            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @NonNull
            @Override
            public <T> T[] toArray(T[] array) {
                return null;
            }
        });
        User storedUser = new User(username, password, homeCollege, new ArrayList<String>(friends));

        return storedUser;
    }

    public void setUserLoggedIn(boolean loggedIn) {
        SharedPreferences.Editor spEditor = preferences.edit();
        spEditor.putBoolean("loggedIn", loggedIn);
        spEditor.commit();
    }

    public boolean getUserLoggedIn() {
        if (preferences.getBoolean("loggedIn", false)) {
            return true;
        } else {
            return false;
        }
    }

    public void clearUserData() {
        SharedPreferences.Editor spEditor = preferences.edit();
        spEditor.clear();
        spEditor.commit();
    }

}
