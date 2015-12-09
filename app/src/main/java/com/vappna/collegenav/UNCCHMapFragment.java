package com.vappna.collegenav;

import android.animation.ValueAnimator;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*A fragment that launches other parts of the demo application.
*/
public class UNCCHMapFragment extends Fragment implements LocationListener {

    MapView mMapView;
    private GoogleMap uncchMap;

    String[] uncchAcademicTitlesArray;
    List<String> uncchAcademicTitles;

    String[] uncchAthleticTitlesArray;
    List<String> uncchAthleticTitles;

    String[] uncchResidenceTitlesArray;
    List<String> uncchResidenceTitles;

    String[] uncchDiningTitlesArray;
    List<String> uncchDiningTitles;

    ArrayList<LatLng> uncchAcademicLatLng, uncchResidenceLatLng, uncchAthleticLatLng, uncchDiningLatLng;

    String[] uncchAcademicDescriptionArray, uncchAthleticDescriptionArray, uncchResidenceDescriptionArray;
    List<String> uncchAcademicDescription, uncchAthleticDescription, uncchResidenceDescription;

    ArrayList<Marker> uncchAcademicMarkerArray, uncchResidenceMarkerArray, uncchAthleticMarkerArray, uncchDiningMarkerArray;

    Marker uncchAcademicMarker, uncchResidenceMarker, uncchAthleticMarker, uncchDiningMarker;
    MapContainerActivity MCA;

    Context cxt;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // inflat and return the layout
        View v = inflater.inflate(R.layout.fragment_location_info, container,
                false);

