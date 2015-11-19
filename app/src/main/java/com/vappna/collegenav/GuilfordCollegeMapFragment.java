package com.vappna.collegenav;

import android.animation.ValueAnimator;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
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
public class GuilfordCollegeMapFragment extends Fragment implements LocationListener {

    MapView mMapView;
    private GoogleMap guilfordMap;

    String[] guilfordAcademicTitlesArray;
    List<String> guilfordAcademicTitles;

    String[] guilfordAthleticTitlesArray;
    List<String> guilfordAthleticTitles;

    String[] guilfordResidenceTitlesArray;
    List<String> guilfordResidenceTitles;

    ArrayList<LatLng> guilfordAcademicLatLng, guilfordResidenceLatLng, guilfordAthleticLatLng;

    String[] guilfordAcademicDescriptionArray, guilfordAthleticDescriptionArray, guilfordResidenceDescriptionArray;
    List<String> guilfordAcademicDescription, guilfordAthleticDescription, guilfordResidenceDescription;

    ArrayList<Marker> guilfordAcademicMarkerArray, guilfordResidenceMarkerArray, guilfordAthleticMarkerArray;

    Marker guilfordAcademicMarker, guilfordResidenceMarker, guilfordAthleticMarker;
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

        guilfordMap = mMapView.getMap();
        guilfordMap.setMyLocationEnabled(true);
        setUpArrays();
        setUpLatLng();
        setUpMap();
        guilfordMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                // getActivity().showDialog();
            }
        });
        return v;
    }

    private void setUpArrays() {
        guilfordAcademicTitlesArray = getResources().getStringArray(R.array.guilford_academic_administrative_building_names);
        guilfordAcademicTitles = Arrays.asList(guilfordAcademicTitlesArray);
        guilfordAcademicDescriptionArray = getResources().getStringArray(R.array.guilford_academic_administrative_building_descriptions);
        guilfordAcademicDescription = Arrays.asList(guilfordAcademicDescriptionArray);
        guilfordAcademicMarkerArray = new ArrayList<Marker>();
        guilfordResidenceTitlesArray = getResources().getStringArray(R.array.guilford_residential_building_names);
        guilfordResidenceTitles = Arrays.asList(guilfordResidenceTitlesArray);
        guilfordResidenceDescriptionArray = getResources().getStringArray(R.array.guilford_residential_building_descriptions);
        guilfordResidenceDescription = Arrays.asList(guilfordResidenceDescriptionArray);
        guilfordResidenceMarkerArray = new ArrayList<Marker>();
    }

    private void setUpLatLng() {
        //TODO Add all coordinates to arraylists in alphabetical order
        setUpGuilfordAcademicInfo();
        setUpGuilfordSportsInfo();
        setUpGuilfordResidenceInfo();

    }

    private void setUpGuilfordSportsInfo() {
        guilfordAthleticLatLng = new ArrayList<LatLng>();
        guilfordAthleticLatLng.add(new LatLng(36.094027, -79.886371)); // Alumni Gym
        guilfordAthleticLatLng.add(new LatLng(36.093392, -79.884218)); // Alumni Wedge Range
        guilfordAthleticLatLng.add(new LatLng(36.091517, -79.886012)); // Armfield Athletic Center
        guilfordAthleticLatLng.add(new LatLng(36.098294, -79.890389)); // Dorothy Ragsdale McMichael '37 Centennial Class Courts
        guilfordAthleticLatLng.add(new LatLng(36.092885, -79.885744)); // Edgar H. McBane Field
        guilfordAthleticLatLng.add(new LatLng(36.098887, -79.887740)); // Guilford Meadows Disc Golf Course
        guilfordAthleticLatLng.add(new LatLng(36.096946, -79.889774)); // Haworth East/West Field
        guilfordAthleticLatLng.add(new LatLng(36.097600, -79.890429)); // Haworth Lighted Field
        guilfordAthleticLatLng.add(new LatLng(36.096490, -79.890904)); // Haworth North/South Field
        guilfordAthleticLatLng.add(new LatLng(36.097813, -79.889427)); // Haworth Soccer Game Field
        guilfordAthleticLatLng.add(new LatLng(36.097574, -79.891166)); // Haworth Softball Field
        guilfordAthleticLatLng.add(new LatLng(36.094094, -79.884226)); // Jack Jensen Golf Center
        guilfordAthleticLatLng.add(new LatLng(36.094155, -79.885316)); // Mary Ragsdale Fitness Area
        guilfordAthleticLatLng.add(new LatLng(36.093994, -79.885411)); // Outdoor Basketball Court
        guilfordAthleticLatLng.add(new LatLng(36.094263, -79.885747)); // PE Center
        guilfordAthleticLatLng.add(new LatLng(36.094421, -79.885528)); // Ragan-Brown Field House and Physical Education Center
        guilfordAthleticLatLng.add(new LatLng(36.094096, -79.885050)); // Sand Volleyball Court
        guilfordAthleticLatLng.add(new LatLng(36.092509, -79.885559)); // Stuart Maynard Batting Center
        guilfordAthleticLatLng.add(new LatLng(36.100310, -79.886895)); // The Meadows at Guilford College
    }

    private void setUpGuilfordResidenceInfo() {
        guilfordResidenceLatLng = new ArrayList<LatLng>();
        guilfordResidenceLatLng.add(new LatLng(36.094422, -79.889743)); // Binford Hall
        guilfordResidenceLatLng.add(new LatLng(36.095318, -79.888509)); // Bryan Hall
        guilfordResidenceLatLng.add(new LatLng(36.092470, -79.886947)); // English Hall
        guilfordResidenceLatLng.add(new LatLng(36.093877, -79.889275)); // Mary Hobbs Hall
        guilfordResidenceLatLng.add(new LatLng(36.095169, -79.887018)); // Milner Hall
        guilfordResidenceLatLng.add(new LatLng(36.097621, -79.885893)); // North Apartments
        guilfordResidenceLatLng.add(new LatLng(36.094310, -79.888993)); // Shore Hall
        guilfordResidenceLatLng.add(new LatLng(36.096783, -79.886742)); // South Apartments
    }

    private void setUpGuilfordAcademicInfo() {
        guilfordAcademicLatLng = new ArrayList<LatLng>();
        guilfordAcademicLatLng.add(new LatLng(36.090227, -79.887342)); // Alumni House
        guilfordAcademicLatLng.add(new LatLng(36.092747, -79.887352)); // Archdale Hall
        guilfordAcademicLatLng.add(new LatLng(36.094948, -79.889273)); // Bauman
        guilfordAcademicLatLng.add(new LatLng(36.090625, -79.887435)); // Bonner Center
        guilfordAcademicLatLng.add(new LatLng(36.096902, -79.886279)); // Community Center
        guilfordAcademicLatLng.add(new LatLng(36.091493, -79.887500)); // Dana Auidotirum
        guilfordAcademicLatLng.add(new LatLng(36.092579, -79.888827)); // Duke Hall
        guilfordAcademicLatLng.add(new LatLng(36.095382, -79.889458)); // Early College Classrooms
        guilfordAcademicLatLng.add(new LatLng(36.089698, -79.885949)); // George White House
        guilfordAcademicLatLng.add(new LatLng(36.096437, -79.888108)); // Facilities and Campus Services
        guilfordAcademicLatLng.add(new LatLng(36.094090, -79.887940)); // Founders Hall
        guilfordAcademicLatLng.add(new LatLng(36.094991, -79.890076)); // FF Science Center
        guilfordAcademicLatLng.add(new LatLng(36.090105, -79.886627)); // Friends Center at Guilford
        guilfordAcademicLatLng.add(new LatLng(36.092928, -79.889522)); // Hege Library
        guilfordAcademicLatLng.add(new LatLng(36.093198, -79.886920)); // Hege-Cox Hall
        guilfordAcademicLatLng.add(new LatLng(36.093801, -79.889969)); // Hendricks Hall
        guilfordAcademicLatLng.add(new LatLng(36.089875, -79.884555)); // Hildebrandt
        guilfordAcademicLatLng.add(new LatLng(36.094771, -79.890021)); // J. Donald Cline Observatory
        guilfordAcademicLatLng.add(new LatLng(36.093397, -79.889407)); // King Hall
        guilfordAcademicLatLng.add(new LatLng(36.095861, -79.888598)); // Mail and Print Services
        guilfordAcademicLatLng.add(new LatLng(36.096727, -79.884578)); // Milner Student Counseling Center
        guilfordAcademicLatLng.add(new LatLng(36.091734, -79.888759)); // New Garden Hall
        guilfordAcademicLatLng.add(new LatLng(36.092731, -79.889442)); // Office of the President
        guilfordAcademicLatLng.add(new LatLng(36.095798, -79.886079)); // Ragsdale House
        guilfordAcademicLatLng.add(new LatLng(36.094402, -79.888490)); // The Hut
    }

    private void setUpMap() {
        guilfordMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        guilfordMap.setPadding(0, 0, 0, 64);
        guilfordMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(36.093422, -79.887920), 17.0f));
        int collegeBackgroundColor = Color.argb(40, 128, 0, 0);
        int polygonStrokeColor = Color.BLACK;
        Polygon guilfordCollege = guilfordMap.addPolygon(new PolygonOptions()
                .add(new LatLng(36.089202, -79.884470), new LatLng(36.089881, -79.888889), new LatLng(36.092057, -79.889801), new LatLng(36.094724, -79.891402), new LatLng(36.098666, -79.891767), new LatLng(36.098341, -79.881383), new LatLng(36.092699, -79.881681), new LatLng(36.089202, -79.884470))
                .strokeColor(polygonStrokeColor)
                .strokeWidth(5)
                .fillColor(collegeBackgroundColor));
        for (int i = 0; i < guilfordAcademicLatLng.size(); i++) {
            guilfordAcademicMarker = guilfordMap.addMarker(new MarkerOptions()
                    .position(guilfordAcademicLatLng.get(i))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.academic_icon))
                    .title(guilfordAcademicTitles.get(i))
                    .snippet(guilfordAcademicDescription.get(i)));
            guilfordAcademicMarkerArray.add(guilfordAcademicMarker);
        }

       for (int i = 0; i < guilfordResidenceLatLng.size(); i++) {
            guilfordResidenceMarker = guilfordMap.addMarker(new MarkerOptions()
                    .position(guilfordResidenceLatLng.get(i))
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.residencehall))
                    .title(guilfordResidenceTitles.get(i))
                   );

            guilfordResidenceMarkerArray.add(guilfordResidenceMarker);
        }

