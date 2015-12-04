package com.vappna.collegenav;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class FriendActivity extends AppCompatActivity {

    ListView allUsersLV;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_friend);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Friends");
        allUsersLV = (ListView) findViewById(R.id.all_users_listview);
        ServerRequest serverRequest = new ServerRequest(this);
        serverRequest.getAllUsersInBackground(new GetUserCallback() {
            @Override
            public void done(User returnedUser) {

            }

            @Override
            public void doneRetrievingArray(List<String> returnedArray) {
                Log.e("friend", returnedArray.toString());
                arrayAdapter = new ArrayAdapter<>(FriendActivity.this, android.R.layout.simple_list_item_1, returnedArray);
                allUsersLV = (ListView) findViewById(R.id.all_users_listview);
                allUsersLV.setAdapter(arrayAdapter);
            }
        });

    }

}
