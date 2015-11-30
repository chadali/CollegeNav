package com.vappna.collegenav;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class UserHomeActivity extends ActionBarActivity {

    Toolbar toolbar;
    String stateID, collegeID;
    MapsActivity mapsA = new MapsActivity();
    RelativeLayout mainLayout; //bottomBar;
    Intent intent;
    Typeface dustismoFont;
    String activityName = "UserHomeActivity";

    CardView getStartedCV, infoCV, mapCV, friendsCV, settingsCV, logOutCV;
    LocalUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        user = new LocalUser(this);
        collegeID = user.getLoggedInUser().getHomeCollege();
        setContentView(R.layout.user_home_page);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        setUpToolbar();
        setUpViews();
        setUpFont();
        setUpPage();
        TextView loginTV = (TextView) findViewById(R.id.loginText);
        loginTV.setText(loginTV.getText() + user.getLoggedInUser().getUsername());
    }

    private void setUpPage() {
        if (user.getLoggedInUser().getHomeCollege() == Strings.getGUICOL()) {
            toolbar.setBackgroundColor(Colors.getGuilfordCollegeColor());
            toolbar.setTitleTextColor(Color.WHITE);
        }
    }

    private void setUpFont() {
        dustismoFont = Typeface.createFromAsset(getAssets(), "fonts/Dustismo.ttf");
    }

    private void setUpViews() {
        mainLayout = (RelativeLayout) findViewById(R.id.main_layout);
        getStartedCV = (CardView) findViewById(R.id.get_started_card);
        getStartedCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserHomeActivity.this, FindCollegeActivity.class);
                intent.putExtra("activityName", activityName);
                intent.putExtra("username", user.getLoggedInUser().getUsername());
                intent.putExtra("password", user.getLoggedInUser().getPassword());
                startActivity(intent);
            }
        });
        infoCV = (CardView) findViewById(R.id.info_card);
        mapCV = (CardView) findViewById(R.id.map_card);
        mapCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewMap();
            }
        });
        friendsCV = (CardView) findViewById(R.id.friends_card);
        friendsCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        settingsCV = (CardView) findViewById(R.id.settings_card);
        logOutCV = (CardView) findViewById(R.id.log_out_card);
        logOutCV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                logOut();
            }
        });
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
        logOut();
    }

    public void logOut() {
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(UserHomeActivity.this);
        dialogBuilder.setMessage("Are you sure you want to log out?");
        dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                user.setUserLoggedIn(false);
                user.clearUserData();
                startActivity(new Intent(UserHomeActivity.this, LoginActivity.class));
            }
        });
        dialogBuilder.setNegativeButton("No", null);
        dialogBuilder.show();
    }

}
