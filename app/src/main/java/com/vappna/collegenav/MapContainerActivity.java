package com.vappna.collegenav;

import android.app.Dialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class MapContainerActivity extends ActionBarActivity {

    Colors colors;
    Strings strings;

    HorizontalScrollView buttonLayout;
    Toolbar toolbar;

    Bundle infoBundle;

    String collegeID;
    String receiverActivityName;
    String listItemString;
    GuilfordCollegeMapFragment gcMapFragment;
    UNCCHMapFragment uncchMapFragment;
    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    LinearLayout academicIconLayout, residenceIconLayout, athleticIconLayout, diningIconLayout, healthIconLayout;
    ImageView academicIcon, residenceIcon, athleticIcon, diningIcon, healthIcon;
    TextView academicIconText, residenceIconText, athleticIconText, diningIconText, healthIconText;
    boolean isAcademicMarkerVisible, isResidenceMarkerVisible, isAthleticMarkerVisible, isHealthMarkerVisible, isDiningMarkerVisible;

    NavigationDrawerFragment drawerFragment;

    LatLng usersLocation, friendLocation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_container);
        colors = new Colors();
        infoBundle = getIntent().getExtras();
        LocalUser user = new LocalUser(this);
        collegeID = user.getLoggedInUser().getHomeCollege();
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setUpPage(collegeID);
        setSupportActionBar(toolbar);
        setUpNavigationDrawer();

        ServerRequest request = new ServerRequest(MapContainerActivity.this);
        request.getUserFriendsInBackground(new LocalUser(MapContainerActivity.this).getLoggedInUser(), new GetUserCallback() {
            @Override
            public void done(User returnedUser) {

            }

            @Override
            public void done(LatLng userLocation) {

            }

            @Override
            public void doneRetrievingArray(final ArrayList<String> returnedArray) {

                if (gcMapFragment != null) {
                    for (int i = 0; i < returnedArray.size(); i++) {
                        Friend friend = new Friend(returnedArray.get(i));
                        final int x = i;
                        new ServerRequest(MapContainerActivity.this).getUserLocationInBackground(friend, new GetUserCallback() {
                            @Override
                            public void done(User returnedUser) {

                            }

                            @Override
                            public void done(LatLng userLocation) {
                                gcMapFragment.addMarkers(new Friend(returnedArray.get(x)), userLocation);
                            }

                            @Override
                            public void doneRetrievingArray(ArrayList<String> returnedArray) {

                            }
                        });
                    }
                } else if (uncchMapFragment != null) {
                    for (int i = 0; i < returnedArray.size(); i++) {
                        Friend friend = new Friend(returnedArray.get(i));
                        final int x = i;
                        new ServerRequest(MapContainerActivity.this).getUserLocationInBackground(friend, new GetUserCallback() {
                            @Override
                            public void done(User returnedUser) {

                            }

                            @Override
                            public void done(LatLng userLocation) {
                                if(userLocation != null) {
                                    uncchMapFragment.addMarkers(new Friend(returnedArray.get(x)), userLocation);
                                }
                            }

                            @Override
                            public void doneRetrievingArray(ArrayList<String> returnedArray) {

                            }
                        });
                    }
                }
            }
        });
    }

    @Override
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        // getIntent() should always return the most recent
        setIntent(intent);
    }

    private void setUpNavigationDrawer() {
        drawerFragment = (NavigationDrawerFragment) getSupportFragmentManager().findFragmentById(R.id.fragment_navigation_drawer);
        drawerFragment.setUp(R.id.fragment_navigation_drawer, (DrawerLayout) findViewById(R.id.drawer_layout), toolbar);
    }

    public void showDirectionsDialog() {
        Dialog dialog = new Dialog(MapContainerActivity.this);
        dialog.setContentView(R.layout.directions_dialog_layout);
        dialog.setTitle("Directions: ");
        dialog.setCancelable(true);

        Button toHereButton = (Button) dialog.findViewById(R.id.to_here_button);
        Button fromHereButton = (Button) dialog.findViewById(R.id.from_here_button);

        toHereButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        fromHereButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        dialog.show();
    }

    private void setUpPage(String collegeID) {
        if (collegeID.equals(strings.getGUICOL())) {
            gcMapFragment = new GuilfordCollegeMapFragment();
            setUpToolbar(strings.getGUICOL(), Colors.getGuilfordCollegeColor());
            setUpFragment(gcMapFragment);
            setUpButtonLayout(colors.getGuilfordCollegeColorClear());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(getResources().getColor(R.color.GuilfordPrimaryDark));
                window.setNavigationBarColor(getResources().getColor(R.color.GuilfordPrimary));
            }
        }
        if (collegeID.equals(strings.getUNCCH())) {
            uncchMapFragment = new UNCCHMapFragment();
            setUpToolbar(strings.getUNCCH(), colors.getUncchColor());
            setUpFragment(uncchMapFragment);
            setUpButtonLayout(colors.getUncchColorClear());
        }
    }

    private void setUpFragment(Fragment fragment) {
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.map_fragment_container, fragment);
        fragmentTransaction.commit();
    }

    private void setUpButtonLayout(int color) {
        buttonLayout = (HorizontalScrollView) findViewById(R.id.map_label_selections);
        buttonLayout.setBackgroundColor(color);

        academicIconLayout = (LinearLayout) findViewById(R.id.school_icon_layout);
        residenceIconLayout = (LinearLayout) findViewById(R.id.residential_icon_layout);
        athleticIconLayout = (LinearLayout) findViewById(R.id.sports_icon_layout);
        diningIconLayout = (LinearLayout) findViewById(R.id.dining_icon_layout);
        healthIconLayout = (LinearLayout) findViewById(R.id.health_icon_layout);

        setUpButtonViews();

        isAcademicMarkerVisible = true;
        academicIconLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAcademicMarkerVisible) {
                    if (collegeID.equals(strings.getGUICOL())) {
                        gcMapFragment.setMarkerVisibilityFalse("academicMarker");
                    }
                    if (collegeID.equals(strings.getUNCCH())) {
                        uncchMapFragment.setMarkerVisibilityFalse("academicMarker");
                    }
                    setButtonAlpha(academicIcon, academicIconText, 0.4f);
                    isAcademicMarkerVisible = false;
                } else if (!isAcademicMarkerVisible) {
                    if (collegeID.equals(strings.getGUICOL())) {
                        gcMapFragment.setMarkerVisibilityTrue("academicMarker");
                    }
                    if (collegeID.equals(strings.getUNCCH())) {
                        uncchMapFragment.setMarkerVisibilityTrue("academicMarker");
                    }
                    setButtonAlpha(academicIcon, academicIconText, 1.0f);
                    isAcademicMarkerVisible = true;
                }
            }
        });

        isResidenceMarkerVisible = true;
        residenceIconLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isResidenceMarkerVisible) {
                    if(collegeID.equals(strings.getGUICOL())){
                        gcMapFragment.setMarkerVisibilityFalse("residenceMarker");
                    }
                    if(collegeID.equals(strings.getUNCCH())){
                        uncchMapFragment.setMarkerVisibilityFalse("residenceMarker");
                    }
                    setButtonAlpha(residenceIcon, residenceIconText, 0.4f);
                    isResidenceMarkerVisible = false;
                } else if (!isResidenceMarkerVisible) {
                    if(collegeID.equals(strings.getGUICOL())){
                        gcMapFragment.setMarkerVisibilityTrue("residenceMarker");
                    }
                    if(collegeID.equals(strings.getUNCCH())){
                        uncchMapFragment.setMarkerVisibilityTrue("residenceMarker");
                    }
                    setButtonAlpha(residenceIcon, residenceIconText, 1.0f);
                    isResidenceMarkerVisible = true;
                }
            }
        });

        isAthleticMarkerVisible = true;
        athleticIconLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isAthleticMarkerVisible) {
                    if(collegeID.equals(strings.getGUICOL())){
                        gcMapFragment.setMarkerVisibilityFalse("athleticMarker");
                    }
                    if(collegeID.equals(strings.getUNCCH())){
                        uncchMapFragment.setMarkerVisibilityFalse("athleticMarker");
                    }
                    setButtonAlpha(athleticIcon, athleticIconText, 0.4f);
                    isAthleticMarkerVisible = false;
                } else if (!isAthleticMarkerVisible) {
                    if(collegeID.equals(strings.getGUICOL())){
                        gcMapFragment.setMarkerVisibilityTrue("athleticMarker");
                    }
                    if(collegeID.equals(strings.getUNCCH())){
                        uncchMapFragment.setMarkerVisibilityTrue("athleticMarker");
                    }
                    setButtonAlpha(athleticIcon, athleticIconText, 1.0f);
                    isAthleticMarkerVisible = true;
                }
            }
        });

        isDiningMarkerVisible = true;
        diningIconLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isDiningMarkerVisible) {
                    if(collegeID.equals(strings.getGUICOL())){
                        gcMapFragment.setMarkerVisibilityFalse("diningMarker");
                    }
                    if(collegeID.equals(strings.getUNCCH())){
                        uncchMapFragment.setMarkerVisibilityFalse("diningMarker");
                    }
                    setButtonAlpha(diningIcon, diningIconText, 0.4f);
                    isDiningMarkerVisible = false;
                } else if (!isDiningMarkerVisible) {
                    if(collegeID.equals(strings.getGUICOL())){
                        gcMapFragment.setMarkerVisibilityTrue("diningMarker");
                    }
                    if(collegeID.equals(strings.getUNCCH())){
                        uncchMapFragment.setMarkerVisibilityTrue("diningMarker");
                    }
                    setButtonAlpha(diningIcon, diningIconText, 1.0f);
                    isDiningMarkerVisible = true;
                }
            }
        });

        isHealthMarkerVisible = true;
        healthIconLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isHealthMarkerVisible) {
                    if(collegeID.equals(strings.getGUICOL())){
                        gcMapFragment.setMarkerVisibilityFalse("healthMarker");
                    }
                    if(collegeID.equals(strings.getUNCCH())){
                        uncchMapFragment.setMarkerVisibilityFalse("healthMarker");
                    }
                    setButtonAlpha(healthIcon, healthIconText, 0.4f);
                    isHealthMarkerVisible = false;
                } else if (!isHealthMarkerVisible) {
                    if(collegeID.equals(strings.getGUICOL())){
                        gcMapFragment.setMarkerVisibilityTrue("healthMarker");
                    }
                    if(collegeID.equals(strings.getUNCCH())){
                        uncchMapFragment.setMarkerVisibilityTrue("healthMarker");
                    }
                    setButtonAlpha(healthIcon, healthIconText, 1.0f);
                    isHealthMarkerVisible = true;
                }
            }
        });

    }

    private void setUpButtonViews() {
        academicIcon = (ImageView) findViewById(R.id.school_icon_button);
        academicIconText = (TextView) findViewById(R.id.school_icon_text);
        residenceIcon = (ImageView) findViewById(R.id.residential_icon_button);
        residenceIconText = (TextView) findViewById(R.id.residential_icon_text);
        athleticIcon = (ImageView) findViewById(R.id.sports_icon_button);
        athleticIconText = (TextView) findViewById(R.id.sports_icon_text);
        diningIcon = (ImageView) findViewById(R.id.dining_icon_button);
        diningIconText = (TextView) findViewById(R.id.dining_icon_text);
        healthIcon = (ImageView) findViewById(R.id.health_icon_button);
        healthIconText = (TextView) findViewById(R.id.health_icon_text);
    }

    private void setButtonAlpha(ImageView icon, TextView iconText, Float alpha) {
        icon.setAlpha(alpha);
        iconText.setAlpha(alpha);
    }

    private void setUpToolbar(String collegeName, int backgroundColor) {
        toolbar.setTitle(collegeName);
        toolbar.setBackgroundColor(backgroundColor);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.bringToFront();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        // Associate searchable configuration with the SearchView
     /*   SearchManager searchManager =
                (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView =
                (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(
                searchManager.getSearchableInfo(getComponentName())); */
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_search) {
            if (collegeID.equals(Strings.getGUICOL())) {
                Intent intent = new Intent(this, SearchActivity.class);
                startActivity(intent);
            }

        }
        if (id == R.id.send_location) {
            if (collegeID.equals(Strings.getGUICOL())) {
                usersLocation = gcMapFragment.getUserLocation();
            }
            if (collegeID.equals(Strings.getUNCCH())) {
                usersLocation = uncchMapFragment.getUserLocation();
            }
            ServerRequest serverRequest = new ServerRequest(MapContainerActivity.this);
            serverRequest.storeUserLocationInBackground(new LocalUser(getApplicationContext()).getLoggedInUser(), usersLocation, new GetUserCallback() {
                @Override
                public void done(User returnedUser) {

                }
                @Override
                public void done(LatLng userLocation) {

                }

                @Override
                public void doneRetrievingArray(ArrayList<String> returnedArray) {

                }
            });


        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onResume() {
        super.onResume();
        receiverActivityName = infoBundle.getString("activityName");
        if (receiverActivityName.equals("SearchActivity")) {
            listItemString = infoBundle.getString("position");
            gcMapFragment.setMapTarget(listItemString);
        }
    }
    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(this, UserHomeActivity.class);
        startActivity(setIntent);
    }
}
