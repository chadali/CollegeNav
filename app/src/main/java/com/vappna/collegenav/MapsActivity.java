package com.vappna.collegenav;

import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

public class MapsActivity extends FragmentActivity implements LocationListener {

    private GoogleMap guilfordMap, otherMap; // Might be null if Google Play services APK is not available.
    FindCollegeActivity mA = new FindCollegeActivity();
    private String collegeID;
    Toolbar toolbar;
    public static final int GUILFORD_COLLEGE_COLOR = Color.rgb(128, 0, 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        Bundle infoBundle = getIntent().getExtras();
        collegeID = infoBundle.getString("collegeID");
        if (collegeID.equals("Guilford College")) {
            setUpGuilfordMapIfNeeded();

        } else {
            setUpOtherMapIfNeeded();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (collegeID == mA.GUICOL) {
            setUpGuilfordMapIfNeeded();
        } else {
            setUpOtherMapIfNeeded();
        }
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpGuilfordMap()} once when {@link #guilfordMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpGuilfordMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (guilfordMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            guilfordMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (guilfordMap != null) {
                setUpGuilfordMap();
                guilfordMap.setMyLocationEnabled(true);
                LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
                Criteria criteria = new Criteria();
                String bestProvider = locationManager.getBestProvider(criteria, true);
                Location location = locationManager.getLastKnownLocation(bestProvider);
                if (location != null) {
                    onLocationChanged(location);
                }
                locationManager.requestLocationUpdates(bestProvider, 20000, 0, this);
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        double latitude = location.getLatitude();
        double longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);

    }

