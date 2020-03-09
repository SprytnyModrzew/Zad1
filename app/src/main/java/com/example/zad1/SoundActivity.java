package com.example.zad1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class SoundActivity extends Activity {
    private int selected_sound = 0;
    private static final String TAG = "UserLog";
    ArrayList<RadioButton> buttons = new ArrayList<>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sound);

        Intent received_intent = getIntent();
        selected_sound = received_intent.getIntExtra(MainActivity.extra_info, 0);
        Log.i(TAG, "Selected sound: "+ selected_sound);

        String sounds[] = getResources().getStringArray(R.array.sounds);
        //todo to
        RadioGroup group = findViewById(R.id.sound_group);
        for(int i = 0; i < sounds.length; i++){
            final RadioButton button = new RadioButton(this);
            button.setText(sounds[i]);
            button.setId(i);
            final int keyI = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(button.isChecked())
                        Log.i(TAG, "Radio Clicked, selected:"+keyI);
                    selected_sound = keyI;
                }
            });
            buttons.add(button);
            group.addView(button);
        }
        buttons.get(selected_sound).setChecked(true);
    }
    public void onOKButtonClicked(View view){
        Log.i(TAG, "OK Clicked, selected:" + selected_sound);
        Intent data = new Intent();
        data.putExtra(MainActivity.extra_info, selected_sound);
        setResult(RESULT_OK, data);

        finish();
    }

    public void onCancelButtonClicked(View view){
        Log.i(TAG, "Cancel selected");
        setResult(RESULT_CANCELED);
        finish();
    }
}
