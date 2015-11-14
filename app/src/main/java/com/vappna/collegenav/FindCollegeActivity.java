package com.vappna.collegenav;

import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.joooonho.SelectableRoundedImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class FindCollegeActivity extends ActionBarActivity {

    Toolbar toolbar;
    private String stateID, collegeID;
    FloatingActionButton searchButton;
    RelativeLayout mainLayout;
    View guilfordBar, uncCHbar;
    public static final String UNCCH = "University of North Carolina at Chapel Hill";
    public static final String GUICOL = "Guilford College";
    public static final int GUILFORD_COLLEGE_COLOR_CLEAR = Color.argb(95, 128, 0, 0);
    public static final int UNCCH_COLOR_CLEAR = Color.argb(99, 153, 186, 221);


    CardView guilfordCard, uncCHCard;
    SelectableRoundedImageView guilfordButton, uncchButton;
    TextView guilfordCollegeTV, uncCHTV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_college);
        setUpToolbar();
        setUpViews();

    }

    private void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("Pick A College");
        toolbar.setBackgroundColor(Color.WHITE);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
    }

    private void setUpViews() {
        guilfordCard = (CardView) findViewById(R.id.guilford_card);
        uncCHCard = (CardView) findViewById(R.id.uncch_card);
        guilfordCard.setPreventCornerOverlap(false);
        uncCHCard.setPreventCornerOverlap(false);
        guilfordButton = (SelectableRoundedImageView) findViewById(R.id.guilford_college_button);
        //guilfordButton.setImageResource(R.drawable.gc_picture);
        guilfordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startNextActivity(GUICOL);
            }
        });
        mainLayout = (RelativeLayout) findViewById(R.id.main_layout);
        uncchButton = (SelectableRoundedImageView) findViewById(R.id.uncch_button);
       // uncchButton.setImageResource(R.drawable.uncchpicture);
        uncchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(mainLayout, "This college will be added soon!", Snackbar.LENGTH_SHORT).show();
                //startNextActivity(UNCCH);
            }
        });
        guilfordCollegeTV = (TextView) findViewById(R.id.guilford_text);
        guilfordCollegeTV.setBackgroundColor(GUILFORD_COLLEGE_COLOR_CLEAR);
        uncCHTV = (TextView) findViewById(R.id.uncch_text);
        uncCHTV.setBackgroundColor(UNCCH_COLOR_CLEAR);
    }

    private void startNextActivity(String collegeName) {
        Intent intent = new Intent(this, CollegeHomeActivity.class);
        intent.putExtra("collegeID", collegeName);
        startActivity(intent);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        if(id == R.id.action_search) {
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_in);

    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(android.R.anim.fade_out, android.R.anim.fade_out);
    }

}
