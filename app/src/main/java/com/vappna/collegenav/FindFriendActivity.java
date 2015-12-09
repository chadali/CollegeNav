package com.vappna.collegenav;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class FindFriendActivity extends AppCompatActivity {

    ListView allUsersLV;
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_friend);

        allUsersLV = (ListView) findViewById(R.id.all_users_listview);
        ServerRequest serverRequest = new ServerRequest(this);
        serverRequest.getAllUsersInBackground(new GetUserCallback() {
            @Override
            public void done(User returnedUser) {

            }

            @Override
            public void done(LatLng userLocation) {

            }

            @Override
            public void doneRetrievingArray(ArrayList<String> returnedArray) {
                Log.e("friend", returnedArray.toString());
                arrayAdapter = new ArrayAdapter<>(FindFriendActivity.this, android.R.layout.simple_list_item_1, returnedArray);
                returnedArray.remove(returnedArray.indexOf(new LocalUser(FindFriendActivity.this).getLoggedInUser().getUsername()));
                allUsersLV = (ListView) findViewById(R.id.all_users_listview);
                allUsersLV.setAdapter(arrayAdapter);
            }
        });
        final LocalUser localUser = new LocalUser(FindFriendActivity.this);
        allUsersLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(final AdapterView<?> parent, final View view, final int position, long id) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(FindFriendActivity.this);
                dialogBuilder.setMessage("Are you sure that you want to add " + parent.getItemAtPosition(position).toString() + " as a friend?");
                dialogBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        ServerRequest serverRequest1 = new ServerRequest(FindFriendActivity.this);
                        serverRequest1.storeUserFriendsInBackground(localUser.getLoggedInUser(), new Friend(parent.getItemAtPosition(position).toString()), new GetUserCallback() {
                            @Override
                            public void done(User returnedUser) {

                            }
                            @Override
                            public void done(LatLng userLocation) {

                            }

                            @Override
                            public void doneRetrievingArray(ArrayList<String> returnedArray) {
                                LocalUser localUser = new LocalUser(FindFriendActivity.this);
                                localUser.storeUserData(new User(localUser.getLoggedInUser().getUsername(), localUser.getLoggedInUser().getPassword(), localUser.getLoggedInUser().getHomeCollege(), returnedArray));
                            }
                        });

                        if(localUser.getLoggedInUser().getFriends().isEmpty()){
                            ArrayList<String> friends = new ArrayList<String>();
                            friends.add(parent.getItemAtPosition(position).toString());
                            localUser.storeUserData(new User(localUser.getLoggedInUser().getUsername(), localUser.getLoggedInUser().getPassword(), localUser.getLoggedInUser().getHomeCollege(), friends));
                            Log.d("Friends", localUser.getLoggedInUser().getFriends().toString());

                        }
                        else{
                            ArrayList<String> friends = localUser.getLoggedInUser().getFriends();
                            friends.add(parent.getItemAtPosition(position).toString());
                            localUser.storeUserData(new User(localUser.getLoggedInUser().getUsername(), localUser.getLoggedInUser().getPassword(), localUser.getLoggedInUser().getHomeCollege(), friends));

                        }
                        startActivity(new Intent(FindFriendActivity.this, FriendActivity.class));
                    }
                });
                dialogBuilder.setNegativeButton("No", null);
                dialogBuilder.show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(FindFriendActivity.this, FriendActivity.class));
    }
}
