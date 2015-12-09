package com.vappna.collegenav;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vnair on 11/14/2015.
 */
public interface GetUserCallback {

    public abstract void done(User returnedUser);
    public abstract void done(LatLng userLocation);
    public abstract void doneRetrievingArray(ArrayList<String> returnedArray);
}
