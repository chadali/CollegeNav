package com.vappna.collegenav;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vnair on 11/14/2015.
 */
public interface GetUserCallback {

    public abstract void done(User returnedUser);
    public abstract void doneRetrievingArray(List<String> returnedArray);
}
