package com.vappna.collegenav;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FriendActivity extends AppCompatActivity {

    ListView allUsersLV;
    TextView noFriendTV;
    ImageView noFriendIV;
    FloatingActionButton addFriendFAB;
    Context cxt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        cxt = FriendActivity.this;
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        getSupportActionBar().setTitle("Friends");
        noFriendTV = (TextView) findViewById(R.id.no_friend_message);
        noFriendIV = (ImageView) findViewById(R.id.sad_face);
        allUsersLV = (ListView) findViewById(R.id.all_users_listview);

        FloatingActionButton addFriend = (FloatingActionButton) findViewById(R.id.addfriend);
        addFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(cxt, FindFriendActivity.class));
            }
        });

        Log.d("Friends",new LocalUser(cxt).getLoggedInUser().getFriends().toString());

        ServerRequest request = new ServerRequest(cxt);
        request.getUserFriendsInBackground(new LocalUser(cxt).getLoggedInUser(), new GetUserCallback() {
            @Override
            public void done(User returnedUser) {

            }
            @Override
            public void done(LatLng userLocation) {

            }

            @Override
            public void doneRetrievingArray(ArrayList<String> returnedList) {
                LocalUser localUser = new LocalUser(cxt);
                localUser.storeUserData(new User(localUser.getLoggedInUser().getUsername(), localUser.getLoggedInUser().getPassword(), localUser.getLoggedInUser().getHomeCollege(), returnedList));
            }
        });

        if (!new LocalUser(FriendActivity.this).getLoggedInUser().getFriends().isEmpty()) {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(FriendActivity.this, android.R.layout.simple_list_item_1, new LocalUser(FriendActivity.this).getLoggedInUser().getFriends());
            allUsersLV = (ListView) findViewById(R.id.all_users_listview);
            allUsersLV.setAdapter(arrayAdapter);
            noFriendIV.setVisibility(View.INVISIBLE);
            noFriendTV.setVisibility(View.INVISIBLE);
        } else {
            allUsersLV.setVisibility(View.INVISIBLE);
            noFriendIV.setVisibility(View.VISIBLE);
            noFriendTV.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void onResume() {
        Log.d("Friends",new LocalUser(cxt).getLoggedInUser().getFriends().toString());

        ServerRequest request = new ServerRequest(cxt);
        request.getUserFriendsInBackground(new LocalUser(cxt).getLoggedInUser(), new GetUserCallback() {
            @Override
            public void done(User returnedUser) {

            }
            @Override
            public void done(LatLng userLocation) {

            }

            @Override
            public void doneRetrievingArray(ArrayList<String> returnedList) {
                LocalUser localUser = new LocalUser(cxt);
                localUser.storeUserData(new User(localUser.getLoggedInUser().getUsername(), localUser.getLoggedInUser().getPassword(), localUser.getLoggedInUser().getHomeCollege(), returnedList));
            }
        });

        if (!new LocalUser(FriendActivity.this).getLoggedInUser().getFriends().isEmpty()) {
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(FriendActivity.this, android.R.layout.simple_list_item_1, new LocalUser(FriendActivity.this).getLoggedInUser().getFriends());
            allUsersLV = (ListView) findViewById(R.id.all_users_listview);
            allUsersLV.setAdapter(arrayAdapter);
            noFriendIV.setVisibility(View.INVISIBLE);
            noFriendTV.setVisibility(View.INVISIBLE);
        } else {
            allUsersLV.setVisibility(View.INVISIBLE);
            noFriendIV.setVisibility(View.VISIBLE);
            noFriendTV.setVisibility(View.VISIBLE);
        }
        super.onResume();

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
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(getApplicationContext(), UserHomeActivity.class));
    }
}
