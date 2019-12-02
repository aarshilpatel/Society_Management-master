package com.example.flattemp.Model;

public class Config {

    public static final String LOGIN_URL = UrlsList.base_url+"Android/login.php";
    public static final String Signup_URL = UrlsList.base_url+"Android/signup.php";

    //Keys for email and password as defined in our $_POST['key'] in login.php
    public static final String KEY_EMAIL = "email";
    public static final String KEY_PASSWORD = "password";

    //If server response is equal to this that means login is successful
    public static final String LOGIN_SUCCESS = "success";

    //Keys for Sharedpreferences
    //This would be the name of our shared preferences
    public static final String SHARED_PREF_NAME = "myloginapp";

    //This would be used to store the email of current logged in user
    public static final String EMAIL_SHARED_PREF = "email";

    public static final String MEMBER_ID_SHARED_PREF = "mem_id";

    public static final String MEMBER_NAME_SHARED_PREF = "name";

    public static final String MEMBER_FLAT_SHARED_PREF = "flat";

    //We will use this to store the boolean in sharedpreference to track user is loggedin or not
    public static final String LOGGEDIN_SHARED_PREF = "loggedin";
}
