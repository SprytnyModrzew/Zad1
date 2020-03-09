package com.example.zad1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class SoundActivity extends Activity {
    private int selected_sound = 0;
    private static final String TAG = "UserLog";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        Intent received_intent = getIntent();
        int current_sound = received_intent.getIntExtra(MainActivity.extra_info, 0);
        Log.i(TAG, "Selected contact: "+ current_sound);

        String sounds[] = getResources().getStringArray(R.array.sounds);
        //todo to
    }
}
