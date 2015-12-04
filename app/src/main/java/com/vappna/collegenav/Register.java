package com.vappna.collegenav;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Register extends AppCompatActivity {

    Button registerButton;
    EditText usernameET, passwordET, confirmedPasswordET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_register);

        registerButton = (Button) findViewById(R.id.registerButton);
        usernameET = (EditText) findViewById(R.id.username);
        passwordET = (EditText) findViewById(R.id.password);
        confirmedPasswordET = (EditText) findViewById(R.id.confirmedPassword);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!passwordET.getText().toString().equals(confirmedPasswordET.getText().toString())){
                    Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
                else {
                    String username = usernameET.getText().toString();
                    String password = passwordET.getText().toString();
                    User user = new User(username, password);

                    registerUser(user);
                }
            }
        });
    }

    private void registerUser(User user) {
        ServerRequest serverRequest = new ServerRequest(this);
        serverRequest.storeUserDataInBackground(user, new GetUserCallback() {
            @Override
            public void done(User returnedUser) {
                startActivity(new Intent(Register.this, LoginActivity.class));
            }

            @Override
            public void doneRetrievingArray(List<String> returnedArray) {

            }
        });
    }

}