    @Override
    public void onProviderDisabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onProviderEnabled(String provider) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        // TODO Auto-generated method stub
    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }

    private void setUpOtherMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (otherMap == null) {
            // Try to obtain the map from the SupportMapFragment.
            otherMap = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (otherMap != null) {
                setUpOtherMap();
            }
        }
    }

    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #guilfordMap} is not null.
     */
    private void setUpGuilfordMap() {
        int collegeBackgroundColor = Color.argb(40, 128, 0, 0);
        int polygonStrokeColor = Color.BLACK;
        int buildingStrokeWidth = 1;
        int buildingFillColor = Color.argb(63, 128, 0, 0);
        int sidewalkStrokeWidth = 3;
        int sidewalkStrokeColor = Color.argb(127, 128, 0, 0);

        guilfordMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        guilfordMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.093422, -79.887920), 17.0f));
        // TODO Fix the Map to include all boundaries of Guilford College
        Polygon guilfordCollege = guilfordMap.addPolygon(new PolygonOptions()
                .add(new LatLng(36.089202, -79.884470), new LatLng(36.089881, -79.888889), new LatLng(36.092057, -79.889801), new LatLng(36.094724, -79.891402), new LatLng(36.098666, -79.891767), new LatLng(36.098341, -79.881383), new LatLng(36.092699, -79.881681), new LatLng(36.089202, -79.884470))
                .strokeColor(polygonStrokeColor)
                .strokeWidth(5)
                .fillColor(collegeBackgroundColor));
        // This section is reserved for Markers
        Marker kingHallMarker = guilfordMap.addMarker(new MarkerOptions()
                .position(new LatLng(36.093396, -79.889402))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.academic_icon))
                .title("King Hall")
                .snippet("King Hall is a classroom building and includes the Study Abroad Office, " +
                        "the Career Development Center, the Multicultural Resource Center, the Center " +
                        "for Principled Problem Solving, and the Office of the Academic Dean.\n" +
                        "Academic Affairs\n" +
                        "Business Administration, Justice and Policy Studies, Economics, Multicultural " +
                        "Education, Sociology & Anthropology, Psychology, and Women's Gender & Sexuality Studies\n" +
                        "Career Development Center\n" +
                        "Center for Prinicipled Problem Solving (CPPS)\n" +
                        "Conflict Resolution Resource Center (CRRC)\n" +
                        "Interdisciplinary Studies\n" +
                        "The Mutlicultural Education Department (MED)\n" +
                        "Bayard Austin Center for LGBTQA Activism, Education, & Reconciliation\n" +
                        "Africana, International Student, Latino, and Native American Communities\n" +
                        "Study Abroad")
                .draggable(false));
        Marker maryHobbsHallMarker = guilfordMap.addMarker(new MarkerOptions()
                .position(new LatLng(36.093880, -79.889271))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.residencehall))
                .title("Mary Hobbs Hall")
                .snippet("Mary Hobbs Hall houses approximately 54 female upper class residents. " +
                        "Average room size is 13 ft. 6 in. x 12 ft. This unique building is the " +
                        "oldest residential facility on campus and has been renovated numerous times " +
                        "over the years. The hall is air-conditioned with rooms on either side of " +
                        "long and short corridors with central bathroom facilities, a kitchen and " +
                        "laundry room in the basement and three lounges. The room furnishings include t" +
                        "he following for each resident: desk, desk chair, bed, and dresser, wardrobe " +
                        "or closet space. The lounges are carpeted and the halls and rooms have hardwood floors.")
                .draggable(false));
        Marker hendricksHallMarker = guilfordMap.addMarker(new MarkerOptions()
                .position(new LatLng(36.093801, -79.889981))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.academic_icon))
                .title("Hendricks Hall")
                .snippet("Hendricks Hall is home to the Office of Advancement, Human Resources and Finance.")
                .draggable(false));
        Marker binfordHall = guilfordMap.addMarker(new MarkerOptions()
                .position(new LatLng(36.094420, -79.889749))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.residencehall))
                .title("Binford Hall")
                .snippet("Binford is a co-ed residence hall that contains rooms for approximately 155 " +
                        "first-year students. Average room size is 17 ft. 4 in. x 11 ft. 9 in. The " +
                        "hall has central air-conditioning and heating, carpeted rooms, and central " +
                        "laundry facilities located in the basement. The building is designed in a " +
                        "T-shape and has bathroom facilities and lounges in the center of each floor " +
                        "and kitchens on the second and third floors. The room furnishings are " +
                        "built-in and include the following for each resident: desk, desk chair, " +
                        "bed, closet and dresser with generous storage space above the closet. " +
                        "Roommates share one cable TV connection. All residence halls have wireless " +
                        "internet connectivity.")
                .draggable(false));
        Marker frankFamilyScienceCenter = guilfordMap.addMarker(new MarkerOptions()
                .position(new LatLng(36.095038, -79.890070))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.academic_icon))
                .title("Frank Family Science Center")
                .snippet("Frank Family Science Center is home to the J. Donald Cline Observatory and " +
                        "contains classroom and lab space for Guilford's various science departments.\n" +
                        "Biology, Chemistry, Geology, and Physics\n" +
                        "Joseph M. Bryan Jr. Auditorium\n" +
                        "Cline Observatory\n" +
                        "Science Laboratories")
                .draggable(false));

        Marker haworthNSField = guilfordMap.addMarker(new MarkerOptions()
                .position(new LatLng(36.096479, -79.890840))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sports))
                .title("Haworth North/South Field")
                .snippet("")
                .draggable(false));
        Marker haworthEWField = guilfordMap.addMarker(new MarkerOptions()
                .position(new LatLng(36.096992, -79.889740))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sports))
                .title("Haworth East/West Field")
                .snippet("")
                .draggable(false));
        Marker haworthSoccerGameField = guilfordMap.addMarker(new MarkerOptions()
                .position(new LatLng(36.097838, -79.889425))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sports))
                .title("Haworth Soccer Game Field")
                .snippet("")
                .draggable(false));
        Marker haworthLightedField = guilfordMap.addMarker(new MarkerOptions()
                .position(new LatLng(36.097640, -79.890417))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sports))
                .title("Haworth Lighted Field")
                .snippet("")
                .draggable(false));
        Marker haworthSoftballField = guilfordMap.addMarker(new MarkerOptions()
                .position(new LatLng(36.097558, -79.891046))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sports))
                .title("Haworth Softball Field")
                .snippet("Guilford's softball team plays and practices on Haworth Field, located on " +
                        "the northwest corner of the College. The softball field was built in 2003 " +
                        "and has steadily received a number of enhancements, including a scoreboard, " +
                        "brick dugouts, a team room, increased bleacher seating and a new windscreen.")
                .draggable(false));
        Marker dorothyRagsdaleMcMichael37CentennialClassCourts = guilfordMap.addMarker(new MarkerOptions()
                .position(new LatLng(36.098333, -79.891078))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sports))
                .title("Dorthy Ragsdale McMichael '37 Centennial Class Courts")
                .snippet("The Quakers compete and practice on eight tennis courts built in 2008 on " +
                        "the northwest corner of the campus. The courts are surrounded by windscreens " +
                        "and have an adjoining building with restrooms and team meeting space.")
                .draggable(false));
        Marker guilfordMeadowsDiscGolfCourse = guilfordMap.addMarker(new MarkerOptions()
                .position(new LatLng(36.098864, -79.887466))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sports))
                .title("Guilford Meadows Disc Golf Course")
                .snippet("The Guilford Meadows Disc Golf Course at Guilford College is an 18-hole, " +
                        "4,255 feet disc golf course which features a combination of par 3 and par 4 " +
                        "hole distances.")
                .draggable(false));
        Marker theMeadowsAtGuilford = guilfordMap.addMarker(new MarkerOptions()
                .position(new LatLng(36.100370, -79.886682))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sports))
                .title("The Meadows at Guilford")
                .snippet("Home to Guilford's men's cross-country and women's cross country teams. " +
                        "The Meadows cross country course was completed in 2009 on the north/northwest " +
                        "part of the Guilford College campus. The all grass course accommodates cross " +
                        "country loops ranging in length from five- to eight-kilometers and features" +
                        " a few steady hills as it rolls through the Guilford Meadows.")
                .draggable(false));
        Marker alumniGym = guilfordMap.addMarker(new MarkerOptions()
                .position(new LatLng(36.100370, -79.886682))
                .icon(BitmapDescriptorFactory.fromResource(R.drawable.sports))
                .title("Alumni Gym")
                .snippet("Home to Guilford's men's cross-country and women's cross country teams. " +
                        "The Meadows cross country course was completed in 2009 on the north/northwest part of the Guilford College campus. The all grass course accommodates cross country loops ranging in length from five- to eight-kilometers and features a few steady hills as it rolls through the Guilford Meadows.")
                .draggable(false));

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private void setUpOtherMap() {

    }
}
