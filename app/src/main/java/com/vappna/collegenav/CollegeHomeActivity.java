package com.vappna.collegenav;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

public class CollegeHomeActivity extends ActionBarActivity {

    Toolbar toolbar;
    String stateID, collegeID;
    Colors colors = new Colors();
    MapsActivity mapsA = new MapsActivity();
    RelativeLayout mainLayout; //bottomBar;
    TextView welcomeTextbox, collegeDetailsTextbox;
    //Button viewMapButton, learnMoreButton;
    ImageView collegeImage;
    Intent intent;
    Animation startAnimation;
    Typeface dustismoFont;
    String activityName = "CollegeHomeActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle infoBundle = getIntent().getExtras();
        collegeID = infoBundle.getString("collegeID");
        setContentView(R.layout.college_home_page);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setUpToolbar();
        setUpViews();
        setUpFont();
        setUpPage();
       // startAnimations();
        ImageView info = new ImageView(this);
        info.setImageResource(R.drawable.info);
        ImageView maps = new ImageView(this);
        maps.setImageResource(R.drawable.mapmarker);
        ImageView icon = new ImageView(this); // Create an app_icon
        icon.setImageResource(R.drawable.options);
        SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
        itemBuilder.setBackgroundDrawable(getResources().getDrawable(R.drawable.fab_options));
        SubActionButton mapsButton = itemBuilder
                .setContentView(maps)
                .build();

        FloatingActionButton actionButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .setBackgroundDrawable(R.drawable.fab)
                .build();


        SubActionButton.LayoutParams params = new SubActionButton.LayoutParams(72, 72);
        mapsButton.setLayoutParams(params);
        SubActionButton infoButton = itemBuilder.setContentView(info).setLayoutParams(params).build();
        FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(mapsButton)
                        .addSubActionView(infoButton)

                        // ...
                .attachTo(actionButton)
                .build();
        mapsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              viewMap();
            }
        });
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                learnMore();
            }
        });

    }
    private void setUpPage() {
        if (collegeID.equals("Guilford College")) {
         //   collegeImage.setBackgroundResource(R.drawable.guilfordlogo);
            toolbar.setBackgroundColor(colors.getGuilfordCollegeColor());
            //  bottomBar.setBackgroundColor(mapsA.GUILFORD_COLLEGE_COLOR);
            toolbar.setTitleTextColor(Color.WHITE);
        //    collegeDetailsTextbox.setText("Located in Greensboro, North Carolina \n Current Enrollment: 2137 \n Offers over 37 Majors");
            collegeID = "Guilford College";
        }
    }
    private void startAnimations() {
   //     welcomeTextbox.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein));
   //     collegeImage.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein));
   //     collegeDetailsTextbox.startAnimation(AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein));
    }

    private void setUpFont() {
        dustismoFont = Typeface.createFromAsset(getAssets(), "fonts/Dustismo.ttf");
      //  welcomeTextbox.setTypeface(dustismoFont);
       // viewMapButton.setTypeface(dustismoFont);
      //  collegeDetailsTextbox.setTypeface(dustismoFont);
        //learnMoreButton.setTypeface(dustismoFont);
    }

    private void setUpViews() {
        mainLayout = (RelativeLayout) findViewById(R.id.main_layout);
       // collegeImage = (ImageView) findViewById(R.id.college_logo);
      //  bottomBar = (RelativeLayout) findViewById(R.id.bottomBar);
       // welcomeTextbox = (TextView) findViewById(R.id.welcome_textbox);
       // collegeDetailsTextbox = (TextView) findViewById(R.id.college_details);
      //  viewMapButton = (Button) findViewById(R.id.View_Map_Button);
       // learnMoreButton = (Button) findViewById(R.id.Learn_More_Button);
    }

    private void setUpToolbar() {
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Home");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.GuilfordPrimaryDark));
            window.setNavigationBarColor(getResources().getColor(R.color.GuilfordPrimary));
        }
    }

    protected void onResume() {
        super.onResume();
        startAnimations();
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main2, menu);
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
            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    public void viewMap() {
        intent = new Intent(this, MapContainerActivity.class);
        intent.putExtra("collegeID", collegeID);
        intent.putExtra("activityName", activityName);
        startActivity(intent);
    }

    public void learnMore() {
        intent = new Intent(this, LearnMore.class);
        intent.putExtra("collegeID", collegeID);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(this, FindCollegeActivity.class);
        startActivity(setIntent);
    }
}
