package com.vappna.collegenav;

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

import java.util.ArrayList;
import java.util.List;

public class FriendActivity extends AppCompatActivity {

    ListView allUsersLV;
    TextView noFriendTV;
    ImageView noFriendIV;
    FloatingActionButton addFriendFAB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Friends");
        noFriendTV = (TextView) findViewById(R.id.no_friend_message);
        noFriendIV = (ImageView) findViewById(R.id.sad_face);
        allUsersLV = (ListView) findViewById(R.id.all_users_listview);
        Log.d("Friends",new LocalUser(FriendActivity.this).getLoggedInUser().getFriends().toString());

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

        addFriendFAB = (FloatingActionButton) findViewById(R.id.addfriend);
        addFriendFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FriendActivity.this, FindFriendActivity.class));
            }
        });

    }

    @Override
    protected void onResume() {
        Log.d("Friends",new LocalUser(FriendActivity.this).getLoggedInUser().getFriends().toString());

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

}
