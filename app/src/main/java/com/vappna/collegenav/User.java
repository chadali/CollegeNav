package com.vappna.collegenav;

/**
 * Created by vnair on 11/14/2015.
 */
public class User {

    private String username;
    private String password;

    private String homeCollege;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String homeCollege) {
        this.username = username;
        this.password = password;
        this.homeCollege = homeCollege;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getHomeCollege() {
        return homeCollege;
    }

    public void setHomeCollege(String homeCollege) {
        this.homeCollege = homeCollege;
    }

}
