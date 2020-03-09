package com.example.zad1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.util.ArrayList;

public class ContactActivity extends Activity {
    private int selected_contact = 0;
    private static final String TAG = "UserLog";
    RadioGroup group;
    ArrayList<RadioButton> buttons = new ArrayList<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        Intent received_intent = getIntent();
        int current_contact = received_intent.getIntExtra(MainActivity.extra_info,0);
        Log.i(TAG, "Selected contact: "+ current_contact);
        String[] contactsArray = getResources().getStringArray(R.array.contacts);
        group = findViewById(R.id.contact_group);
        selected_contact = current_contact;
        for(int i = 0; i < contactsArray.length; i++){
            final RadioButton button = new RadioButton(this);
            button.setText(contactsArray[i]);
            button.setId(i);
            final int keyI = i;
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(button.isChecked())
                        Log.i(TAG, "Radio Clicked, selected:"+keyI);
                        selected_contact = keyI;
                }
            });
            buttons.add(button);
            group.addView(button);
        }
        buttons.get(selected_contact).setChecked(true);

}

    public void onOKButtonClicked(View view){
        Log.i(TAG, "OK Clicked, selected:" + selected_contact);
        Intent data = new Intent();
        data.putExtra(MainActivity.extra_info, selected_contact);
        setResult(RESULT_OK, data);

        finish();
    }

    public void onCancelButtonClicked(View view){
        Log.i(TAG, "Cancel selected");
        Intent data = new Intent();
        setResult(RESULT_CANCELED);
        finish();
    }
}