        cxt = getActivity().getApplicationContext();
        mMapView = (MapView) v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);
        mMapView.onResume();// needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        uncchMap = mMapView.getMap();
        uncchMap.setMyLocationEnabled(true);
        setUpArrays();
        setUpLatLng();
        setUpMap();
        uncchMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                // getActivity().showDialog();
            }
        });
        return v;
    }

    private void setUpArrays() {
        uncchAcademicTitlesArray = getResources().getStringArray(R.array.uncch_academic_administrative_building_names);
        uncchAcademicTitles = Arrays.asList(uncchAcademicTitlesArray);
        uncchAcademicDescriptionArray = getResources().getStringArray(R.array.uncch_academic_administrative_building_descriptions);
        uncchAcademicDescription = Arrays.asList(uncchAcademicDescriptionArray);
        uncchAcademicMarkerArray = new ArrayList<Marker>();
        uncchResidenceTitlesArray = getResources().getStringArray(R.array.uncch_residential_building_names);
        uncchResidenceTitles = Arrays.asList(uncchResidenceTitlesArray);
        uncchResidenceDescriptionArray = getResources().getStringArray(R.array.uncch_residential_building_descriptions);
        uncchResidenceDescription = Arrays.asList(uncchResidenceDescriptionArray);
        uncchResidenceMarkerArray = new ArrayList<Marker>();

        uncchAthleticTitlesArray = getResources().getStringArray(R.array.uncch_sports_and_recreational_names);
        uncchAthleticTitles = Arrays.asList(uncchAthleticTitlesArray);
        uncchAthleticDescriptionArray = getResources().getStringArray(R.array.uncch_residential_building_descriptions);
        uncchAthleticDescription = Arrays.asList(uncchAthleticDescriptionArray);
        uncchAthleticMarkerArray = new ArrayList<Marker>();

        uncchDiningTitlesArray = getResources().getStringArray(R.array.uncch_dining_names);
        uncchDiningTitles = Arrays.asList(uncchDiningTitlesArray);
        uncchDiningMarkerArray = new ArrayList<Marker>();
    }

    private void setUpLatLng() {
        //TODO Add all coordinates to arraylists in alphabetical order
        setUpuncchAcademicInfo();
        setUpuncchSportsInfo();
        setUpuncchResidenceInfo();
        setUpuncchDiningInfo();

    }

    private void setUpuncchSportsInfo() {
        uncchAthleticLatLng = new ArrayList<LatLng>();
        uncchAthleticLatLng.add(new LatLng(35.906955, -79.047858)); //Kenan Stadium Field
        uncchAthleticLatLng.add(new LatLng(35.907107, -79.049006)); //Kenan Football Center
        uncchAthleticLatLng.add(new LatLng(35.907439, -79.047590)); // Kenan Stadium Stands
        uncchAthleticLatLng.add(new LatLng(35.906421, -79.047012)); //Loudermilk Center for Excellence
        uncchAthleticLatLng.add(new LatLng(35.909500, -79.045453)); //Carmichael Arena
        uncchAthleticLatLng.add(new LatLng(35.908939, -79.045357)); //McCaskill Soccer Center
        uncchAthleticLatLng.add(new LatLng(35.909012, -79.043101)); // Eddie Smith Field House
        uncchAthleticLatLng.add(new LatLng(35.908528, -79.044662)); //Fetzer Field
        uncchAthleticLatLng.add(new LatLng(35.908355, -79.043719)); //Belk Track
        uncchAthleticLatLng.add(new LatLng(35.907306, -79.042730)); // Henry Stadium Field
        uncchAthleticLatLng.add(new LatLng(35.906362, -79.042797)); //Boshamer Stadium
        uncchAthleticLatLng.add(new LatLng(35.899556, -79.043824)); //Smith Student Activity Center
        uncchAthleticLatLng.add(new LatLng(35.899441, -79.042747)); //Koury Natatorium
        uncchAthleticLatLng.add(new LatLng(35.899884, -79.042161)); //Williamson Athletic Center
    }

    private void setUpuncchResidenceInfo() {
        uncchResidenceLatLng = new ArrayList<LatLng>();
        uncchResidenceLatLng.add(new LatLng(35.915003, -79.049316)); // Spencer Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.914739, -79.048208)); // Alderman Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.914585, -79.047503)); //Kenan Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.914051, -79.047851)); //McIver Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.911980, -79.051766)); //Old West Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.912330, -79.050787)); //Old East Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.912435, -79.048489)); //Grimes Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.912625, -79.047889)); //Manly Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.912816, -79.047184)); //Aycock Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.913037, -79.046615)); //Graham Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.912975, -79.046107)); //Stacy Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.912588, -79.046342)); //Everett Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.912379, -79.046934)); //Lewis Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.912035, -79.047510)); //Mangum Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.911783, -79.048079)); //Ruffin Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.912422, -79.044733)); //Cobb Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.911501, -79.046638)); //Joyner Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.911372, -79.046251)); //Alexander Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.910880, -79.046076)); //Connor Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.910358, -79.045940)); //Winston Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.908318, -79.046137)); //Carmichael Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.907617, -79.045022)); //Teague Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.906935, -79.044422)); //Parker Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.906229, -79.044256)); //Avery Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.904499, -79.046174)); //Morrison Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.904032, -79.045901)); //Paul Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.904382, -79.042859)); //Ehringhaus Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.903614, -79.043504)); //Koury Hall
        uncchResidenceLatLng.add(new LatLng(35.903515, -79.046000)); //Craige North Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.903159, -79.043716)); //George Moses Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.902649, -79.045226)); //Craige Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.902385, -79.043276)); //Hinton James Residence Hall
        uncchResidenceLatLng.add(new LatLng(35.901549, -79.043633)); //Taylor Hall
    }

    private void setUpuncchAcademicInfo() {
        uncchAcademicLatLng = new ArrayList<LatLng>();
        uncchAcademicLatLng.add(new LatLng(36.090227, -79.887342)); // Alumni House
        uncchAcademicLatLng.add(new LatLng(36.092747, -79.887352)); // Archdale Hall
        uncchAcademicLatLng.add(new LatLng(36.094948, -79.889273)); // Bauman
        uncchAcademicLatLng.add(new LatLng(36.090625, -79.887435)); // Bonner Center
        uncchAcademicLatLng.add(new LatLng(36.096902, -79.886279)); // Community Center
        uncchAcademicLatLng.add(new LatLng(36.091493, -79.887500)); // Dana Auidotirum
        uncchAcademicLatLng.add(new LatLng(36.092579, -79.888827)); // Duke Hall
        uncchAcademicLatLng.add(new LatLng(36.095382, -79.889458)); // Early College Classrooms
        uncchAcademicLatLng.add(new LatLng(36.089698, -79.885949)); // George White House
        uncchAcademicLatLng.add(new LatLng(36.096437, -79.888108)); // Facilities and Campus Services
        uncchAcademicLatLng.add(new LatLng(36.094090, -79.887940)); // Founders Hall
        uncchAcademicLatLng.add(new LatLng(36.094991, -79.890076)); // FF Science Center
        uncchAcademicLatLng.add(new LatLng(36.090105, -79.886627)); // Friends Center at uncch
        uncchAcademicLatLng.add(new LatLng(36.092928, -79.889522)); // Hege Library
        uncchAcademicLatLng.add(new LatLng(36.093198, -79.886920)); // Hege-Cox Hall
        uncchAcademicLatLng.add(new LatLng(36.093801, -79.889969)); // Hendricks Hall
        uncchAcademicLatLng.add(new LatLng(36.089875, -79.884555)); // Hildebrandt
        uncchAcademicLatLng.add(new LatLng(36.094771, -79.890021)); // J. Donald Cline Observatory
        uncchAcademicLatLng.add(new LatLng(36.093397, -79.889407)); // King Hall
        uncchAcademicLatLng.add(new LatLng(36.095861, -79.888598)); // Mail and Print Services
        uncchAcademicLatLng.add(new LatLng(36.096727, -79.884578)); // Milner Student Counseling Center
        uncchAcademicLatLng.add(new LatLng(36.091734, -79.888759)); // New Garden Hall
        uncchAcademicLatLng.add(new LatLng(36.092731, -79.889442)); // Office of the President
        uncchAcademicLatLng.add(new LatLng(36.095798, -79.886079)); // Ragsdale House
        uncchAcademicLatLng.add(new LatLng(36.094402, -79.888490)); // The Hut
    }

    private void setUpuncchDiningInfo() {
        uncchDiningLatLng = new ArrayList<LatLng>();
        uncchDiningLatLng.add(new LatLng(35.914163, -79.051610)); //Common Grounds Cafe
        uncchDiningLatLng.add(new LatLng(35.911417, -79.051200)); //Blue Ram
        uncchDiningLatLng.add(new LatLng(35.910618, -79.048811)); //Top of Lenoir
        uncchDiningLatLng.add(new LatLng(35.909690, -79.048371)); //The Pit Stop
        uncchDiningLatLng.add(new LatLng(35.910317, -79.047407)); //Alpine Bagel
        uncchDiningLatLng.add(new LatLng(35.907938, -79.053968)); //EspressOasis Global
        uncchDiningLatLng.add(new LatLng(35.907385, -79.050737)); //Genomic Cafe
        uncchDiningLatLng.add(new LatLng(35.905911, -79.051708)); //The Beach Cafe
        uncchDiningLatLng.add(new LatLng(35.905751, -79.052967)); //Friends' Cafe
        uncchDiningLatLng.add(new LatLng(35.905382, -79.054181)); //The Atrium Cafe
        uncchDiningLatLng.add(new LatLng(35.904571, -79.053081)); //Raynor Food Center
        uncchDiningLatLng.add(new LatLng(35.903987, -79.054613)); //Alpine Deli at Tar Heal
        uncchDiningLatLng.add(new LatLng(35.901725, -79.053235)); //Kind Coffee
        uncchDiningLatLng.add(new LatLng(35.900090, -79.055336)); //Courtyard Café
        uncchDiningLatLng.add(new LatLng(35.899640, -79.046208)); //Cafe McColl
        uncchDiningLatLng.add(new LatLng(35.905772, -79.046397)); //Rams Head Market and Subway
        uncchDiningLatLng.add(new LatLng(35.905950, -79.045548)); //Rams Head Dining Hall
        uncchDiningLatLng.add(new LatLng(35.908322, -79.042612)); //The Law Bar
    }

    private void setUpMap() {
        uncchMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        uncchMap.setPadding(0, 0, 0, 64);
        uncchMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.093422, -79.887920), 17.0f));
        int collegeBackgroundColor = Color.argb(40, 128, 0, 0);
        int polygonStrokeColor = Color.BLACK;
        Polygon uncchCollege = uncchMap.addPolygon(new PolygonOptions()
                .add(new LatLng(36.089202, -79.884470), new LatLng(36.089881, -79.888889), new LatLng(36.092057, -79.889801), new LatLng(36.094724, -79.891402), new LatLng(36.098666, -79.891767), new LatLng(36.098341, -79.881383), new LatLng(36.092699, -79.881681), new LatLng(36.089202, -79.884470))
                .strokeColor(polygonStrokeColor)
                .strokeWidth(5)
                .fillColor(collegeBackgroundColor));
        for (int i = 0; i < uncchAcademicLatLng.size(); i++) {
            uncchAcademicMarker = uncchMap.addMarker(new MarkerOptions()
                    .position(uncchAcademicLatLng.get(i))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.academic_icon))
                    .title(uncchAcademicTitles.get(i)));
                   // .snippet(uncchAcademicDescription.get(i)));
            uncchAcademicMarkerArray.add(uncchAcademicMarker);
        }

        for (int i = 0; i < uncchResidenceLatLng.size(); i++) {
            uncchResidenceMarker = uncchMap.addMarker(new MarkerOptions()
                            .position(uncchResidenceLatLng.get(i))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.residencehall))
                            .title(uncchResidenceTitles.get(i))
            );

            uncchResidenceMarkerArray.add(uncchResidenceMarker);
        }

        for (int i = 0; i < uncchAthleticLatLng.size(); i++) {
            uncchAthleticMarker = uncchMap.addMarker(new MarkerOptions()
                            .position(uncchAthleticLatLng.get(i))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.sports))
                            .title(uncchAthleticTitles.get(i))
            );

            uncchAthleticMarkerArray.add(uncchAthleticMarker);
        }

        for (int i = 0; i < uncchDiningLatLng.size(); i++) {
            uncchDiningMarker = uncchMap.addMarker(new MarkerOptions()
                            .position(uncchDiningLatLng.get(i))
                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.dining_icon))
                            .title(uncchDiningTitles.get(i))
            );

            uncchDiningMarkerArray.add(uncchDiningMarker);
        }


        uncchMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

            @Override
            public View getInfoWindow(Marker marker) {
                LayoutInflater inflater = LayoutInflater.from(cxt);
                View v = inflater.inflate(R.layout.info_window_layout, null);

                v.setBackgroundColor(Color.argb(150, 183, 28, 28));

                String title = marker.getTitle();

                TextView titleTV = (TextView) v.findViewById(R.id.marker_title);

                titleTV.setText(title);
                return v;
            }

            @Override
            public View getInfoContents(Marker marker) {

                return null;
            }
        });
    }


    @Override
    public void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mMapView.onLowMemory();
    }


    public void setMarkerVisibilityFalse(String markerName) {
        ValueAnimator ani1 = ValueAnimator.ofFloat(1, 0); //change for (0,1) if you want a fade in
        ani1.setDuration(500);
        if (markerName.equals("academicMarker")) {
            for (final Marker uncchMarker : uncchAcademicMarkerArray) {
                ani1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        uncchMarker.setAlpha((float) animation.getAnimatedValue());
                    }
                });
                ani1.start();
                uncchMarker.setVisible(false);
            }
        }
        if (markerName.equals("residenceMarker")) {
            for (final Marker uncchMarker : uncchResidenceMarkerArray) {
                ani1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        uncchMarker.setAlpha((float) animation.getAnimatedValue());
                    }
                });
                ani1.start();
                uncchMarker.setVisible(false);
            }
        }

        if (markerName.equals("athleticMarker")) {
            for (final Marker uncchMarker : uncchAthleticMarkerArray) {
                ani1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        uncchMarker.setAlpha((float) animation.getAnimatedValue());
                    }
                });
                ani1.start();
                uncchMarker.setVisible(false);
            }
        }

        if (markerName.equals("diningMarker")) {
            for (final Marker uncchMarker : uncchDiningMarkerArray) {
                ani1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        uncchMarker.setAlpha((float) animation.getAnimatedValue());
                    }
                });
                ani1.start();
                uncchMarker.setVisible(false);
            }
        }
    }

    public void setMarkerVisibilityTrue(String markerName) {
        ValueAnimator ani2 = ValueAnimator.ofFloat(0, 1); //change for (0,1) if you want a fade in
        ani2.setDuration(500);
        if (markerName.equals("academicMarker")) {
            for (final Marker uncchMarker : uncchAcademicMarkerArray) {
                ani2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        uncchMarker.setAlpha((float) animation.getAnimatedValue());
                    }
                });
                ani2.start();
                uncchMarker.setVisible(true);
            }
        }
        if (markerName.equals("residenceMarker")) {
            for (final Marker uncchMarker : uncchResidenceMarkerArray) {
                ani2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        uncchMarker.setAlpha((float) animation.getAnimatedValue());
                    }
                });
                ani2.start();
                uncchMarker.setVisible(true);
            }
        }
        if (markerName.equals("athleticMarker")) {
            for (final Marker uncchMarker : uncchAthleticMarkerArray) {
                ani2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        uncchMarker.setAlpha((float) animation.getAnimatedValue());
                    }
                });
                ani2.start();
                uncchMarker.setVisible(true);
            }
        }
        if (markerName.equals("diningMarker")) {
            for (final Marker uncchMarker : uncchDiningMarkerArray) {
                ani2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        uncchMarker.setAlpha((float) animation.getAnimatedValue());
                    }
                });
                ani2.start();
                uncchMarker.setVisible(true);
            }
        }
    }

    public void setMapTarget(String markerName) {
        LatLng markerPosition = uncchAcademicLatLng.get(uncchAcademicTitles.indexOf(markerName));
        uncchMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerPosition, 18.0f));
        uncchAcademicMarkerArray.get(uncchAcademicTitles.indexOf(markerName)).showInfoWindow();
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    public LatLng getUserLocation(){
        Log.d("Location: ", new LatLng(uncchMap.getMyLocation().getLatitude(), uncchMap.getMyLocation().getLongitude()).toString());
        return new LatLng(uncchMap.getMyLocation().getLatitude(), uncchMap.getMyLocation().getLongitude());
    }

    public void addMarkers(Friend friend, LatLng latLng){
        uncchMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.friend))).setTitle(friend.getUsername());
    }
}