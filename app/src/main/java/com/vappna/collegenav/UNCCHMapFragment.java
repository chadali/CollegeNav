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
        uncchAcademicLatLng.add(new LatLng(35.914022, -79.051559)); // Graham Memorial Hall
        uncchAcademicLatLng.add(new LatLng(35.912348, -79.054385)); // Hanes Art Center
        uncchAcademicLatLng.add(new LatLng(35.912606, -79.053192)); // Hill Hall
        uncchAcademicLatLng.add(new LatLng(35.913182, -79.050975)); // Alumni Hall
        uncchAcademicLatLng.add(new LatLng(35.913306, -79.050044)); // Howell Hall
        uncchAcademicLatLng.add(new LatLng(36.091493, -79.887500)); // Kenan Music Building
        uncchAcademicLatLng.add(new LatLng(35.911935, -79.053186)); // Smith Building
        uncchAcademicLatLng.add(new LatLng(35.911852, -79.052401)); // New West
        uncchAcademicLatLng.add(new LatLng(35.912686, -79.050210)); // New East
        uncchAcademicLatLng.add(new LatLng(35.912769, -79.049489)); // Davie Hall
        uncchAcademicLatLng.add(new LatLng(35.912205, -79.049049)); // Caldwell Hall
        uncchAcademicLatLng.add(new LatLng(35.910516, -79.053534)); // Peabody Hall
        uncchAcademicLatLng.add(new LatLng(35.910702, -79.052647)); // Phillips Hall
        uncchAcademicLatLng.add(new LatLng(35.911312, -79.051154)); // YMCA Building
        uncchAcademicLatLng.add(new LatLng(35.911208, -79.050159)); // Carolina Hall
        uncchAcademicLatLng.add(new LatLng(36.089875, -79.884555)); // Hanes Hall
        uncchAcademicLatLng.add(new LatLng(36.094771, -79.890021)); // Manning Hall
        uncchAcademicLatLng.add(new LatLng(36.093397, -79.889407)); // Hamilton Hall
        uncchAcademicLatLng.add(new LatLng(35.909860, -79.053278)); // Sitterson Hall
        uncchAcademicLatLng.add(new LatLng(35.910035, -79.052532)); // Chapman Hall
        uncchAcademicLatLng.add(new LatLng(35.910216, -79.051843)); // Carroll Hall
        uncchAcademicLatLng.add(new LatLng(35.910242, -79.051001)); // Gardner Hall
        uncchAcademicLatLng.add(new LatLng(35.910717, -79.049629)); // Murphey Hall
        uncchAcademicLatLng.add(new LatLng(35.909090, -79.052806)); // Naval Armory
        uncchAcademicLatLng.add(new LatLng(35.909710, -79.051786)); // Murray Hall
        uncchAcademicLatLng.add(new LatLng(35.909648, -79.051294)); // Venable
        uncchAcademicLatLng.add(new LatLng(35.909880, -79.050637)); // Dey Hall
        uncchAcademicLatLng.add(new LatLng(35.910289, -79.049585)); // Bingham Hall
        uncchAcademicLatLng.add(new LatLng(35.910364, -79.049228)); // Greenlaw Hall
        uncchAcademicLatLng.add(new LatLng(35.908933, -79.051198)); // Kenan Laboratories
        uncchAcademicLatLng.add(new LatLng(35.909781, -79.049079)); // Wilson Library, Louis Round
        uncchAcademicLatLng.add(new LatLng(35.908134, -79.051290)); // Coker Hall
        uncchAcademicLatLng.add(new LatLng(35.907973, -79.051923)); // Wilson Hall
        uncchAcademicLatLng.add(new LatLng(35.907921, -79.050148)); // Sonja Haynes Center for Black Culture and History
        uncchAcademicLatLng.add(new LatLng(35.907960, -79.054068)); // FedEx Global Education Cente
        uncchAcademicLatLng.add(new LatLng(35.907691, -79.052207)); // Mitchell Hall
        uncchAcademicLatLng.add(new LatLng(35.907673, -79.050882)); // Genome Sciences Building
        uncchAcademicLatLng.add(new LatLng(35.907408, -79.053366)); // Beard Hall
        uncchAcademicLatLng.add(new LatLng(35.907104, -79.054267)); // Tate-Turner-Kuralt Building
        uncchAcademicLatLng.add(new LatLng(35.906326, -79.054379)); // McGavran-Greenberg Hall
        uncchAcademicLatLng.add(new LatLng(35.906687, -79.053516)); // Kerr Hall
        uncchAcademicLatLng.add(new LatLng(35.906761, -79.052148)); // Carrington Hall
        uncchAcademicLatLng.add(new LatLng(35.906061, -79.052330)); // Bondurant Hall
        uncchAcademicLatLng.add(new LatLng(35.905992, -79.053661)); // Rosenau Hall
        uncchAcademicLatLng.add(new LatLng(35.905389, -79.054282)); // Michael Hooker Research Center
        uncchAcademicLatLng.add(new LatLng(35.905335, -79.052875)); // MacNider Hall
        uncchAcademicLatLng.add(new LatLng(35.905544, -79.049909)); // Medical School Wing B
        uncchAcademicLatLng.add(new LatLng(35.912005, -79.043939)); // Center for Dramatic Art
        uncchAcademicLatLng.add(new LatLng(35.909163, -79.047027)); // Fetzer Hall
        uncchAcademicLatLng.add(new LatLng(35.909463, -79.045866)); // Woollen Gymnasium
        uncchAcademicLatLng.add(new LatLng(35.910000, -79.042434)); // Knapp-Sanders Building
        uncchAcademicLatLng.add(new LatLng(35.908233, -79.042587)); // Van Hecke-Wettach Hall
        uncchAcademicLatLng.add(new LatLng(35.904692, -79.053621)); // Koury Oral Health Sciences Building
        uncchAcademicLatLng.add(new LatLng(35.904546, -79.053193)); // Brauer Hall
        uncchAcademicLatLng.add(new LatLng(35.904871, -79.052967)); // School of Dentistry
        uncchAcademicLatLng.add(new LatLng(35.903202, -79.055105)); // NeuroSciences Research Building
        uncchAcademicLatLng.add(new LatLng(35.902526, -79.054077)); // Marsico Hall
        uncchAcademicLatLng.add(new LatLng(35.901623, -79.053304)); // Bioinformatics Building

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
        uncchMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(35.906292, -79.047836), 17.0f));
        int collegeBackgroundColor = Color.argb(40, 128, 0, 0);
        int polygonStrokeColor = Color.BLACK;
        Polygon uncchCollege = uncchMap.addPolygon(new PolygonOptions()
                .add(new LatLng(35.916119, -79.047979), new LatLng(35.912868, -79.055642), new LatLng(35.905219, -79.066615), new LatLng(35.896578, -79.047422), new LatLng(35.896900, -79.034929), new LatLng(35.911342, -79.040475), new LatLng(35.916119, -79.047979))
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
                LinearLayout buttonLayout = (LinearLayout) v.findViewById(R.id.buttonLayout);
                buttonLayout.setBackgroundColor(Colors.getUncchColor());
                v.setBackgroundColor(Colors.getUncchColorClear());

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
        uncchMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerPosition, 15.0f));
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

    public void addMarkers(Friend friends, LatLng latLng){
        uncchMap.addMarker(new MarkerOptions().position(latLng).icon(BitmapDescriptorFactory.fromResource(R.drawable.friend))).setTitle(friends.getUsername());
    }
}