package com.vappna.collegenav;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchActivity extends Activity {

    SearchView searchBar;
    ListView displaySearchList;
    String[] searchArray;
    List<String> searchArrayList = new ArrayList<String>();
    ArrayAdapter<String> searchAdapter;
    int sendPosition;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(getResources().getColor(R.color.GuilfordPrimaryDark));
            window.setNavigationBarColor(getResources().getColor(R.color.GuilfordPrimary));
        }
        searchArray = getResources().getStringArray(R.array.guilford_academic_administrative_building_names);
        searchArrayList = Arrays.asList(searchArray);
        searchBar = (SearchView) findViewById(R.id.searchView);
        displaySearchList = (ListView) findViewById(R.id.listView);
        final ListAdapter searchAdapter= new ListAdapter(SearchActivity.this, searchArrayList);
        displaySearchList.setAdapter(searchAdapter);
        searchBar.setIconified(false);
        searchBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBar.setIconified(false);
            }
        });
        searchBar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                ArrayList<String> temp = new ArrayList<String>();
                int textlength = searchBar.getQuery().length();
                temp.clear();
                for (int i = 0; i < searchArray.length; i++) {
                    if (textlength <= searchArray[i].length()) {
                        if (searchBar.getQuery().toString().equalsIgnoreCase((String) searchArray[i].subSequence(0, textlength)) | searchBar.getQuery().toString().toLowerCase().contains(searchArray[i])) {
                            temp.add(searchArray[i]);
                        }
                    }
                }
                displaySearchList.setAdapter(new ListAdapter(SearchActivity.this, temp));

                return false;
            }
        });
        displaySearchList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                intent = new Intent(getApplicationContext(), MapContainerActivity.class);
                intent.putExtra("collegeID", "Guilford College");
                intent.putExtra("activityName", "SearchActivity");
                intent.putExtra("position", (String) parent.getItemAtPosition(position));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search, menu);
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
