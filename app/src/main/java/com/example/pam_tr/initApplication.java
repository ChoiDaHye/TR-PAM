package com.example.pam_tr;

import android.app.Application;

public class initApplication extends Application {
    private static initApplication singleton = null;
    private boolean isNightModeEnabled = false;
    public static initApplication getInstance(){
        if (singleton==null) {
        singleton = new initApplication();
        }
        return singleton;
        }
@Override
public void onCreate(){
        super.onCreate();
        singleton=this;
}
public boolean isNightModeEnabled(){
        return isNightModeEnabled;

}
public void  setNightModeEnabled (boolean n){
        this.isNightModeEnabled=n;
}

}
