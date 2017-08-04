package inducesmile.com.suumme.config;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by user on 25/07/2017.
 */

public class MyApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        ActiveAndroid.initialize(this);
    }
}
