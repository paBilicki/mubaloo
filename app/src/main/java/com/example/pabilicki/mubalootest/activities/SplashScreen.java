package com.example.pabilicki.mubalootest.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.example.pabilicki.mubalootest.R;

/**
 * Display a logo and checks if there is internet connection
 *
 * @author Piotr Aleksander Bilicki
 */
public class SplashScreen extends Activity {
    public static boolean internetConnection;
    private static int SPLASH_SCREEN_DELAY = 3000;
    private static final String TAG_WIFI = "WIFI";
    private static final String TAG_MOBILE = "MOBILE";


    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.mubaloo_splash_screen);

        internetConnection = haveNetworkConnection();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, SPLASH_SCREEN_DELAY);
    }

    private boolean haveNetworkConnection() {
        boolean haveConnectedWifi = false;
        boolean haveConnectedMobile = false;

        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo[] netInfo = cm.getAllNetworkInfo();

        for (NetworkInfo ni : netInfo) {
            if (ni.getTypeName().equalsIgnoreCase(TAG_WIFI))
                if (ni.isConnected())
                    haveConnectedWifi = true;
            if (ni.getTypeName().equalsIgnoreCase(TAG_MOBILE))
                if (ni.isConnected())
                    haveConnectedMobile = true;
        }

        return haveConnectedWifi || haveConnectedMobile;
    }
}