//        for (int i = 0; i < guilfordAthleticLatLng.size(); i++) {
//            guilfordAthleticMarker = guilfordMap.addMarker(new MarkerOptions()
//                            .position(guilfordAthleticLatLng.get(i))
//                            .icon(BitmapDescriptorFactory.fromResource(R.drawable.sports))
//                            .title(guilfordAthleticTitles.get(i))
//            );
//
//            guilfordAthleticMarkerArray.add(guilfordAthleticMarker);
//        }

        guilfordMap.setInfoWindowAdapter(new GoogleMap.InfoWindowAdapter() {

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
            for (final Marker guilfordMarker : guilfordAcademicMarkerArray) {
                ani1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        guilfordMarker.setAlpha((float) animation.getAnimatedValue());
                    }
                });
                ani1.start();
                guilfordMarker.setVisible(false);
            }
        }
        if (markerName.equals("residenceMarker")) {
            for (final Marker guilfordMarker : guilfordResidenceMarkerArray) {
                ani1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        guilfordMarker.setAlpha((float) animation.getAnimatedValue());
                    }
                });
                ani1.start();
                guilfordMarker.setVisible(false);
            }
        }

        if (markerName.equals("athleticMarker")) {
            for (final Marker guilfordMarker : guilfordAthleticMarkerArray) {
                ani1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        guilfordMarker.setAlpha((float) animation.getAnimatedValue());
                    }
                });
                ani1.start();
                guilfordMarker.setVisible(false);
            }
        }
    }

    public void setMarkerVisibilityTrue(String markerName) {
        ValueAnimator ani2 = ValueAnimator.ofFloat(0, 1); //change for (0,1) if you want a fade in
        ani2.setDuration(500);
        if (markerName.equals("academicMarker")) {
            for (final Marker guilfordMarker : guilfordAcademicMarkerArray) {
                ani2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        guilfordMarker.setAlpha((float) animation.getAnimatedValue());
                    }
                });
                ani2.start();
                guilfordMarker.setVisible(true);
            }
        }
        if (markerName.equals("residenceMarker")) {
            for (final Marker guilfordMarker : guilfordResidenceMarkerArray) {
                ani2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        guilfordMarker.setAlpha((float) animation.getAnimatedValue());
                    }
                });
                ani2.start();
                guilfordMarker.setVisible(true);
            }
        }
        if (markerName.equals("AthleticMarker")) {
            for (final Marker guilfordMarker : guilfordAthleticMarkerArray) {
                ani2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        guilfordMarker.setAlpha((float) animation.getAnimatedValue());
                    }
                });
                ani2.start();
                guilfordMarker.setVisible(true);
            }
        }
    }

    public void setMapTarget(String markerName) {
        LatLng markerPosition = guilfordAcademicLatLng.get(guilfordAcademicTitles.indexOf(markerName));
        guilfordMap.moveCamera(CameraUpdateFactory.newLatLngZoom(markerPosition, 18.0f));
        guilfordAcademicMarkerArray.get(guilfordAcademicTitles.indexOf(markerName)).showInfoWindow();
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
}