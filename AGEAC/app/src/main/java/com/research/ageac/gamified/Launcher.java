package com.research.ageac.gamified;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

public class Launcher extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        final int flags = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        getWindow().getDecorView().setSystemUiVisibility(flags);
        setContentView(R.layout.activity_launcher);

        Thread timer = new Thread() {
            public void run() {
                try {
                    sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if (userRegistered()) {
                        DBAdapter dbAdapter = new DBAdapter(Launcher.this);
                        dbAdapter.addLogEntry("Launcher", "App Started");
                        Intent openMainMenu = new Intent(Launcher.this, MainActivity.class);
                        startActivity(openMainMenu);
                    } else {
                        Intent userRegistrationIntent = new Intent(Launcher.this, UserRegistrationActivity.class);
                        startActivity(userRegistrationIntent);
                    }
                }
            }
        };
        timer.start();
    }

    private boolean userRegistered() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserData", MODE_PRIVATE);
        return sharedPreferences.getBoolean("registered", false);
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
