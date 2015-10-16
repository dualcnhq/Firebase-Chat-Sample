package com.dualcnhq.firebasetest;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by dualcnhq on 10/16/15.
 */
public class FireBaseApplication extends Application {

    @Override
    public void onCreate(){
        super.onCreate();
        Firebase.setAndroidContext(this);


    }
}
