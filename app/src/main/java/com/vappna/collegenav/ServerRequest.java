package com.vappna.collegenav;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vnair on 11/14/2015.
 */
public class ServerRequest {
    ProgressDialog progressDialog;
    public static final int CONNECTION_TIMEOUT = 1000*15;
    public static final String SERVER_ADDRESS = "http://nairv.site88.net/";

    public ServerRequest(Context cxt){
        progressDialog = new ProgressDialog(cxt);
        progressDialog.setCancelable(false);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please wait...");
    }

    public void storeUserDataInBackground(User user, GetUserCallback callback){
        progressDialog.show();
        new StoreUserDataAsyncTask(user, callback).execute();
    }

    public void fetchUserDataInBackground(User user, GetUserCallback callback){
        progressDialog.show();
        new FetchUserDataAsyncTask(user, callback).execute();
    }

    public void getAllUsersInBackground(GetUserCallback callback){
        progressDialog.setTitle("Getting Users");
        progressDialog.show();
        new GetAllUsersAsyncTask(callback).execute();
    }

    public void getUserFriendsInBackground(User user, GetUserCallback callback){
        progressDialog.setTitle("Getting Friends");
        progressDialog.show();
        new GetUserFriendsAsyncTask(user, callback).execute();
    }

    public void storeUserFriendsInBackground(User user, Friend friend, GetUserCallback callback){
        progressDialog.setTitle("Getting Friends");
        progressDialog.show();
        new StoreUserFriendsAsyncTask(user, friend, callback).execute();
    }

    public class StoreUserDataAsyncTask extends AsyncTask<Void, Void, Void>{

        User user;
        GetUserCallback userCallback;

        public StoreUserDataAsyncTask(User user, GetUserCallback callback){
            this.user= user;
            this.userCallback = callback;
        }

        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("username", user.getUsername()));
            dataToSend.add(new BasicNameValuePair("password", user.getPassword()));

            HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpParams, CONNECTION_TIMEOUT);

            HttpClient httpClient = new DefaultHttpClient(httpParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS+ "Register.php");
            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                httpClient.execute(post);
            } catch (UnsupportedEncodingException e) {
                Log.e("Error", e.getMessage());
            } catch (ClientProtocolException e) {
                Log.e("Error", e.getMessage());
            } catch (IOException e) {
                Log.e("Error", e.getMessage());
            }

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            progressDialog.dismiss();
            userCallback.done(null);
            super.onPostExecute(aVoid);
        }
    }

    public class FetchUserDataAsyncTask extends AsyncTask<Void, Void, User> {

        User user;
        GetUserCallback userCallback;

        public FetchUserDataAsyncTask(User user, GetUserCallback callback) {
            this.user = user;
            this.userCallback = callback;
        }

        @Override
        protected User doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("username", user.getUsername()));
            dataToSend.add(new BasicNameValuePair("password", user.getPassword()));

            HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpParams, CONNECTION_TIMEOUT);

            HttpClient httpClient = new DefaultHttpClient(httpParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "FetchUserData.php");

            String result = null;
            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = httpClient.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                result = EntityUtils.toString(entity);
                Log.e("Result", result);
                JSONObject jsonObject = new JSONObject(result);
                if(jsonObject.length() != 0){
                    Log.e("JSON output", jsonObject.toString());
                    String accepted = jsonObject.getString("accepted");
                    if(accepted.equals("no")){
                        user = null;
                    }
                } else{
                    user = null;
                    Log.e("Array", "jsonArray is empty");
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("JSON Parser", "Error parsing data [" + e.getMessage() + "] " + result);
            }


           // return returnedUser;
            return user;
        }

        @Override
        protected void onPostExecute(User returnedUser){
            progressDialog.dismiss();
            userCallback.done(returnedUser);
            super.onPostExecute(returnedUser);
        }
    }

    public class GetAllUsersAsyncTask extends AsyncTask<Void, Void, List<String>> {

        GetUserCallback userCallback;

        public GetAllUsersAsyncTask(GetUserCallback callback) {
            this.userCallback = callback;
        }

        @Override
        protected List<String> doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            List<String> usernames= new ArrayList<>();

            HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpParams, CONNECTION_TIMEOUT);

            HttpClient httpClient = new DefaultHttpClient(httpParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "GetAllUsers.php");

            String result = null;
            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = httpClient.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                result = EntityUtils.toString(entity);
                Log.e("Result", result);
                JSONArray jsonArray = new JSONArray(result);
                if(jsonArray.length() != 0){
                    Log.e("JSON output", jsonArray.toString());

                    for(int i=0; i<jsonArray.length(); i++){
                        usernames.add(jsonArray.get(i).toString());
                        Log.e("Usernames output", usernames.toString());
                    }
                } else{
                    Log.e("Array", "jsonArray is empty");
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("JSON Parser", "Error parsing data [" + e.getMessage() + "] " + result);
            }


            return usernames;
        }

        @Override
        protected void onPostExecute(List<String> usernames){
            progressDialog.dismiss();
            userCallback.doneRetrievingArray(usernames);
            super.onPostExecute(usernames);
        }
    }

    public class GetUserFriendsAsyncTask extends AsyncTask<Void, Void, List<String>> {

        GetUserCallback userCallback;
        User user;

        public GetUserFriendsAsyncTask(User user, GetUserCallback callback) {
            this.user = user;
            this.userCallback = callback;
        }

        @Override
        protected List<String> doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            List<String> usernames= new ArrayList<>();

            HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpParams, CONNECTION_TIMEOUT);

            HttpClient httpClient = new DefaultHttpClient(httpParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "GetAllUsers.php");

            String result = null;
            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                HttpResponse httpResponse = httpClient.execute(post);

                HttpEntity entity = httpResponse.getEntity();
                result = EntityUtils.toString(entity);
                Log.e("Result", result);
                JSONArray jsonArray = new JSONArray(result);
                if(jsonArray.length() != 0){
                    Log.e("JSON output", jsonArray.toString());

                    for(int i=0; i<jsonArray.length(); i++){
                        usernames.add(jsonArray.get(i).toString());
                        Log.e("Usernames output", usernames.toString());
                    }
                } else{
                    Log.e("Array", "jsonArray is empty");
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
                Log.e("JSON Parser", "Error parsing data [" + e.getMessage() + "] " + result);
            }


            return usernames;
        }

        @Override
        protected void onPostExecute(List<String> usernames){
            progressDialog.dismiss();
            userCallback.doneRetrievingArray(usernames);
            super.onPostExecute(usernames);
        }
    }

    public class StoreUserFriendsAsyncTask extends AsyncTask<Void, Void, Void> {

        GetUserCallback userCallback;
        User user;
        Friend friend;

        public StoreUserFriendsAsyncTask(User user, Friend friend, GetUserCallback callback) {
            this.user = user;
            this.userCallback = callback;
            this.friend = friend;
        }

        @Override
        protected Void doInBackground(Void... params) {
            ArrayList<NameValuePair> dataToSend = new ArrayList<>();
            dataToSend.add(new BasicNameValuePair("username", user.getUsername()));
            dataToSend.add(new BasicNameValuePair("friend", friend.getUsername()));

            HttpParams httpParams = new BasicHttpParams();
            HttpConnectionParams.setConnectionTimeout(httpParams, CONNECTION_TIMEOUT);
            HttpConnectionParams.setSoTimeout(httpParams, CONNECTION_TIMEOUT);

            HttpClient httpClient = new DefaultHttpClient(httpParams);
            HttpPost post = new HttpPost(SERVER_ADDRESS + "StoreUserFriends.php");

            try {
                post.setEntity(new UrlEncodedFormEntity(dataToSend));
                httpClient.execute(post);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid){
            progressDialog.dismiss();
            userCallback.done(null);
            super.onPostExecute(aVoid);
        }
    }
}
